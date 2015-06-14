package sanitize.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.impl.EClassImpl;

public class InheritanceRemovalPolicy extends SanitizePolicy {

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}
	
	protected void sanitizeEClass(EClass ec)
	{
//		for (EGenericType eSuperType : ec.getEGenericSuperTypes())
//		{
//			
//		}
	}

}
