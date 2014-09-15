package hubpages.jacobzimmerman.collectiondecorators;

import java.util.ArrayList;

public class InitialIdea
{
	
	public static void main(String[] args)
	{
		ArrayList<String> untrimmedStrings = getListOfStrings();
		ArrayList<String> trimmedStrings = new ArrayList<>();
		
		for(String untrimmedString : untrimmedStrings)
		{
			trimmedStrings.add(untrimmedString.trim());
		}
		
		//use trimmed strings...
	}
	
	public static ArrayList<String> getListOfStrings()
	{
		ArrayList<String> strings = new ArrayList<>();
		strings.add(" string to trim ");
		strings.add("another string to trim");
		return strings;
	}
	
}
