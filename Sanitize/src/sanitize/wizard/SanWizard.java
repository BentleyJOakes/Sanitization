package sanitize.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;


public class SanWizard extends Wizard {

	protected OptionsPage oPage;
	protected PreviewPage pPage;
	
	public SanWizard() {

	    super();

	    setNeedsProgressMonitor(true);

	  }



	  @Override

	  public String getWindowTitle() {

	    return "Export My Data";

	  }



	  @Override

	  public void addPages() {

		  oPage = new OptionsPage();
		  pPage = new PreviewPage();

	    addPage(oPage);
	    addPage(pPage);

	  }

	  @Override
	  public IWizardPage getNextPage(IWizardPage currentPage) {
	      
	      if (currentPage == oPage) {
	          return pPage;
	      }
	      return null;
	  } 



	  @Override

	  public boolean performFinish() {

	    // Print the result to the console

	    System.out.println(oPage.getText1());



	    return true;

	  }

}
