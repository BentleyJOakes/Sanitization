package sanitize.policies;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.impl.EGenericTypeImpl;

public class AttributeTypeRemovePolicy extends SanitizePolicy {

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}
	
	protected void sanitizeEAttribute(EAttribute ea)
	{
		//ea.setEGenericType(new String());
		
	}


}
