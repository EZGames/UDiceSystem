package hubpages.jacobzimmerman.collectiondecorators;

import java.util.Iterator;

public class Trimmed implements Iterable<String>
{
	public Trimmed(Iterable<String> base)
	{
		this.base = base;
	}
	
	public Iterator<String> iterator()
	{
		return new TrimmedIterator(base.iterator());
	}
	
	private Iterable<String> base;
}

class TrimmedIterator implements Iterator<String>
{
	public TrimmedIterator(Iterator<String> base)
	{
		this.base = base;
	}
	
	public boolean hasNext()
	{
		return base.hasNext();
	}
	
	public String next()
	{
		return base.next().trim();
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

	private Iterator<String> base;
}
