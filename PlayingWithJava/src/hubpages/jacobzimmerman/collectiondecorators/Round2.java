package hubpages.jacobzimmerman.collectiondecorators;

import java.util.ArrayList;

public class Round2
{
	public static void main(String[] args)
	{
		ArrayList<String> untrimmedStrings = getListOfStrings();
		Iterable<String> trimmedStrings = new Trimmed(untrimmedStrings);
		
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
