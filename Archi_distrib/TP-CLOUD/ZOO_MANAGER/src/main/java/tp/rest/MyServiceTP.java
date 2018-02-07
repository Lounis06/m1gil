package tp.rest;

import org.apache.http.protocol.HTTP;
import tp.model.*;

import javax.ws.rs.*;
import javax.xml.bind.JAXBException;
import javax.xml.ws.http.HTTPException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

@Path("/zoo-manager/")
public class MyServiceTP {

    private Center center = new Center(new LinkedList<>(), new Position(49.30494d, 1.2170602d), "Biotropica");

    public MyServiceTP() {
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

    /**
     * GET method bound to calls on /animals/{something}
     */
    @GET
    @Path("/animals/{id}/")
    @Produces("application/xml")
    public Animal getAnimal(@PathParam("id") String animal_id) throws JAXBException {
        try {
            return center.findAnimalById(UUID.fromString(animal_id));
        } catch (AnimalNotFoundException e) {
            throw new HTTPException(404);
        }
    }

    /**
     * GET method bound to calls on /animals
     */
    @GET
    @Path("/animals/")
    @Produces("application/xml")
    public Center getAnimals(){
        return this.center;
    }


    /**
     * POST method bound to calls on /animals
     */
    @POST
    @Path("/animals/")
    @Consumes({"application/xml", "application/json" })
    public Center postAnimals(Animal animal) throws JAXBException {
        this.center.getCages()
                .stream()
                .filter(cage -> cage.getName().equals(animal.getCage()))
                .findFirst()
                .orElseThrow(() -> new HTTPException(404))
                .getResidents()
                .add(animal);
        return this.center;
    }

    /**
     * DELETE method bound to calls on /animals
     */
    @DELETE
    @Path("/animals/")
    @Produces("application/xml")
    public Center deleteAnimals() throws JAXBException {
        this.center.getCages()
                .stream()
                .findAny()
    }

    /**
     * GET method bound to calls on /find/byName
     */
    @GET
    @Path("/find/byName/{name}/")
    @Produces("application/xml")
    public Animal getFindByName(@PathParam("name") String animal_name) throws JAXBException {
        try {
            return center.findByName(animal_name);
        } catch (AnimalNotFoundException e) {
            throw new HTTPException(404);
        }
    }

    /**
     * GET method bound to calls on /find/at
     */
    @GET
    @Path("/find/at/{position}/")
    @Produces("application/xml")
    public Cage getFindAt(@PathParam("position") String animal_position) throws JAXBException {
        try {
            String positions[] = animal_position.split(";");
            Position position = new Position(Double.parseDouble(positions[0]), Double.parseDouble(positions[1]));
            return center.findAt(position);
        } catch (AnimalNotFoundException e) {
            throw new HTTPException(404);
        }
    }

}
