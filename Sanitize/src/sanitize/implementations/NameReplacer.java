package sanitize.implementations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class NameReplacer
{
	ArrayList<String> candidateWords = null;
	
	HashMap<String, String> wordMapping;
	boolean retainMapping;
	
	public NameReplacer(String wordFile, boolean retainMapping)
	{
		
		if (retainMapping)
		{
			wordMapping = new HashMap<String, String>();
			this.retainMapping = retainMapping;
		}
		
		HashSet<String> wordSet = new HashSet<String>();
		try
		{
			FileReader f = new FileReader(wordFile);
			BufferedReader br = new BufferedReader(f);
			
			String input = br.readLine();
			
			while (input != null)
			{
				input = input.trim().toLowerCase();
				input = input.replaceAll("[^a-zA-Z]", "");
				
				//System.out.println(input);
				
				if (input.length() >= 3)
				{
					//capitalize
					input = Character.toUpperCase(input.charAt(0)) + input.substring(1);
					wordSet.add(input);
				}
				
				input = br.readLine();
			}
			
			candidateWords = new ArrayList<String>(wordSet);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("The file: " + wordFile + " was not found");
		}
		catch (IOException e)
		{
			System.out.println("An error occured with the file: " + wordFile);
		}
		
	}
	
	public String [] splitName(String name)
	{
		String[] r;
		
		//try splitting on '_' first
		r = name.split("_");
		if (r.length > 1)
			return r;
		
		//try splitting on spaces
		r = name.split("\\s+");
		if (r.length > 1)
			return r;
		
		//else, try splitting on capitals
		//don't return if the name is all capitals
		
		r = name.split("(?=\\p{Lu})");
		if (r.length < name.length())
			return r;
		
		//it's one word
		
		r = new String[1];
		r[0] = name;
		return r;
	}
	
	public String nameReplace(String oldName)
	{
		
		
		String newName = "";
		
		for (String s : splitName(oldName))
		{
			newName += wordReplace(s);
		}
		
		return newName;
	}
	
	public String wordReplace(String oldWord)
	{
		if (retainMapping && wordMapping.containsKey(oldWord))
		{
			return wordMapping.get(oldWord);
		}
		
		int index = (int) (Math.random() * candidateWords.size());
		String newWord = candidateWords.get(index);
		
		candidateWords.remove(index);
		
		//try to match the case of the old word
		int upperCase = 0;
		
		for (int k = 0; k < oldWord.length(); k++) {
		    // Check for uppercase letters.
		    if (Character.isUpperCase(oldWord.charAt(k))) upperCase++;
		}
		
		if (upperCase == oldWord.length())
			newWord = newWord.toUpperCase();
		
		
		wordMapping.put(oldWord, newWord);
		System.out.println(oldWord + " => " + newWord);
		
		return newWord;
	}
	
	
}
