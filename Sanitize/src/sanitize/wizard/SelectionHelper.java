package sanitize.wizard;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class SelectionHelper implements SelectionListener
{
	TreeViewer one;
	TreeViewer two;
	
	public void setViewers(TreeViewer one, TreeViewer two)
	{
		this.one = one;
		this.two = two;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}
	
	public void widgetSelected(SelectionEvent e)
	{
		if( e.getSource().equals(one.getTree().getVerticalBar()) )
			onTreeVerticalScrollSelected(e, one, two);
		else if( e.getSource().equals(two.getTree().getVerticalBar()) )
			onTreeVerticalScrollSelected(e, two, one);	
	}
	 
	/* HANDLE TABLE SCROLL */
//	private void onTblVerticalScrollSelected(SelectionEvent e, TableViewer
//		target, TableViewer other)
//	{
//		other.getTable().setTopIndex(target.getTable().getTopIndex());	
//	}	
	 
	/* HANDLE TREE SCROLL */
	private void onTreeVerticalScrollSelected(SelectionEvent e, TreeViewer target,
		TreeViewer other)
	{
		
		TreeItem targetItem = target.getTree().getTopItem();				
		TreeItem otherItem = getOtherItem(targetItem, target, other);
		
		if( otherItem != null )
			other.getTree().setTopItem(otherItem);
	}
	
	private TreeItem getOtherItem(TreeItem targetItem, TreeViewer target, TreeViewer other)
	{	
		ArrayList targetVisibleItems = getAllVisibleItems(target);
		ArrayList otherVisibleItems = getAllVisibleItems(other);
		
		int targetIndex = targetVisibleItems.indexOf(targetItem);
		if( targetIndex != -1 && otherVisibleItems.size() > targetIndex )
			return (TreeItem)otherVisibleItems.get(targetIndex);
		
		return (TreeItem)otherVisibleItems.get(otherVisibleItems.size() - 1);
	}
	
	public ArrayList getAllVisibleItems(TreeViewer treeViewer)
	{
		ArrayList result = new ArrayList();
		getVisibleItems(result, treeViewer.getControl());
		return result;
	}
	
	private void getVisibleItems(ArrayList result, Widget widget)
	{
		TreeItem[] items = getChildren(widget);
	    
		for(int i=0; i<items.length; i++)
	    {
			TreeItem item = items[i];
			result.add(item);          
			if( item.getExpanded() )
				getVisibleItems(result, item);
	    }
	}
	
	private TreeItem[] getChildren(Widget o)
	{
		if( o instanceof TreeItem )
			return ((TreeItem)o).getItems();
		if( o instanceof Tree )
			return ((Tree)o).getItems();
		return null;
	}

	
	
}
