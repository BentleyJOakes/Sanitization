package sanitizepolicies;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class PrintSanitizePolicy
{

	public static EList<EObject> sanitize(EList<EObject> eobjects)
	{
		System.out.println("Start sanitization");
		
		
		//TODO: Don't assume heirarchical
		
		EPackageImpl root = (EPackageImpl) eobjects.get(0);
		
		sanitizeRoot(root);
		
		
		Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
		if (eClasses.size() > 0)
			sanitizeClasses(eClasses);
		
		Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
		if (eEnums.size() > 0)
			sanitizeEnums(eEnums);
		
		Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
		//eDataTypes.removeAll(eEnums);
		
		
		for (EDataType eDataType : eDataTypes)
		{
			sanitizeEDataType(eDataType);
			
		}
		
		
		return eobjects;
	
	}
	
	private static void sanitizeEModelElement(EModelElement eme)
	{
		for (EAnnotation eAnnotation : eme.getEAnnotations())
		{
			String source = eAnnotation.getSource();
			EMap<String, String> details = eAnnotation.getDetails();
		}
		
		String packageDocumentation = EcoreUtil.getDocumentation(eme);
		//System.out.println("packageDocumentation: " + packageDocumentation);
		
		List<String> constraints = EcoreUtil.getConstraints(eme);
		
	}
	
	
	private static void sanitizeENamedElement(ENamedElement ene)
	{
		sanitizeEModelElement(ene);
		String name = ene.getName();
		
		System.out.println("Name: " + name);
	}
	
	
	private static void sanitizeRoot(EPackageImpl root)
	{
		sanitizeEModelElement(root);
		
		//get namespaces
		System.out.println("NS_URI: " + root.getNsURI());
		System.out.println("NS_Prefix: " + root.getNsPrefix());
	}
	
	
	private static void sanitizeClasses(Collection<EClass> eClasses)
	{
		for (EClass eClass : eClasses)
		{
			System.out.println("EClass:");
			sanitizeEClassifier(eClass);
			
			
			boolean isAbstract = eClass.isAbstract();
			boolean isInterface = eClass.isInterface();
			
			if (isAbstract)
				System.out.println("\tisAbstract: " + isAbstract);
			
			if (isInterface)
				System.out.println("\tisInterface: " + isInterface);
			
			
			if (eClass.getEGenericSuperTypes().size() > 0)
			{
				System.out.println("\tSuperTypes: ");
				for (EGenericType eSuperType : eClass.getEGenericSuperTypes())
				{
					sanitizeEGenericType(eSuperType);
				}
				System.out.println("\tEnd SuperTypes: ");
			}
			
			if (eClass.getEAttributes().size() > 0)
			{
				System.out.println("\tAttributes: ");
				for (EAttribute eAttribute : eClass.getEAttributes())
				{
					sanitizeEAttribute(eAttribute);
				}
			}
			
			if (eClass.getEReferences().size() > 0)
			{
				System.out.println("\tReferences: ");
				for (EReference eReference : eClass.getEReferences())
				{
					sanitizeEReference(eReference);
					 
				}
				System.out.println("\tEnd References: ");
			}
			
			if (eClass.getEOperations().size() > 0)
			{
				System.out.println("\tEOperations: ");
				for (EOperation eOperation : eClass.getEOperations())
				{
					
					sanitizeETypedElement(eOperation);
					
					for (EParameter ep : eOperation.getEParameters())
					{
						sanitizeETypedElement(ep);
					}
					
					for (ETypeParameter etp : eOperation.getETypeParameters())
					{
						sanitizeENamedElement(etp);
					}
					
					for (EClassifier ee : eOperation.getEExceptions())
					{
						sanitizeEClassifier(ee);
					}
				}
				System.out.println("\tEnd EOperations: ");
			}
		}
	}
	
	
	private static void sanitizeEnums(Collection<EEnum> eEnums) {
		for (EEnum eEnum : eEnums)
		{
			sanitizeEModelElement(eEnum);
			
			
			for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
			{
				String litName = eEnumLiteral.getName();
				String enumLiteralDocumentation = EcoreUtil.getDocumentation(eEnumLiteral);
				
				String lit = eEnumLiteral.getLiteral();
				int value = eEnumLiteral.getValue();
			}
		}
	}
	
	
	

	
	
	
	private static void sanitizeEClassifier(EClassifier ec)
	{
		sanitizeENamedElement(ec);
		
		String instanceClassName = ec.getInstanceClassName();
		String instanceTypeName = ec.getInstanceTypeName();
		
		for (ETypeParameter etp : ec.getETypeParameters())
		{
			for (EGenericType egt : etp.getEBounds())
			{
				sanitizeEGenericType(egt);
			}
			
			
		}
	}
	
	private static void sanitizeEGenericType(EGenericType egt)
	{
		System.out.println(egt.getERawType().getName());
		
	}
	
	private static void sanitizeETypedElement(ETypedElement ete)
	{
		sanitizeENamedElement(ete);
		
		boolean isOrdered = ete.isOrdered();
		boolean isUnique = ete.isUnique();
		boolean isMany = ete.isMany();
		boolean isRequired = ete.isRequired();
		
		int lowerBound = ete.getLowerBound();
		int upperBound = ete.getUpperBound();
	}
	
	private static void sanitizeEStructuralFeature(EStructuralFeature esf)
	{
		sanitizeETypedElement(esf);
		
		boolean isOrdered = esf.isOrdered();
		boolean isUnique = esf.isUnique();
		boolean isMany = esf.isMany();
		boolean isRequired = esf.isRequired();
		boolean isDerived = esf.isDerived();
		
		String dvl = esf.getDefaultValueLiteral();
		
		int ID = esf.getFeatureID();
		
		
	}
	
	private static void sanitizeEDataType(EDataType edt)
	{
		sanitizeEClassifier(edt);
		boolean isSerializable = edt.isSerializable();
	}
	
	
	private static void sanitizeEAttribute(EAttribute ea)
	{
		sanitizeEStructuralFeature(ea);
		boolean isID = ea.isID();
		
		EDataType edt = ea.getEAttributeType();
		sanitizeEDataType(edt);
	}


	private static void sanitizeEReference(EReference er)
	{
		sanitizeEStructuralFeature(er);
		
		EReference eOpposite = er.getEOpposite();
		
		 for (Iterator<EAttribute> i = er.getEKeys().iterator(); i.hasNext();)
	      {
	        EAttribute attribute = i.next();
	        sanitizeEAttribute(attribute);
	        
	      }
		 
		 boolean isContainment = er.isContainment();
		 boolean isContainer = er.isContainer();
		 boolean isResolveProxies = er.isResolveProxies();
	}



	
}
