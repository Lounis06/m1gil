public class Test {
	public static void main(String[] args) {
		// Partie serveur :
		IEmployee e=new Employee("Marcel-Paul Schutzenberger", "Universite de Paris VII", "0235020202", "1729999999999", "Dep. Mathematiques", 1697);
		IPolicy pol=new DenyFirst();
		pol.allow("getName");
		pol.allow("getAddress");

		IEmployee returned_employee=(IEmployee)PolicyProxy.newInstance(e, pol, new Class[] { IEmployee.class } );

		// Partie cliente :
		System.out.println("Nom de l'employe : "+returned_employee.getName());
		System.out.println("Adresse de l'employe : "+returned_employee.getAddress());
		System.out.println("Numero de telephone de l'employe : "+returned_employee.getPhoneNumber());
	}
}

