package sanitize.wizard;

import javax.swing.text.StyleConstants.FontConstants;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.KeyEvent;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;


public class OptionsPage extends WizardPage implements Listener {

	private Text text1;

	  private Composite composite;
	  
	  private Label consis;
	  
	  public SanOptions sanOptions;

	  
	protected OptionsPage() {
		super("Options Page");

	    setTitle("Options Page");

	    setDescription("Now this is the options page");

	    setControl(text1);

	}

	static void checkPath(TreeItem item, boolean checked, boolean grayed) {
	    if (item == null) return;
	    if (grayed) {
	        checked = true;
	    } else {
	        int index = 0;
	        TreeItem[] items = item.getItems();
	        while (index < items.length) {
	            TreeItem child = items[index];
	            if (child.getGrayed() || checked != child.getChecked()) {
	                checked = grayed = true;
	                break;
	            }
	            index++;
	        }
	    }
	    item.setChecked(checked);
	    item.setGrayed(grayed);
	    checkPath(item.getParentItem(), checked, grayed);
	}

	static void checkItems(TreeItem item, boolean checked) {
	    item.setGrayed(false);
	    item.setChecked(checked);
	    TreeItem[] items = item.getItems();
	    for (int i = 0; i < items.length; i++) {
	        checkItems(items[i], checked);
	    }
	}
	
	@Override

	  public void createControl(Composite parent) {

//	    container = new Composite(parent, SWT.NONE);
//
//	    GridLayout layout = new GridLayout();
//
//	    container.setLayout(layout);
//
//	    layout.numColumns = 2;
//
//	    Label label1 = new Label(container, SWT.NONE);
//
//	    label1.setText("Say hello to Fred");
//
//
//
//	    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
//
//	    text1.setText("");
//
//	    text1.addKeyListener(new KeyListener() {
//
//
//
//	      @Override
//
//	      public void keyPressed(KeyEvent e) {
//
//	        // TODO Auto-generated method stub
//
//	      }
//
//
//
//	      @Override
//
//	      public void keyReleased(KeyEvent e) {
//
//	        if (!text1.getText().isEmpty()) {
//
//	          setPageComplete(true);
//
//	        }
//
//	      }
//
//
//
//	    });
//
//	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//
//	    text1.setLayoutData(gd);
//
//	    Label labelCheck = new Label(container, SWT.NONE);
//
//	    labelCheck.setText("This is a check");
//
//	    Button check = new Button(container, SWT.CHECK);
//
//	    check.setSelection(true);
//
		
		composite = new Composite(parent, SWT.NONE);
	    GridLayout layout = new GridLayout();

	    layout.makeColumnsEqualWidth = true;
	    layout.numColumns = 2;
	    composite.setLayout(layout);
	    
		
	    Label analysis = new Label(composite, SWT.NONE);
	    analysis.setText("Analysis Statements:");
	    
	    Label threat = new Label(composite, SWT.NONE);
	    threat.setText("Threat Statements:");
	    
	    Listener treeListener = new Listener() {
	        @Override
			public void handleEvent(Event event) {
	            if (event.detail == SWT.CHECK) {
	                TreeItem item = (TreeItem) event.item;
	                boolean checked = item.getChecked();
	                checkItems(item, checked);
	                checkPath(item.getParentItem(), checked, false);
	            }
	            
	        }
	    };
	    
		Tree tree = new Tree(composite, SWT.BORDER | SWT.CHECK);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		
	    tree.addListener(SWT.Selection, treeListener);
	    tree.addListener(SWT.Selection, this);
	    
	    
	    sanOptions = new SanOptions();
	    
	    sanOptions.addAnalysisOptions(tree);
	    
	   
	    
	    Tree tree2 = new Tree(composite, SWT.BORDER | SWT.CHECK);
		tree2.setLayoutData(new GridData(GridData.FILL_BOTH));
		
	    tree2.addListener(SWT.Selection, treeListener);
	    tree2.addListener(SWT.Selection, this);
	    
	    sanOptions.addThreatOptions(tree2);
	    
	    consis = new Label(composite, SWT.NONE);
	    setConsisText(true);
	    consis.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
//		Rectangle clientArea = shell.getClientArea();
//	    tree.setBounds(clientArea.x, clientArea.y, 200, 200);
//	    shell.pack();
//	    shell.open();
//	    while (!shell.isDisposed()) {
//	        if (!display.readAndDispatch()) display.sleep();
//	    }
//	    display.dispose();
	    
	    // required to avoid an error in the system

	    setControl(composite);

	    setPageComplete(true);
		
//		protected Control createContents(Composite parent) {
//		    Composite composite = new Composite(parent, SWT.NONE);
//		    composite.setLayout(new GridLayout(1, false));
//
//		    // Add a checkbox to toggle whether the labels preserve case
//		    Button preserveCase = new Button(composite, SWT.CHECK);
//		    preserveCase.setText("&Preserve case");
//
//		    // Create the tree viewer to display the file tree
//		    final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite);
//		    tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
//		    tv.setContentProvider(new FileTreeContentProvider());
//		    tv.setLabelProvider(new FileTreeLabelProvider());
//		    tv.setInput("root"); // pass a non-null that will be ignored
//
//		    // When user checks the checkbox, toggle the preserve case attribute
//		    // of the label provider
//		    preserveCase.addSelectionListener(new SelectionAdapter() {
//		      public void widgetSelected(SelectionEvent event) {
//		        boolean preserveCase = ((Button) event.widget).getSelection();
//		        FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv
//		            .getLabelProvider();
//		        ftlp.setPreserveCase(preserveCase);
//		      }
//		    });
//
//		    // When user checks a checkbox in the tree, check all its children
//		    tv.addCheckStateListener(new ICheckStateListener() {
//		      public void checkStateChanged(CheckStateChangedEvent event) {
//		        // If the item is checked . . .
//		        if (event.getChecked()) {
//		          // . . . check all its children
//		          tv.setSubtreeChecked(event.getElement(), true);
//		        }
//		      }
//		    });
//		    return composite;
		  }

	private void setConsisText(boolean isConsis)
	{
		consis.setText("Statements are consistent: " + isConsis);
		
		if (isConsis)
			consis.setForeground(new Color(null, 0,0,0));
		else
			consis.setForeground(new Color(null, 200,0,0));
	    
	}
	
	@Override
	public void handleEvent(Event event) {
        if (event.detail == SWT.CHECK) {
        	boolean isConsis = sanOptions.getConsis();
            setConsisText(isConsis);
            setPageComplete(isConsis);
        }
    }

	  public String getText1() {

	    return text1.getText();

	  }


}
