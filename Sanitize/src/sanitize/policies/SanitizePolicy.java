package sanitize.policies;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
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

public abstract class SanitizePolicy
{
	public EList<EObject> sanitize(EList<EObject> eobjects)
	{
		System.out.println("Start sanitization");
		
		
		//TODO: Don't assume heirarchical
		
		EPackageImpl root = (EPackageImpl) eobjects.get(0);
		
		sanitizeRoot(root);
		
		
		Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
		if (eClasses.size() > 0)
		{
			for (EClass ec : eClasses)
			{
				sanitizeEClassStructure(ec);
			}
		}
		
		
		Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
		if (eEnums.size() > 0)
		{
			for (EEnum ee : eEnums)
			{
				sanitizeEEnumStructure(ee);
			}
		}
			
		
		Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
		eDataTypes.removeAll(eEnums);
		
		
		for (EDataType eDataType : eDataTypes)
		{
			sanitizeEDataTypeStructure(eDataType);
			
		}
		
		
		return eobjects;
	
	}
	
	//=========
	protected void sanitizeRoot(EPackageImpl root){}

	protected void sanitizeRootStructure(EPackageImpl root)
	{
		sanitizeEModelElementStructure(root);
		sanitizeRoot(root);
	}
	//=========

	
	protected void sanitizeEModelElement(EModelElement eme){}

	protected void sanitizeEModelElementStructure(EModelElement eme)
	{
		for (EAnnotation eAnnotation : eme.getEAnnotations())
		{
			sanitizeEAnnotation(eAnnotation);
		}
		
		sanitizeEModelElement(eme);
	}
	
	//=========

	protected void sanitizeEAnnotation(EAnnotation ea){}
	
	//=========
	protected void sanitizeENamedElement(ENamedElement ene){}

	protected void sanitizeENamedElementStructure(ENamedElement ene)
	{
		sanitizeENamedElement(ene);
		sanitizeEModelElementStructure(ene);
		
	}
	//=========

	protected void sanitizeEClassifier(EClassifier ec){}

	protected void sanitizeEClassifierStructure(EClassifier ec)
	{
		sanitizeENamedElementStructure(ec);
		sanitizeEClassifier(ec);
		
		for (ETypeParameter etp : ec.getETypeParameters())
		{
			for (EGenericType egt : etp.getEBounds())
			{
				sanitizeEGenericType(egt);
			}

		}
		
	}

	//=========
	protected void sanitizeEGenericType(EGenericType egt){}

	//=========
	
	protected void sanitizeETypedElement(ETypedElement ete){}
	protected void sanitizeETypedElementStructure(ETypedElement ete)
	{
		sanitizeENamedElementStructure(ete);
		sanitizeETypedElement(ete);
	}
	//=========

	protected void sanitizeEStructuralFeature(EStructuralFeature esf){}
	protected void sanitizeEStructuralFeatureStructure(EStructuralFeature esf)
	{
		sanitizeETypedElementStructure(esf);
		sanitizeEStructuralFeature(esf);
	}
	//=========

	protected void sanitizeEDataType(EDataType edt){}
	protected void sanitizeEDataTypeStructure(EDataType edt)
	{
		sanitizeEClassifierStructure(edt);
		sanitizeEDataType(edt);
	}

	//=========
	
	protected void sanitizeEAttribute(EAttribute ea){}

	protected void sanitizeEAttributeStructure(EAttribute ea)
	{
		sanitizeEAttribute(ea);
		sanitizeEStructuralFeatureStructure(ea);
		
	}
	
	//=========


	protected void sanitizeEReference(EReference er){}
	protected void sanitizeEReferenceStructure(EReference er)
	{
		sanitizeEReference(er);
		sanitizeEStructuralFeatureStructure(er);
		
		
		for (Iterator<EAttribute> i = er.getEKeys().iterator(); i.hasNext();)
		  {
		    EAttribute attribute = i.next();
		    sanitizeEAttributeStructure(attribute);
		    
		  }
		
		
	}
	
	//=========

	protected void sanitizeEClass(EClass ec){}
	protected void sanitizeEClassStructure(EClass eClass)
	{
		sanitizeEClass(eClass);
		sanitizeEClassifierStructure(eClass);
		
		
		if (eClass.getEGenericSuperTypes().size() > 0)
		{
			for (EGenericType eSuperType : eClass.getEGenericSuperTypes())
			{
				sanitizeEGenericType(eSuperType);
			}
		}
		
		if (eClass.getEAttributes().size() > 0)
		{
			for (EAttribute eAttribute : eClass.getEAttributes())
			{
				sanitizeEAttributeStructure(eAttribute);
			}
		}
		
		if (eClass.getEReferences().size() > 0)
		{
			for (EReference eReference : eClass.getEReferences())
			{
				sanitizeEReferenceStructure(eReference);
			}
		}
		
		if (eClass.getEOperations().size() > 0)
		{
			for (EOperation eOperation : eClass.getEOperations())
			{
				
				sanitizeETypedElementStructure(eOperation);
				
				for (EParameter ep : eOperation.getEParameters())
				{
					sanitizeETypedElementStructure(ep);
				}
				
				for (ETypeParameter etp : eOperation.getETypeParameters())
				{
					sanitizeENamedElementStructure(etp);
				}
				
				for (EClassifier ee : eOperation.getEExceptions())
				{
					sanitizeEClassifierStructure(ee);
				}
			}
		}
	}
	

	//=========
	
	protected void sanitizeEEnum(EEnum eEnum){}
	
	protected void sanitizeEEnumStructure(EEnum eEnum)
	{
		sanitizeEEnum(eEnum);
		sanitizeEDataTypeStructure(eEnum);
		
		
		for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
		{
			
			sanitizeEEnumLiteral(eEnumLiteral);
		}
		
	}

	
	
	//=========
	
	protected void sanitizeEEnumLiteral(EEnumLiteral eEnumLiteral){}
	
}
