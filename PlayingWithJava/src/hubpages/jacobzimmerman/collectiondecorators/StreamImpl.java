package hubpages.jacobzimmerman.collectiondecorators;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamImpl
{
	public static void main(String[] args)
	{
		ArrayList<String> untrimmedStrings = getListOfStrings();
		Stream<String> trimmedStrings = untrimmedStrings.stream().map(String::trim);
		
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
