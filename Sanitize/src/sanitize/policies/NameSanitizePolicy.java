package sanitize.policies;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NameSanitizePolicy extends SanitizePolicy
{

	HashMap<String, String> nameMap = new HashMap<String, String>();
	
	
	
	boolean sanitizeAnnotations = false;
	
	boolean keepTypeAtEnd = true;
	boolean keepEnumAtEnd = true;
	
	boolean keepNullInEnums = true;
	
	@Override
	public void finish()
	{
		report.addMap("Name Mapping", nameMap);
	}
	
	@Override
	protected void sanitizeRoot(EPackageImpl root) {
		
		String oldRootName = root.getName();
		System.out.println("Root Name: " + oldRootName);
		String newRootName = "Blah";
		root.setName(newRootName);
		nameMap.put(oldRootName, newRootName);

		String oldNsURI = root.getNsURI();
		System.out.println("NS_URI: " + oldNsURI);
		String newNsURI = "BlahURI";
		root.setName(newNsURI);
		nameMap.put(oldNsURI, newNsURI);
		
		String oldNsPrefix = root.getNsPrefix();
		System.out.println("Root Name: " + oldNsPrefix);
		String newNsPrefix = "BlahPrefix";
		root.setName(newNsPrefix);
		nameMap.put(oldNsPrefix, newNsPrefix);
	}

	@Override
	protected void sanitizeEModelElement(EModelElement eme) {
		String packageDocumentation = EcoreUtil.getDocumentation(eme);
		
		if (packageDocumentation != null)
			System.out.println("packageDocumentation: " + packageDocumentation);
		
		EcoreUtil.setDocumentation(eme, "");
		
		List<String> constraints = EcoreUtil.getConstraints(eme);
		for (String s : constraints)
		{
			System.out.println("Constraint: " + s);
		}

	}

	@Override
	protected void sanitizeEAnnotation(EAnnotation ea) {
		String source = ea.getSource();
		System.out.println("Source: " + source);
		ea.setSource("BlahSource");
		
		EMap<String, String> details = ea.getDetails();
		for (String s : details.keySet())
		{
			System.out.println("Key: " + s);
		}
	}

	@Override
	protected void sanitizeENamedElement(ENamedElement ene) {
		System.out.println("****************");
		String name = ene.getName();
		System.out.println("Name: " + name);
		ene.setName("Blah");
		
		
		
		if (this.keepEnumAtEnd && ene instanceof EEnum)
		{
			if (! ene.getName().toLowerCase().endsWith("enum"))
				ene.setName(ene.getName() + "Enum");
		}
	}

	@Override
	protected void sanitizeEEnum(EEnum eEnum)
	{
		
	}
	
	@Override
	protected void sanitizeEEnumLiteral(EEnumLiteral eEnumLiteral) {
		String lit = eEnumLiteral.getLiteral();
		System.out.println("Lit: " + lit);
	}

}
