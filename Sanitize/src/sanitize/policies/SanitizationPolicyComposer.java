package sanitize.policies;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.TreeItem;

public class SanitizationPolicyComposer
{

	ArrayList<SanitizePolicy> sanPolicyList;
	
	public SanitizationPolicyComposer(HashMap<String, TreeItem> analysisOptions,
			HashMap<String, TreeItem> threatOptions)
	{
		sanPolicyList = new ArrayList<SanitizePolicy>();
		
		if (threatOptions.get("industry").getChecked())
		{
			NameSanitizePolicy namePolicy = new NameSanitizePolicy();
			sanPolicyList.add(namePolicy);
		}
		if (threatOptions.get("subtypes").getChecked())
		{
			InheritanceRemovalPolicy inheritancePolicy = new InheritanceRemovalPolicy();
			sanPolicyList.add(inheritancePolicy);
		}
		if (threatOptions.get("typeAttributes").getChecked())
		{
			AttributeTypeRemovePolicy attribTypePolicy = new AttributeTypeRemovePolicy();
			sanPolicyList.add(attribTypePolicy);
		}
	}

	public void sanitizeModel(EList<EObject> previewModel)
	{
		System.out.println("Starting to sanitize...");
		for (SanitizePolicy sp : sanPolicyList)	
		{
			sp.sanitize(previewModel);
		}
	}

}
