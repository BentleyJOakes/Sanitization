package sanitize.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
public class PreviewPage extends WizardPage
{
	private Composite composite;
	
	private StyledText before;
	private StyledText after;
      
	protected PreviewPage()
	{
		super("Preview Page");

	    setTitle("Preview Page");

	    setDescription("Now this is the preview page");

	}
	
	public void createControl(Composite parent)
	{
		composite = new Composite(parent, SWT.NONE);
	    
	    GridLayout layout = new GridLayout();

	    layout.makeColumnsEqualWidth = true;
	    layout.numColumns = 2;
	    composite.setLayout(layout);
	    
	    
	    before = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
	    before.setLayoutData(new GridData(GridData.FILL_BOTH));
	    before.setText("Model Data");
	    
	    after = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
	    after.setLayoutData(new GridData(GridData.FILL_BOTH));
	    after.setText("Sanitized Data");
	    
	    
	    
	    
	    
	    setControl(composite);

	    setPageComplete(true);
	}
		

}
