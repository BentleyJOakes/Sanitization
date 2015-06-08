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
		ea.setSource("BlahSource");
		
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
		System.out.println("*****************************************************");
		String name = ene.getName();
		System.out.println("Name: " + name);
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
		
		System.out.print(instanceClassName);
		System.out.print(instanceTypeName);
		
		
	}
	
	protected void sanitizeEGenericType(EGenericType egt)
	{
		System.out.println(egt.getERawType().getName());
		
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
		
//		System.out.println("eTypedElement isOrdered: " + isOrdered);
//		System.out.println("eTypedElement isUnique: " + isUnique);
//		System.out.println("eTypedElement isMany: " + isMany);
//		System.out.println("eTypedElement isRequired: " + isRequired);
//	
//		System.out.println("eTypedElement lowerBound: " + lowerBound);
//		System.out.println("eTypedElement upperBound: " + upperBound);
	}
	
	
	
	protected void sanitizeEStructuralFeature(EStructuralFeature esf)
	{	
		//System.out.println(esf.getName());
		boolean isOrdered = esf.isOrdered();
		boolean isUnique = esf.isUnique();
		boolean isMany = esf.isMany();
		boolean isRequired = esf.isRequired();
		boolean isDerived = esf.isDerived();
		
		String dvl = esf.getDefaultValueLiteral();
		
		int ID = esf.getFeatureID();
		

//		System.out.println("eStructuralFeature isOrdered: " + isOrdered + " \n");
//		System.out.println("eStructuralFeature isUnique: " + isUnique);
//		System.out.println("eStructuralFeature isMany: " + isMany);
//		System.out.println("eStructuralFeature isRequired: " + isRequired);
//		
//		System.out.println(dvl);
//		System.out.println(ID);

		
		
	}
	
	
	
	protected void sanitizeEDataType(EDataType edt)
	{
		boolean isSerializable = edt.isSerializable();
	}
	

	
	
	protected void sanitizeEAttribute(EAttribute ea)
	{
		
		boolean isID = ea.isID();
		
		
	}
	

	protected void sanitizeEReference(EReference er)
	{
		
		EReference eOpposite = er.getEOpposite();
		
		 boolean isContainment = er.isContainment();
		 boolean isContainer = er.isContainer();
		 boolean isResolveProxies = er.isResolveProxies();
		 System.out.println("isContainment: " + isContainment);
		 System.out.println("isContainer:" + isContainer);
		 System.out.println("isResolveProxies: " + isResolveProxies);
		 
	}
	
	



	protected void sanitizeEClass(EClass eClass)
	{
		boolean isAbstract = eClass.isAbstract();
		boolean isInterface = eClass.isInterface();
		

		System.out.print("\tisAbstract: " + isAbstract);

		
		System.out.println("\tisInterface: " + isInterface);
		
			
	}
	

	protected void sanitizeEEnumLiteral(EEnumLiteral eEnumLiteral)
	{
		String lit = eEnumLiteral.getLiteral();
		int value = eEnumLiteral.getValue();
		
		System.out.println(lit);
		System.out.println(value);
	}
}
