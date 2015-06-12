package sanitize.wizard;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.views.properties.IPropertySourceProvider;

import sanitize.handlers.SanitizerSaveLoadHandler;

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
	    
	    EMFReader reader = new EMFReader();
	    
	    
	    EList<EObject> previewModel = SanitizerSaveLoadHandler.loadFile("IFC4.ecore");
	    EList<EObject> previewModel2 = SanitizerSaveLoadHandler.loadFile("IFC4-san.ecore");
	    
	    System.out.println("LEngth: " + previewModel.size());
	    
	    TreeViewer treeViewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	    
	    //treeViewer.setContentProvider(reader);
	    //treeViewer.setLabelProvider(tree);
	    //treeViewer.setInput(tree);
	    
//	    ComposedAdapterFactory composedAdapterFactory = 
//		   new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
//		 
//		AdapterFactoryLabelProvider labelProvider = 
//		   new AdapterFactoryLabelProvider(composedAdapterFactory);
//		
//		AdapterFactoryContentProvider contentProvider = 

//		   new AdapterFactoryContentProvider(composedAdapterFactory);
		 
		treeViewer.setLabelProvider(reader);
		treeViewer.setContentProvider(reader);
		
		treeViewer.setInput(previewModel);
		
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		TreeViewer treeViewer2 = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer2.setLabelProvider(reader);
		treeViewer2.setContentProvider(reader);
		
		treeViewer2.setInput(previewModel2);
		
		treeViewer2.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		
		// Make selection the same in both tables
		
		SelectionHelper sh = new SelectionHelper();
		sh.setViewers(treeViewer, treeViewer2);
		treeViewer.getTree().getVerticalBar().addSelectionListener(sh);
		//treeViewer2.getTree().getVerticalBar().addSelectionListener(sh);
		
		
		
		//treeViewer.setInput(previewModel.get(0));
	    		
	    //EMFReader reader = new EMFReader();
	    
	    //treeViewer.setContentProvider(reader);
	   //treeViewer.setContentProvider(new AdapterFactoryContentProvider(reader));
	    //treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(reader));
	    
	    
//	    
//	    before = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
//	    before.setLayoutData(new GridData(GridData.FILL_BOTH));
//	    before.setText("Model Data");
//	    
//	    after = new StyledText(composite, SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
//	    after.setLayoutData(new GridData(GridData.FILL_BOTH));
//	    after.setText("Sanitized Data");
	    
	    
	    
	    
	    
	    setControl(composite);

	    setPageComplete(true);
	}
		

}
