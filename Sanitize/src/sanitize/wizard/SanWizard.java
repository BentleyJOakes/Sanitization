package sanitize.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


public class SanWizard extends Wizard {

	protected OptionsPage oPage;
	protected PreviewPage pPage;
	
	int nextPageCount = 0;
	
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

	    // Print the result to the console

	    System.out.println(oPage.getText1());



	    return true;

	  }

}
