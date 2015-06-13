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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.views.properties.IPropertySourceProvider;

import sanitize.handlers.SanitizerSaveLoadHandler;
import sanitize.policies.SanitizationPolicyComposer;

public class PreviewPage extends WizardPage
{
	private Composite composite;
	
	private OptionsPage oPage;
	
	private SanitizationPolicyComposer spc;
	
	
	//the treeviewers for the panes
	TreeViewer originalModel;
	TreeViewer sanModel;
      
	protected PreviewPage(OptionsPage oPage)
	{
		super("Preview Page");

	    setTitle("Preview Page");

	    setDescription("Now this is the preview page");

	    this.oPage = oPage;
	  
	}
	
	
	public void createControl(Composite parent)
	{
		composite = new Composite(parent, SWT.NONE);
	    
	    GridLayout layout = new GridLayout();

	    layout.makeColumnsEqualWidth = true;
	    layout.numColumns = 2;
	    composite.setLayout(layout);
	    
	    EMFReader reader = new EMFReader();
	    
	    
	    EList<EObject> previewModel = SanitizerSaveLoadHandler.loadFile("sample.ecore");
	    //EList<EObject> previewModel2 = SanitizerSaveLoadHandler.loadFile("IFC4-san.ecore");
	    
	    
	    originalModel = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	    
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
		 
		originalModel.setLabelProvider(reader);
		originalModel.setContentProvider(reader);
		
		originalModel.setInput(previewModel);
		
		originalModel.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		originalModel.expandAll();
		
		sanModel = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		sanModel.setLabelProvider(reader);
		sanModel.setContentProvider(reader);
		
		
		
		sanModel.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		
		// Make selection the same in both tables
		
		SelectionHelper sh = new SelectionHelper();
		sh.setViewers(originalModel, sanModel);
		originalModel.getTree().getVerticalBar().addSelectionListener(sh);
		//treeViewer2.getTree().getVerticalBar().addSelectionListener(sh);
		
	
	    
	    
	    
	    
	    setControl(composite);

	    setPageComplete(true);
	}


	public void setOptions()
	{
		System.out.println("Setting options for preview page");
		
		spc = new SanitizationPolicyComposer(oPage.sanOptions.analysisOptions, oPage.sanOptions.threatOptions);
	}


	public void sanitize()
	{
		System.out.println("Sanitizing for preview page");
		
		EList<EObject> previewModel = SanitizerSaveLoadHandler.loadFile("sample.ecore");
		
		spc.sanitizeModel(previewModel);
		
		sanModel.setInput(previewModel);
		sanModel.expandAll();
	}
		

}
