/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /uml.addressbook/model/addressbook.ecore
 * using:
 *   /uml.addressbook/model/addressbook.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package addressbook.univrouen.fr.addressbook;

import addressbook.univrouen.fr.addressbook.AddressbookPackage;
import addressbook.univrouen.fr.addressbook.AddressbookTables;
import java.lang.String;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreLibraryOppositeProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * AddressbookTables provides the dispatch tables for the addressbook for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
@SuppressWarnings("nls")
public class AddressbookTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final /*@NonNull*/ EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(AddressbookPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final /*@NonNull*/ ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
    public static final /*@NonNull*/ /*@NonInvalid*/ NsURIPackageId PACKid_addressbook_univrouen_fr = IdManager.getNsURIPackageId("addressbook.univrouen.fr", null, AddressbookPackage.eINSTANCE);
    public static final /*@NonNull*/ /*@NonInvalid*/ NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
    public static final /*@NonNull*/ /*@NonInvalid*/ ClassId CLSSid_AddressBook = AddressbookTables.PACKid_addressbook_univrouen_fr.getClassId("AddressBook", 0);
    public static final /*@NonNull*/ /*@NonInvalid*/ ClassId CLSSid_Adresse = AddressbookTables.PACKid_addressbook_univrouen_fr.getClassId("Adresse", 0);
    public static final /*@NonNull*/ /*@NonInvalid*/ ClassId CLSSid_Personne = AddressbookTables.PACKid_addressbook_univrouen_fr.getClassId("Personne", 0);
    public static final /*@NonNull*/ /*@NonInvalid*/ DataTypeId DATAid_EInt = AddressbookTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EInt", 0);
    public static final /*@NonNull*/ /*@NonInvalid*/ IntegerValue INT_0 = ValueUtil.integerValueOf("0");
    public static final /*@NonNull*/ /*@NonInvalid*/ IntegerValue INT_16 = ValueUtil.integerValueOf("16");
    public static final /*@NonNull*/ /*@NonInvalid*/ String STR_Personne_c_c_ageMin = "Personne::ageMin";
    public static final /*@NonNull*/ /*@NonInvalid*/ String STR_Personne_c_c_nomFormat = "Personne::nomFormat";
    public static final /*@NonNull*/ /*@NonInvalid*/ String STR__94_91_A_m_Z_93_a_$ = "^[A-Z]*$";
    public static final /*@NonNull*/ /*@NonInvalid*/ CollectionTypeId ORD_CLSSid_Personne = TypeId.ORDERED_SET.getSpecializedId(AddressbookTables.CLSSid_Personne);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			AddressbookTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::TypeParameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The type descriptors for each type.
	 */
	public static class Types {
		static {
			Init.initStart();
			TypeParameters.init();
		}

		public static final /*@NonNull*/ EcoreExecutorType _AddressBook = new EcoreExecutorType(AddressbookPackage.Literals.ADDRESS_BOOK, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Adresse = new EcoreExecutorType(AddressbookPackage.Literals.ADRESSE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Personne = new EcoreExecutorType(AddressbookPackage.Literals.PERSONNE, PACKAGE, 0);

		private static final /*@NonNull*/ EcoreExecutorType[] types = {
			_AddressBook,
			_Adresse,
			_Personne
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::Types and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragment descriptors for the local elements of each type and its supertypes.
	 */
	public static class Fragments {
		static {
			Init.initStart();
			Types.init();
		}

		private static final /*@NonNull*/ ExecutorFragment _AddressBook__AddressBook = new ExecutorFragment(Types._AddressBook, AddressbookTables.Types._AddressBook);
		private static final /*@NonNull*/ ExecutorFragment _AddressBook__OclAny = new ExecutorFragment(Types._AddressBook, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _AddressBook__OclElement = new ExecutorFragment(Types._AddressBook, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Adresse__Adresse = new ExecutorFragment(Types._Adresse, AddressbookTables.Types._Adresse);
		private static final /*@NonNull*/ ExecutorFragment _Adresse__OclAny = new ExecutorFragment(Types._Adresse, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Adresse__OclElement = new ExecutorFragment(Types._Adresse, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Personne__OclAny = new ExecutorFragment(Types._Personne, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Personne__OclElement = new ExecutorFragment(Types._Personne, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _Personne__Personne = new ExecutorFragment(Types._Personne, AddressbookTables.Types._Personne);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}

		public static final /*@NonNull*/ ParameterTypes _ = TypeUtil.createParameterTypes();
		public static final /*@NonNull*/ ParameterTypes _Personne = TypeUtil.createParameterTypes(AddressbookTables.Types._Personne);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		public static final /*@NonNull*/ ExecutorOperation _AddressBook__addContact = new ExecutorOperation("addContact", Parameters._Personne, Types._AddressBook,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final /*@NonNull*/ ExecutorOperation _AddressBook__removeContact = new ExecutorOperation("removeContact", Parameters._Personne, Types._AddressBook,
			1, TemplateParameters.EMPTY_LIST, null);

		public static final /*@NonNull*/ ExecutorOperation _Adresse__display = new ExecutorOperation("display", Parameters._, Types._Adresse,
			0, TemplateParameters.EMPTY_LIST, null);

		public static final /*@NonNull*/ ExecutorOperation _Personne__display = new ExecutorOperation("display", Parameters._, Types._Personne,
			0, TemplateParameters.EMPTY_LIST, null);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final /*@NonNull*/ ExecutorProperty _AddressBook__name = new EcoreExecutorProperty(AddressbookPackage.Literals.ADDRESS_BOOK__NAME, Types._AddressBook, 0);
		public static final /*@NonNull*/ ExecutorProperty _AddressBook__personne = new EcoreExecutorProperty(AddressbookPackage.Literals.ADDRESS_BOOK__PERSONNE, Types._AddressBook, 1);

		public static final /*@NonNull*/ ExecutorProperty _Adresse__numero = new EcoreExecutorProperty(AddressbookPackage.Literals.ADRESSE__NUMERO, Types._Adresse, 0);
		public static final /*@NonNull*/ ExecutorProperty _Adresse__rue = new EcoreExecutorProperty(AddressbookPackage.Literals.ADRESSE__RUE, Types._Adresse, 1);
		public static final /*@NonNull*/ ExecutorProperty _Adresse__Personne__adresse = new ExecutorPropertyWithImplementation("Personne", Types._Adresse, 2, new EcoreLibraryOppositeProperty(AddressbookPackage.Literals.PERSONNE__ADRESSE));

		public static final /*@NonNull*/ ExecutorProperty _Personne__adresse = new EcoreExecutorProperty(AddressbookPackage.Literals.PERSONNE__ADRESSE, Types._Personne, 0);
		public static final /*@NonNull*/ ExecutorProperty _Personne__age = new EcoreExecutorProperty(AddressbookPackage.Literals.PERSONNE__AGE, Types._Personne, 1);
		public static final /*@NonNull*/ ExecutorProperty _Personne__identifier = new EcoreExecutorProperty(AddressbookPackage.Literals.PERSONNE__IDENTIFIER, Types._Personne, 2);
		public static final /*@NonNull*/ ExecutorProperty _Personne__nom = new EcoreExecutorProperty(AddressbookPackage.Literals.PERSONNE__NOM, Types._Personne, 3);
		public static final /*@NonNull*/ ExecutorProperty _Personne__prenom = new EcoreExecutorProperty(AddressbookPackage.Literals.PERSONNE__PRENOM, Types._Personne, 4);
		public static final /*@NonNull*/ ExecutorProperty _Personne__AddressBook__personne = new ExecutorPropertyWithImplementation("AddressBook", Types._Personne, 5, new EcoreLibraryOppositeProperty(AddressbookPackage.Literals.ADDRESS_BOOK__PERSONNE));
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final /*@NonNull*/ ExecutorFragment[] _AddressBook =
		{
			Fragments._AddressBook__OclAny /* 0 */,
			Fragments._AddressBook__OclElement /* 1 */,
			Fragments._AddressBook__AddressBook /* 2 */
		};
		private static final /*@NonNull*/ int[] __AddressBook = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment[] _Adresse =
		{
			Fragments._Adresse__OclAny /* 0 */,
			Fragments._Adresse__OclElement /* 1 */,
			Fragments._Adresse__Adresse /* 2 */
		};
		private static final /*@NonNull*/ int[] __Adresse = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment[] _Personne =
		{
			Fragments._Personne__OclAny /* 0 */,
			Fragments._Personne__OclElement /* 1 */,
			Fragments._Personne__Personne /* 2 */
		};
		private static final /*@NonNull*/ int[] __Personne = { 1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._AddressBook.initFragments(_AddressBook, __AddressBook);
			Types._Adresse.initFragments(_Adresse, __Adresse);
			Types._Personne.initFragments(_Personne, __Personne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final /*@NonNull*/ ExecutorOperation[] _AddressBook__AddressBook = {
			AddressbookTables.Operations._AddressBook__addContact /* addContact(Personne[?]) */,
			AddressbookTables.Operations._AddressBook__removeContact /* removeContact(Personne[?]) */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _AddressBook__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _AddressBook__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		private static final /*@NonNull*/ ExecutorOperation[] _Adresse__Adresse = {
			AddressbookTables.Operations._Adresse__display /* display() */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _Adresse__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _Adresse__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		private static final /*@NonNull*/ ExecutorOperation[] _Personne__Personne = {
			AddressbookTables.Operations._Personne__display /* display() */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _Personne__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation[] _Personne__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AddressBook__AddressBook.initOperations(_AddressBook__AddressBook);
			Fragments._AddressBook__OclAny.initOperations(_AddressBook__OclAny);
			Fragments._AddressBook__OclElement.initOperations(_AddressBook__OclElement);

			Fragments._Adresse__Adresse.initOperations(_Adresse__Adresse);
			Fragments._Adresse__OclAny.initOperations(_Adresse__OclAny);
			Fragments._Adresse__OclElement.initOperations(_Adresse__OclElement);

			Fragments._Personne__OclAny.initOperations(_Personne__OclAny);
			Fragments._Personne__OclElement.initOperations(_Personne__OclElement);
			Fragments._Personne__Personne.initOperations(_Personne__Personne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::FragmentOperations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final /*@NonNull*/ ExecutorProperty[] _AddressBook = {
			AddressbookTables.Properties._AddressBook__name,
			AddressbookTables.Properties._AddressBook__personne
		};

		private static final /*@NonNull*/ ExecutorProperty[] _Adresse = {
			AddressbookTables.Properties._Adresse__numero,
			AddressbookTables.Properties._Adresse__rue,
			AddressbookTables.Properties._Adresse__Personne__adresse
		};

		private static final /*@NonNull*/ ExecutorProperty[] _Personne = {
			AddressbookTables.Properties._Personne__adresse,
			AddressbookTables.Properties._Personne__age,
			AddressbookTables.Properties._Personne__identifier,
			AddressbookTables.Properties._Personne__nom,
			AddressbookTables.Properties._Personne__prenom,
			AddressbookTables.Properties._Personne__AddressBook__personne
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AddressBook__AddressBook.initProperties(_AddressBook);
			Fragments._Adresse__Adresse.initProperties(_Adresse);
			Fragments._Personne__Personne.initProperties(_Personne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::FragmentProperties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of AddressbookTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that
	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.
	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.
	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.
	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so
	 * ensure that residual construction occurs just once after all predecessors.
	 */
	private static class Init {
		/**
		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.
		 */
		private static int initCount = 0;

		/**
		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.
		 */
		private static void initEnd() {
			if (initCount > 0) {
				if (--initCount == 0) {
					initCount = -1;
					EnumerationLiterals.init();
				}
			}
		}
	}

	static {
		Init.initEnd();
	}

	/*
	 * Force initialization of outer fields. Inner fields are lazily initialized.
	 */
	public static void init() {}
}
