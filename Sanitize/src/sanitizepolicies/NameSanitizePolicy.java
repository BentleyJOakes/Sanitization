package sanitizepolicies;

import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NameSanitizePolicy extends SanitizePolicy {

	@Override
	protected void sanitizeRoot(EPackageImpl root) {
		System.out.println("NS_URI: " + root.getNsURI());
		root.setNsURI("BlahURI");
		
		System.out.println("NS_Prefix: " + root.getNsPrefix());
		root.setNsPrefix("BlahPrefix");
	}

	@Override
	protected void sanitizeEModelElement(EModelElement eme) {
		String packageDocumentation = EcoreUtil.getDocumentation(eme);
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
		String name = ene.getName();
		System.out.println("Name: " + name);
		ene.setName("Blah");
	}

	@Override
	protected void sanitizeEClassifier(EClassifier ec) {
		String instanceClassName = ec.getInstanceClassName();
		String instanceTypeName = ec.getInstanceTypeName();

	}

	@Override
	protected void sanitizeEGenericType(EGenericType egt) {
		System.out.println(egt.getERawType().getName());
	}

	@Override
	protected void sanitizeETypedElement(ETypedElement ete) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEStructuralFeature(EStructuralFeature esf) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEDataType(EDataType edt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEAttribute(EAttribute ea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEReference(EReference er) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEClass(EClass ec) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sanitizeEEnumLiteral(EEnumLiteral eEnumLiteral) {
		String lit = eEnumLiteral.getLiteral();
	}

}
