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
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.impl.EModelElementImpl;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

public class EMFReader extends LabelProvider implements ITreeContentProvider 
{

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
    	  
      System.out.println("No Children for element: " + element.getClass().getName());
      return false;
    }
    
  //Queried to load the children of a given node
    @Override
    public Object[] getChildren(Object parentElement) {
    	
    	ArrayList<Object> list = new ArrayList<Object>();
    	System.out.println("Get Children for element: " + parentElement.getClass().getName());
    	if (parentElement instanceof EPackageImpl)
        {
    		EPackageImpl root = (EPackageImpl) parentElement;
    		
    		
    		
    		list.add(root.getNsPrefix());
    		list.add(root.getNsURI());
    		
    		list.addAll(EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS));
    		list.addAll(EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM));
    		list.addAll(EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE));
      	  
    		
//      	  EPackageImpl 
//      	  Collection<EClass> eClasses = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.ECLASS);
//      	  Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EENUM);
//      	  Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(root.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE);
//      	  
//      	  if (eClasses.size() > 0 || eEnums.size() > 0 || eDataTypes.size() > 0)
//      		  return true;
    			
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
  		  System.out.println("This is a EClassImpl");
  		EClassImpl ec = (EClassImpl) parentElement;
  		  
  		  list.addAll(ec.getEGenericSuperTypes());
  		list.addAll(ec.getEAttributes());
  		list.addAll(ec.getEReferences());
  		list.addAll(ec.getEOperations());
  		
  			
  	  }
  	  if (parentElement instanceof EEnumImpl)
  	  {
  		  EEnumImpl ee = (EEnumImpl) parentElement;
  		  
  		  list.addAll(ee.getELiterals());
  	  }
  	  
  	  //System.out.println("Returning no Children for element: " + parentElement.getClass().getName());
  		return list.toArray();
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
    
    
	//LabelProvider
	 @Override
	   public String getText(Object element) {
		 
		 if (element instanceof ENamedElementImpl)
		 {
			 ENamedElement ene = (ENamedElement) element;
			 return ene.getName();
		 }
		 
		 return element.toString();
	 }
	


}
