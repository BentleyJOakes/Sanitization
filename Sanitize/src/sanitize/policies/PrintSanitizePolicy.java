package sanitize.policies;

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

public class PrintSanitizePolicy extends SanitizePolicy
{

	
	protected void sanitizeEAnnotation(EAnnotation ea)
	{
		String source = ea.getSource();
		System.out.println("Source: " + source);
		
		EMap<String, String> details = ea.getDetails();
		for (String s : details.keySet())
		{
			System.out.println("Key: " + s);
		}
	}
	
	
	protected void sanitizeEModelElement(EModelElement eme)
	{
		String packageDocumentation = EcoreUtil.getDocumentation(eme);
		//System.out.println("packageDocumentation: " + packageDocumentation);
		
		List<String> constraints = EcoreUtil.getConstraints(eme);
		
	}
	
	protected void sanitizeENamedElement(ENamedElement ene)
	{
		String name = ene.getName();
		System.out.println("\tName: " + name);
	}
	
	
	protected void sanitizeRoot(EPackageImpl root)
	{
		//get namespaces
		System.out.println("NS_URI: " + root.getNsURI());
		root.setNsURI("BlahURI");
		
		System.out.println("NS_Prefix: " + root.getNsPrefix());
		root.setNsPrefix("BlahPrefix");
	}
	

	
	
			
	protected void sanitizeEClassifier(EClassifier ec)
	{
		
		String instanceClassName = ec.getInstanceClassName();
		String instanceTypeName = ec.getInstanceTypeName();
		
		if (instanceClassName != null)
			System.out.print("\tinstanceClassName: " + instanceClassName);
		
		if (instanceClassName != null)
			System.out.println("\tinstanceTypeName: " + instanceTypeName);
		
		
	}
	
	protected void sanitizeEGenericType(EGenericType egt)
	{
		System.out.println("EGenericType: " + egt.getERawType().getName());
		
	}
	
	protected void sanitizeETypedElement(ETypedElement ete)
	{
		//System.out.println(ete.getName());
		boolean isOrdered = ete.isOrdered();
		boolean isUnique = ete.isUnique();
		boolean isMany = ete.isMany();
		boolean isRequired = ete.isRequired();
		
		int lowerBound = ete.getLowerBound();
		int upperBound = ete.getUpperBound();
		
		System.out.print("\tisOrdered: " + isOrdered);
		System.out.print("\tisUnique: " + isUnique);
		System.out.print("\tisMany: " + isMany);
		System.out.println("\tisRequired: " + isRequired);
	
		System.out.print("\tlowerBound: " + lowerBound);
		System.out.println("\tupperBound: " + upperBound);
	}
	
	
	
	protected void sanitizeEStructuralFeature(EStructuralFeature esf)
	{	
		System.out.println("Structural Feature");

		boolean isDerived = esf.isDerived();
		
		String dvl = esf.getDefaultValueLiteral();

		System.out.println("\tisDerived: " + isDerived);
		if (dvl != null)
			System.out.println("\tDefaultValueLiteral: " + dvl);

		
		
	}
	
	
	
	protected void sanitizeEDataType(EDataType edt)
	{
		boolean isSerializable = edt.isSerializable();
	}
	

	
	
	protected void sanitizeEAttribute(EAttribute ea)
	{
		System.out.println("Attribute:");
		boolean isID = ea.isID();
		System.out.println("\tisID: " + isID);

		EDataType edt = ea.getEAttributeType();
		System.out.println("Data Type: " + edt.getName());
		
	}
	

	protected void sanitizeEReference(EReference er)
	{
		System.out.println("Reference: ");
		EReference eOpposite = er.getEOpposite();
		if (eOpposite != null)
			System.out.println("\tEOpposite: " + eOpposite.getName());
		
		 boolean isContainment = er.isContainment();
		 boolean isContainer = er.isContainer();
		 boolean isResolveProxies = er.isResolveProxies();
		 System.out.print("\tisContainment: " + isContainment);
		 System.out.print("\tisContainer:" + isContainer);
		 System.out.println("\tisResolveProxies: " + isResolveProxies);
		 
	}
	
	



	protected void sanitizeEClass(EClass eClass)
	{
		System.out.println("*****************************************************");
		System.out.println("EClass: ");
		
		boolean isAbstract = eClass.isAbstract();
		boolean isInterface = eClass.isInterface();
		

		System.out.print("\tisAbstract: " + isAbstract);
		System.out.println("\tisInterface: " + isInterface);
	
	}
	
	protected void sanitizeEEnumLiteral(EEnumLiteral eEnumLiteral)
	{
		System.out.print("EENumLiteral: ");
		String lit = eEnumLiteral.getLiteral();
		int value = eEnumLiteral.getValue();
		
		System.out.println(lit + " = " + value);
	}
}
