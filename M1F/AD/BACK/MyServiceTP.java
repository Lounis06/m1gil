package tp.rest;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

import com.sun.javaws.exceptions.InvalidArgumentException;
import tp.model.Animal;
import tp.model.AnimalNotFoundException;
import tp.model.Cage;
import tp.model.Center;
import tp.model.Position;

@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
public class MyServiceTP implements Provider<Source> {
    // CONSTANTES
    /**
     * La distance utilisée pour désigner la proximité d'une position
     * par rapport à une autre (en mètres)
     */
    public static final double NEAR_DISTANCE = 5000;

    /**
     * L'URL à laquelle tout service crée peut être utilisé
     */
    public static final String url = "http://127.0.0.1:8084/";


    // POINT D'ENTREE
    public static void main(String args[]) {
        Endpoint e = Endpoint.create(HTTPBinding.HTTP_BINDING, new MyServiceTP());

        e.publish(url);
        System.out.println("Service started, listening on " + url);
        // pour arrêter : e.stop();
    }


    // ATTRIBUTS
    private JAXBContext jc;

    @javax.annotation.Resource(type = Object.class)
    protected WebServiceContext wsContext;

    /** Le centre d'animaux servant de modèle au service */
    private Center center = new Center(new LinkedList<>(), new Position(49.30494d, 1.2170602d), "Biotropica");

    // CONSTRUCTEUR
    public MyServiceTP() {
        try {
            jc = JAXBContext.newInstance(Center.class, Cage.class, Animal.class, Position.class);
        } catch (JAXBException je) {
            System.out.println("Exception " + je);
            throw new WebServiceException("Cannot create JAXBContext", je);
        }

        // Fill our center with some animals
        Cage usa = new Cage(
                "usa",
                new Position(49.305d, 1.2157357d),
                25,
                new LinkedList<>(Arrays.asList(
                        new Animal("Tic", "usa", "Chipmunk", UUID.randomUUID()),
                        new Animal("Tac", "usa", "Chipmunk", UUID.randomUUID())
                ))
        );

        Cage amazon = new Cage(
                "amazon",
                new Position(49.305142d, 1.2154067d),
                15,
                new LinkedList<>(Arrays.asList(
                        new Animal("Canine", "amazon", "Piranha", UUID.randomUUID()),
                        new Animal("Incisive", "amazon", "Piranha", UUID.randomUUID()),
                        new Animal("Molaire", "amazon", "Piranha", UUID.randomUUID()),
                        new Animal("De lait", "amazon", "Piranha", UUID.randomUUID())
                ))
        );

        center.getCages().addAll(Arrays.asList(usa, amazon));
    }

    // COMMANDES
    public Source invoke(Source source) {
        MessageContext mc = wsContext.getMessageContext();
        String path = (String) mc.get(MessageContext.PATH_INFO);
        String method = (String) mc.get(MessageContext.HTTP_REQUEST_METHOD);

        // determine the targeted ressource of the call
        try {
            // no target, throw a 404 exception.
            if (path == null) {
                throw new HTTPException(404);
            }
            // "/animals" target - Redirect to the method in charge of managing this sort of call.
            else if (path.startsWith("animals")) {
                String[] path_parts = path.split("/");
                switch (path_parts.length){
                    case 1 :
                        return this.animalsCrud(method, source);
                    case 2 :
                        return this.animalCrud(method, source, path_parts[1]);
                    default:
                        throw new HTTPException(404);
                }
            }
            else if (path.startsWith("find/")) {
                return this.findCrud(method, path);
            }
            else if ("coffee".equals(path)) {
                throw new HTTPException(418);
            }
            else {
                throw new HTTPException(404);
            }
        } catch (JAXBException | AnimalNotFoundException e) {
            throw new HTTPException(500);
        }
    }

    /**
     * Method bound to calls on /animals/{something}
     */
    private Source animalCrud(String method, Source source, String animal_id) throws JAXBException, AnimalNotFoundException {
        if("GET".equals(method)){ // Get an animal by id
            try {
                return new JAXBSource(this.jc, center.findAnimalById(UUID.fromString(animal_id)));
            } catch (AnimalNotFoundException e) {
                throw new HTTPException(404);
            }
        } else if ("POST".equals(method)) { // Create an animal by id
            return addAnAnimalByID(source, animal_id);
        } else if ("PUT".equals(method)) { // Modify an animal by id
            deleteAnAnimalByID(animal_id);
            return addAnAnimalByID(source, animal_id);
        } else if ("DELETE".equals(method)) { // Delete an animal by id
            return deleteAnAnimalByID(animal_id);
        }
        else{
            throw new HTTPException(405);
        }
    }

    /**
     * Method bound to calls on /animals
     */
    private Source animalsCrud(String method, Source source) throws JAXBException {
        if("GET".equals(method)){ // Return all the animals
            return new JAXBSource(this.jc, this.center);
        }
        else if("POST".equals(method)){ // Add an animal
            return addAnAnimal(source);
        }
        else if("PUT".equals(method)){ // Alter all the animals
            return alterAnimals(source);
        }
        else if("DELETE".equals(method)){ // Delete all animals
            for (Cage cage : this.center.getCages()) {
                cage.setResidents(new LinkedList<>());
            }
            return new JAXBSource(this.jc, this.center);
        }
        else {
            throw new HTTPException(405);
        }
    }

    /**
     * Method bound to calls on /find
     */
    private Source findCrud(String method, String path) throws JAXBException, AnimalNotFoundException {
        if ("GET".equals(method)) {
            // Contrôle des arguments
            String[] path_parts = path.split("/");
            if (path_parts.length != 3) {
                throw new HTTPException(405);
            }

            // Obtention du prédicat utilisé pour la recherche
            try {
                switch (path_parts[1]) {
                    case "byName" :
                        final String animal_name = path_parts[2].trim();
                        return findFromPredicate(a -> a.getName().equals(animal_name));

                    case "bySpecies" :
                        final String species_name = path_parts[2].trim();
                        return findFromPredicate(a -> a.getSpecies().equals(species_name));

                    case "near" :
                        final Position near = parsePosition(path_parts[2]);
                        return findFromCage(cage -> getDistance(cage.getPosition(), near) < NEAR_DISTANCE);

                    case "at" :
                        final Position at = parsePosition(path_parts[2]);
                        return findFromCage(cage -> cage.getPosition().equals(at));
                }
            } catch (Exception e) {
                throw new HTTPException(405);
            }
        }
        // Méthode de requête incorrect : on lance une exception
        throw new HTTPException(405);
    }


    // FONCTIONNALITES DU SERVICE
    /**
     * Ajoute un nouvel animal au centre
     *
     * @param source La source XML de l'objet animal à ajouter
     */
    private Source addAnAnimal(Source source) throws JAXBException {
        Animal animal = unmarshalAnimal(source);
        this.center.getCages()
                .stream()
                .filter(cage -> cage.getName().equals(animal.getCage()))
                .findFirst()
                .orElseThrow(() -> new HTTPException(404))
                .getResidents()
                .add(animal);
        return new JAXBSource(this.jc, this.center);
    }

    /**
     * Modifie les animaux du centre
     *
     * @param source La source XML de l'objet animal à substituer
     */
    private Source alterAnimals(Source source) throws JAXBException {
        Animal animal = unmarshalAnimal(source);
        Optional<Cage> rightCage = this.center.getCages()
                .stream()
                .filter(cage -> cage.getName().equals(animal.getCage()))
                .findAny();
        if (!rightCage.isPresent()) {
            return new JAXBSource(this.jc, this.center);
        }

        Collection<Cage> wrongCages = this.center.getCages()
                .stream()
                .filter(cage -> !cage.getName().equals(animal.getCage()))
                .collect(Collectors.toList());

        for (Cage cage : wrongCages) {
            rightCage.get().getResidents().addAll(cage.getResidents());
            cage.setResidents(new LinkedList<>());
        }

        for (Cage cage : this.center.getCages()) {
            for (Animal a : cage.getResidents()) {
                a.setCage(animal.getCage());
                a.setName(animal.getName());
                a.setSpecies(animal.getSpecies());
            }
        }

        return new JAXBSource(this.jc, this.center);
    }

    /**
     * Ajoute un nouvel animal en lui attribuant l'identifiant spécifié
     *
     * @param source La source de l'objet XML décrivant l'animal à ajouter
     * @param animal_id L'identifiant à associer
     * @throws JAXBException
     */
    private Source addAnAnimalByID(Source source, String animal_id) throws JAXBException {
        Animal newAnimal = unmarshalAnimal(source);
        newAnimal.setId(UUID.fromString(animal_id));
        this.center.getCages()
                .stream()
                .filter(cage -> cage.getName().equals(newAnimal.getCage()))
                .findFirst()
                .orElseThrow(() -> new HTTPException(404))
                .getResidents()
                .add(newAnimal);
        return new JAXBSource(this.jc, this.center);
    }

    /**
     * Supprime l'animal du centre associé à l'identifiant spécifié
     *
     * @param animal_id L'identifiant de l'animal à supprimer
     * @throws JAXBException
     */
    private Source deleteAnAnimalByID(String animal_id) throws JAXBException, AnimalNotFoundException {
        Animal animal = center.findAnimalById(UUID.fromString(animal_id));
        this.center.getCages()
                .stream()
                .filter(cage -> cage.getName().equals(animal.getCage()))
                .findFirst()
                .orElseThrow(() -> new HTTPException(404))
                .getResidents()
                .remove(animal);
        return new JAXBSource(this.jc, this.center);
    }

    /**
     * Trouve tous les animaux appartenant dans les cages remplissant
     * le prédicat donné.
     *
     * @param predicate La condition de recherche sur une cage donnée
     */
    private Source findFromCage(Predicate<Cage> predicate) throws JAXBException, AnimalNotFoundException {
        // Obtention des cages respectant le prédicat donné
        Iterator<Cage> it = this.center.getCages()
                .stream()
                .filter(predicate)
                .filter(cage -> cage.getResidents().size() > 0)
                .iterator();

        // Récupération des animaux
        List<Animal> animals = new ArrayList<>();
        while (it.hasNext()) {
            for (Animal a : it.next().getResidents()) {
                animals.add(a);
            }
        }

        // Si aucun animal n'a été trouvé : on lance une exception
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException();
        }

        // On renvoie les animaux trouvés sous le format d'une cage.
        Cage cage = new Cage();
        cage.setResidents(animals);
        return new JAXBSource(this.jc, cage);
    }

    /**
     * Trouve tous les animaux remplissant tous les critères
     * définis par le prédicat spécifié.
     *
     * @param predicate Le critère de recherche sur un animal quelconque
     */
    private Source findFromPredicate(Predicate<Animal> predicate) throws JAXBException, AnimalNotFoundException {
        // Obtention des cages respectant le prédicat donné
        Iterator<Cage> it = this.center.getCages()
                .stream()
                .filter(cage -> cage.getResidents().stream().anyMatch(predicate))
                .filter(cage -> cage.getResidents().size() > 0)
                .iterator();

        // Récupération des animaux
        List<Animal> animals = new ArrayList<>();
        while (it.hasNext()) {
            for (Animal a : it.next().getResidents()) {
                if (predicate.test(a)) animals.add(a);
            }
        }

        // Si aucun animal n'a été trouvé : on lance une exception
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException();
        }

        // On renvoie les animaux trouvés sous le format d'une cage.
        Cage cage = new Cage();
        cage.setResidents(animals);
        return new JAXBSource(this.jc, cage);
    }


    // OUTILS
    /**
     * Convertit un objet XML provenant de la source spécifiée en instance
     * de la classe Animal.
     *
     * @param source
     * @return L'objet Animal correspondant à la source fournie.
     * @throws JAXBException
     */
    private Animal unmarshalAnimal(Source source) throws JAXBException {
        return (Animal) this.jc.createUnmarshaller().unmarshal(source);
    }

    /**
     * Parse une chaîne décrivant une position
     * selon le format "(???,???)".
     *
     * @param position
     * @return L'objet position correspondant à la chaîne entrée
     * @throws InvalidArgumentException
     */
    public static Position parsePosition(String position) throws Exception {
        String[] terms = position.substring(1, position.length() - 1).split(",");
        if (terms.length != 2) {
            throw new Exception();
        }

        return new Position(Double.valueOf(terms[0]), Double.valueOf(terms[1]));
    }


    /**
     * Détermine la distance entre 2 positions déterminées par
     * leur latitudes et leurs longitudes.
     *
     * @return La distance entre ces positions en mètres.
     */
    public double getDistance(Position p1, Position p2) {
        // Différences angulaires
        Double latD = Math.toRadians(p2.getLatitude() - p1.getLatitude());
        Double lonD = Math.toRadians(p2.getLongitude() - p1.getLongitude());

        // Calcul de la distance (arc de cercles)
        Double a = Math.sin(latD / 2) * Math.sin(latD / 2)
                + Math.cos(Math.toRadians(p1.getLatitude())) * Math.cos(Math.toRadians(p2.getLatitude()))
                * Math.sin(lonD / 2) * Math.sin(lonD / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Conversion Radians -> Mètres
        final int R = 6371; // Rayon de la terre
        return (double) R * c * 1000;
    }
}
