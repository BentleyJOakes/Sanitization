package sanitize.reports;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Report
{
	String report = "Report:\n";
	
	public String toString()
	{
		return report;
	}

	public void addMap(String mapName, HashMap<String, String> nameMap)
	{
		Iterator<Entry<String, String>> it = nameMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        report += pair.getKey() + " => " + pair.getValue() + "\n";
	    }
	}
}
