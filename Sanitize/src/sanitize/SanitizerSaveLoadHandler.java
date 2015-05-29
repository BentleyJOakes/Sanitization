package sanitize;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class SanitizerSaveLoadHandler
{
	static ResourceSet resourceSet;
	static String savePath;
	
	public static EList<EObject> loadFile(String path)
	{
		if (!path.endsWith(".ecore"))
		{
			System.out.println("Can't sanitize non-ecore file");
		}	
		
		//TODO: Make robust
		savePath = path.toString().replaceFirst(".ecore", "-san.ecore");
		
		// Create a resource set.
		resourceSet = new ResourceSetImpl();

		XMIResourceFactoryImpl xmiFactory= new XMIResourceFactoryImpl();
		EcoreResourceFactoryImpl ecoreFactory = new EcoreResourceFactoryImpl();
		
		
		// Register the default resource factory -- only needed for stand-alone!
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", ecoreFactory);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", xmiFactory);
		
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
			    "xml", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
			    "ecore", new EcoreResourceFactoryImpl());
		
		// Register the package -- only needed for stand-alone!
		EcorePackage ecorePackage = EcorePackage.eINSTANCE;

		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(path);

		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);

		return resource.getContents();
		
	}
	
	public static void saveFile(EList<EObject> neweObjects)
	{
	
		System.out.println("Save file: " + savePath);
		URI uri = URI.createFileURI(savePath);
		Resource newResource = resourceSet.createResource(uri);
		newResource.getContents().addAll(neweObjects);

		// Print the contents of the resource to System.out.
		try
		{
			newResource.save(Collections.EMPTY_MAP);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
				
	}
	
	
	
}
