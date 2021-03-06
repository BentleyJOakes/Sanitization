package sanitize.wizard;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl;
import org.eclipse.emf.ecore.impl.EModelElementImpl;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EParameterImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

public class EMFReader extends LabelProvider implements ITreeContentProvider 
{
	
	String NsPrefix = "NSPREFIX_";
	String NsUri = "NSURI_";

//	EPackageImpl root = (EPackageImpl) eobjects.get(0);
//	
//	
//	
//	Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
//	if (eClasses.size() > 0)
//	{
//		for (EClass ec : eClasses)
//		{
//			sanitizeEClassStructure(ec);
//		}
//	}
//	
//	
//	Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
//	if (eEnums.size() > 0)
//	{
//		for (EEnum ee : eEnums)
//		{
//			sanitizeEEnumStructure(ee);
//		}
//	}
//		
//	
//	Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
//	eDataTypes.removeAll(eEnums);
//	
//	
//	for (EDataType eDataType : eDataTypes)
//	{
//		sanitizeEDataTypeStructure(eDataType);
//		
//	}
	//ContentProvider
	@Override
    public Object[] getElements(Object inputElement) {
      if (inputElement instanceof EList<?>)
      {
    	  
    	  System.out.println("Returning epackageImpl");
    	  Object[] objArray = new Object[1];
    	  EList list = (EList) inputElement;
        objArray[0] = (EPackageImpl) list.get(0);
        return objArray;
      }
      else
      {
    	  System.out.println("Returning nothing");
        return new Object[0];
    
      }
	}
	
	//Queried to know if the current node has children
    @Override
    public boolean hasChildren(Object element) {
      if (element instanceof EPackageImpl)
      {
    	  //has namespace stuff
    	  return true;
    	  
//    	  EPackageImpl 
//    	  Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
//    	  Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
//    	  Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
//    	  
//    	  if (eClasses.size() > 0 || eEnums.size() > 0 || eDataTypes.size() > 0)
//    		  return true;
  			
      }
      
      if (element instanceof EModelElementImpl)
      {
    	  EModelElementImpl eme = (EModelElementImpl) element;
    	  
    	  if (eme.getEAnnotations().size() > 0)
    		  return true;
      }
      
      if (element instanceof EClassifierImpl)
      {
    	  //has etypeparameters
    	  return true;
      }
      
    	  
	  if (element instanceof EReferenceImpl)
	  {
		  EReferenceImpl er = (EReferenceImpl) element;
    	  
    	  if (er.getEKeys().size() > 0)
    		  return true;
		  
	  
	  }
	  
	  if (element instanceof EOperationImpl)
	  {
		  return true;
	  }
	  
	  
	  if (element instanceof EClassImpl)
	  {
		  EClassImpl ec = (EClassImpl) element;
		  
		  if (ec.getEGenericSuperTypes().size() > 0 || ec.getEAttributes().size() > 0 || 
				  ec.getEReferences().size() > 0 || ec.getEOperations().size() > 0)
			{
				return true;
				
			}
			
	  }
	  if (element instanceof EEnumImpl)
	  {
		  EEnumImpl ee = (EEnumImpl) element;
		  
		  if (ee.getELiterals().size() > 0)
			  return true;
	  }
	  
	  if (element instanceof EAnnotationImpl)
	  {
		  return true;
	  }
    	  
      //System.out.println("No Children for element: " + element.getClass().getName());
      return false;
    }
    
  //Queried to load the children of a given node
    @Override
    public Object[] getChildren(Object parentElement) {
    	
    	ArrayList<Object> list = new ArrayList<Object>();
    	//System.out.println("Get Children for element: " + parentElement.getClass().getName());
    	if (parentElement instanceof EPackageImpl)
        {
    		EPackageImpl root = (EPackageImpl) parentElement;
    		
    		
    		
    		list.add(NsPrefix + root.getNsPrefix());
    		list.add(NsUri + root.getNsURI());
    		
    		Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
    		Collection<EClass> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
    		Collection<EClass> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
    		
    		eDataTypes.removeAll(eEnums);
    		
    		list.addAll(eClasses);
    		list.addAll(eEnums);
    		list.addAll(eDataTypes);
      	  
    			
        }
        
        if (parentElement instanceof EModelElementImpl)
        {
        	EModelElementImpl eme = (EModelElementImpl) parentElement;
      	  
      	  list.addAll(eme.getEAnnotations());
        }
        
        if (parentElement instanceof EClassifierImpl)
        {
        	EClassifierImpl ec = (EClassifierImpl) parentElement;
      	  
  		
  		list.addAll(ec.getETypeParameters());
  		
        }
        
      	  
  	  if (parentElement instanceof EReferenceImpl)
  	  {
  		EReferenceImpl er = (EReferenceImpl) parentElement;
      	  
      	  list.addAll(er.getEKeys());
  		  
  	  
  	  }
  	  
  	  if (parentElement instanceof EOperationImpl)
  	  {
  		  
  		EOperationImpl eo = (EOperationImpl) parentElement;
  		
  		list.addAll(eo.getEParameters());
  		list.addAll(eo.getETypeParameters());
  		list.addAll(eo.getEExceptions());
  		
  	  }
  	  
  	  
  	  if (parentElement instanceof EClassImpl)
  	  {
  		  //System.out.println("This is a EClassImpl");
  		EClassImpl ec = (EClassImpl) parentElement;
  		
  		
  		
  		EList<EGenericType> supertypes = ec.getEGenericSuperTypes();
  		if (supertypes.size() > 0)
  		{
	  		String superTypesString = "Supertypes: ";
	  		for (EGenericType egt : supertypes)
	  		{
	  			superTypesString += egt.getERawType().getName() + ", ";
	  		}
	  		  
	  		list.add(superTypesString.substring(0, superTypesString.length()-2));
  		}
  		list.addAll(ec.getEAttributes());
  		list.addAll(ec.getEReferences());
  		list.addAll(ec.getEOperations());
  		
  			
  	  }
  	  if (parentElement instanceof EEnumImpl)
  	  {
  		  EEnumImpl ee = (EEnumImpl) parentElement;
  		  
  		  list.addAll(ee.getELiterals());
  	  }
  	  
  	  if (parentElement instanceof EAnnotationImpl)
  	  {
  		  EAnnotationImpl ea = (EAnnotationImpl) parentElement;
  		  list.add("Source: " + ea.getSource());
  	  }
  	  
  		return list.toArray();
    }
    
    
  //LabelProvider
  	 @Override
  	   public String getText(Object element) {
  		 
  		 String type = element.getClass().getSimpleName();
  		 type = type.replace("Impl", "");
  		 type = type.substring(1);
  		 
  		 String returnString = type + ": " + element.toString();
  		 
  		 
  		 if (element instanceof ENamedElementImpl)
  		 {
  			 ENamedElement ene = (ENamedElement) element;
  			 returnString = type + ": " + ene.getName();
  		 }
  		 
  		 else if (element instanceof String)
  		 {
  			 String s = (String) element;
  			 if (s.startsWith(NsPrefix))
  			 {
  				 s = s.replace(NsPrefix, "");
  				 return "NsPrefix: " + s;
  			 }
  			if (s.startsWith(NsUri))
 			 {
 				 s = s.replace(NsUri, "");
 				 return "NsURI: " + s;
 			 }
  			
  			return s;
  				 
  		 }
  		 
  		 if (element instanceof EClassImpl)
  		 {
  			 EClassImpl ec = (EClassImpl) element;
  			
  	  		if (ec.isInterface())
  	  			returnString += " (Interface)";
  	  		else if (ec.isAbstract())
  	  			returnString += " (Abstract)";
  		 }
  		 else if (element instanceof EAttributeImpl)
  		 {
  			 EAttributeImpl ea = (EAttributeImpl) element;
  			EDataType edt = ea.getEAttributeType();
  			returnString = "Attribute: " + edt.getName() + " " + ea.getName();
  		 }
  		else if (element instanceof EReferenceImpl)
 		 {
  			EReferenceImpl er = (EReferenceImpl) element;
 			EClass ec = er.basicGetEReferenceType();
 			returnString = "Reference: " + ec.getName() + " " + er.getName();
 		 }
  		 
  		else if (element instanceof EOperationImpl)
		 {
  			EOperationImpl eo = (EOperationImpl) element;
			EClassifier ec = eo.getEType();
			returnString = "Operation: " + ec.getName() + " " + eo.getName();
		 }
  		else if (element instanceof EParameterImpl)
		 {
  			EParameterImpl ep = (EParameterImpl) element;
			EClassifier ec = ep.getEType();
			returnString = "Parameter: " + ec.getName() + " " + ep.getName();
		 }
  		 
  		else if (element instanceof EEnumLiteralImpl)
  		{
  			EEnumLiteralImpl eel = (EEnumLiteralImpl) element;
  			returnString += " = " + eel.getValue();
  		}

  		else if (element instanceof EAnnotationImpl)
  		{
  			returnString = "Annotation: ";
  		}
  		 
  		 return returnString;
  	 }
	
	
	@Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object getParent(Object element) {
      return null;
    }
    
    
	
	


}
