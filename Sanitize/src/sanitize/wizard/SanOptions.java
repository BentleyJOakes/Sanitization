package sanitize.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SanOptions
{
	HashMap<String, TreeItem> analysisOptions = new HashMap<String, TreeItem>();
	HashMap<String, TreeItem> threatOptions = new HashMap<String, TreeItem>();

	public void addOption(String name, String desc, HashMap<String, TreeItem> map, Tree parent)
	{
		TreeItem newItem = new TreeItem(parent, SWT.NONE);
		newItem.setText(desc);
		map.put(name, newItem);
	}
	public void addOption(String name, String desc, HashMap<String, TreeItem> map, TreeItem parent)
	{
		TreeItem newItem = new TreeItem(parent, SWT.NONE);
		newItem.setText(desc);
		map.put(name, newItem);
	}
	
	public void addAnalysisOptions(Tree tree)
	{
		addOption("subtypes", "What is the inheritance hierarchy?", analysisOptions, tree);

		addOption("numClasses", "How many classes are in the metamodel?", analysisOptions, tree);

		addOption("attribOptions", "Attribute Options", analysisOptions, tree);

		addOption("numAttributes", "How many attributes are there in the model?", analysisOptions, analysisOptions.get("attribOptions"));

		addOption("typeAttributes", "What is the type of each attribute?", analysisOptions, analysisOptions.get("attribOptions"));

		addOption("nameClasses", "What are the names of each class?", analysisOptions, tree);

		
	}

	public void addThreatOptions(Tree tree) {
		addOption("industry", "What industry is this metamodel used in?", threatOptions, tree);
		
		addOption("typeAttributes", "What is the type of each attribute?", threatOptions, tree);
		
	}

	public boolean getConsis()
	{
		boolean a_subtypesChecked = analysisOptions.get("subtypes").getChecked();
		boolean a_numClassesChecked = analysisOptions.get("numClasses").getChecked();
		
		boolean a_attribOptionsChecked = analysisOptions.get("attribOptions").getChecked();
		boolean a_numAttributesChecked = analysisOptions.get("numAttributes").getChecked();
		
		boolean a_typeAttributesChecked = analysisOptions.get("typeAttributes").getChecked();
		boolean a_nameClassesChecked = analysisOptions.get("nameClasses").getChecked();
		
		
		boolean t_industryChecked = threatOptions.get("industry").getChecked();
		boolean t_typeAttributesChecked = threatOptions.get("typeAttributes").getChecked();
		
		if (t_typeAttributesChecked && a_typeAttributesChecked)
			return false;
		
		if (a_nameClassesChecked && t_industryChecked)
			return false;
		
		
		return true;
	}
}
