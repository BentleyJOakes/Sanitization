package sanitize.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import sanitizepolicies.PrintSanitizePolicy;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SanitizerMain extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SanitizerMain() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		    if (selection != null && selection instanceof IStructuredSelection) {
		      IStructuredSelection strucSelection = (IStructuredSelection) selection;
		      for (Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
		        Object element = iterator.next();

		        
		        IFile i = (IFile) element;
		        
		        IPath path = i.getLocation();

		        System.out.println("ECore file: " + path.toString());

		        EList<EObject> eObjects = SanitizerSaveLoadHandler.loadFile(path.toString());
		        
		        PrintSanitizePolicy printPolicy = new PrintSanitizePolicy();
		        EList<EObject> neweObjects = printPolicy.sanitize(eObjects);
		        
		        
		        SanitizerSaveLoadHandler.saveFile(neweObjects);
		        
		      }
		    }
//		MessageDialog.openInformation(
//				window.getShell(),
//				"Sanitize",
//				"Hello, Eclipse world");
		return null;
	}
	
	public static void main(String[] args)
	{
		 EList<EObject> eObjects = SanitizerSaveLoadHandler.loadFile("ifc2x3.ecore");
	        
		 PrintSanitizePolicy printPolicy = new PrintSanitizePolicy();
	     EList<EObject> neweObjects = printPolicy.sanitize(eObjects);
	        
	     
	     SanitizerSaveLoadHandler.saveFile(neweObjects);
	}
}
