package sanitize;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class Sanitizer
{
	public static void loadFile(String path)
	{
		if (!path.endsWith(".ecore"))
		{
			System.out.println("Can't sanitize non-ecore file");
		}	
		
		
		// Create a resource set.
		ResourceSet resourceSet = new ResourceSetImpl();

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
		URI fileURI = URI.createFileURI(path.toString());

		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);

		// Print the contents of the resource to System.out.
		try
		{
			resource.save(System.out, Collections.EMPTY_MAP);
		}
		catch (IOException e) {} 
				
		
		
		
	}
}
