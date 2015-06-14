package sanitize.wizard;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import sanitize.handlers.SanitizerSaveLoadHandler;
import sanitize.policies.PrintSanitizePolicy;
import sanitize.policies.SanitizationPolicyComposer;


public class SanWizard extends Wizard {

	protected OptionsPage oPage;
	protected PreviewPage pPage;
	
	int nextPageCount = 0;
	
	public String modelFileName = null;
	
	public SanWizard() {

	    super();

	    setNeedsProgressMonitor(true);

	  }



	  @Override

	  public String getWindowTitle() {

	    return "Sanitization";

	  }



	  @Override

	  public void addPages() {

		  oPage = new OptionsPage();
		  pPage = new PreviewPage(oPage);

	    addPage(oPage);
	    addPage(pPage);

	  }

	  @Override
	  public IWizardPage getNextPage(IWizardPage currentPage) {
	      
	      if (currentPage == oPage)
	      {  
	          return pPage;
	      }
	      else if (currentPage == pPage)
	      {
	    	  //System.out.println("Start sanitizing?");
    		  pPage.setOptions();
    		  pPage.sanitize();
	      }
	      return null;
	  } 



	  @Override

	  public boolean performFinish()
	  {

		  if (modelFileName == null)
		  {
			  System.out.println("Error: No model selected!");
		  }
		  
		  EList<EObject> eObjects = SanitizerSaveLoadHandler.loadFile(modelFileName);
	      
		  SanitizationPolicyComposer spc = new SanitizationPolicyComposer(oPage.sanOptions.analysisOptions, oPage.sanOptions.threatOptions);
		  
	        spc.sanitizeModel(eObjects);
	        
	        
	        SanitizerSaveLoadHandler.saveFile(eObjects);


	    return true;

	  }

}
