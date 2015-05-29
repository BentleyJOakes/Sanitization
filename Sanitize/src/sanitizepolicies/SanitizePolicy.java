package sanitizepolicies;

import java.util.Collection;
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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.EPackageImpl;

public abstract class SanitizePolicy
{
	public abstract EList<EObject> sanitize(EList<EObject> eobjects);
	public abstract void sanitizeRoot(EPackageImpl root);

	public abstract  void sanitizeClasses(Collection<EClass> eClasses);


	public abstract  void sanitizeEnums(Collection<EEnum> eEnums);

	public abstract  void sanitizeEModelElement(EModelElement eme);



	public abstract  void sanitizeENamedElement(ENamedElement ene);


	public abstract void sanitizeEClassifier(EClassifier ec);


	public abstract void sanitizeEGenericType(EGenericType egt);


	public abstract void  sanitizeETypedElement(ETypedElement ete);


	public abstract void  sanitizeEStructuralFeature(EStructuralFeature esf);


	public abstract void  sanitizeEDataType(EDataType edt);



	public abstract void  sanitizeEAttribute(EAttribute ea);



	public abstract void sanitizeEReference(EReference er);



}
