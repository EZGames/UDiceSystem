package com.ezgames.utils.collections;

import java.util.Iterator;
import com.ezgames.annotations.Immutable;
import com.ezgames.math.hashing.HashUtil;
import com.ezgames.utils.IterableUtil;
import com.ezgames.utils.Weighted;

//TODO: testing and documentation
@Immutable
public class MlList<E> implements Iterable<E>
{
	//**************************************************************************
	// Public static factories
	//**************************************************************************
	public static <E> MlList<E> fromIterable(Iterable<E> iterable) throws IllegalArgumentException
	{
		//if it's already an MlList, then just send it back
		if (iterable instanceof MlList) { return (MlList<E>) iterable; }
		
		//let's create the new one - simply 
		MlList<E> backwards = MlList.<E>empty();
		for (E o : iterable)
		{
			backwards = backwards.add(o);
		}
		return backwards.reverse();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> MlList<E> empty()
	{
		return (MlList<E>) EMPTY;
	}
	
	public static <E> MlList<E> append(MlList<E> l1, MlList<E> l2)
	{
		if (l2 == EMPTY)
		{
			return l1;
		}
		else if (l1 == EMPTY)
		{
			return l2;
		}
		else
		{
			return append(l1.tail, l2).add(l1.head);
		}
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	public MlList<E> add(final E element)
	{
		if (element == null) { return this; }
		MlList<E> out = new MlList<>(element, this);
		return out;
	}
	
	/**
	 * Same as the {@link add(E)} method. Can be used to make building MlLists more fluent.
	 */
	public MlList<E> and(final E element)
	{
		return add(element);
	}
	
	public boolean contains(final E obj)
	{
		return IterableUtil.contains(this, obj);
	}
	
	public boolean equals(Object o)
	{
		if (o == this) { return true; }
		
		if (!(o instanceof MlList<?>)) { return false; }
		
		MlList<?> oList = (MlList<?>) o;
		
		return head.equals(oList.head) && tail.equals(oList.tail);
	}
	
	public E get(int index)
	{
		return IterableUtil.getFrom(this, index);
	}
	
	public int hashCode()
	{
		HashUtil hasher = HashUtil.createDefaultHashUtil();
		int code = hasher.getStartingValue();
		code = hasher.hash(head, code);
		return hasher.hash(tail, code);
	}
	
	public E head()
	{
		return head;
	}
	
	public int indexOf(E obj)
	{
		return IterableUtil.indexOf(this, obj);
	}
	
	public boolean isNull()
	{
		return head == null;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return new MllIterator<E>(this);
	}
	
	public MlList<E> reverse()
	{
		return reverse(this);
	}
	
	public int size()
	{
		return IterableUtil.sizeOf(this);
	}
	
	public MlList<E> tail()
	{
		return tail;
	}
	
	public String toString()
	{
		if (isNull()) { return ""; }
		return "[" + head.toString() + "]" + tail.toString();
	}
	
	public MlList<Weighted<E>> withWeight(int weight)
	{
		MlList<Weighted<E>> out = tail.withWeight(1);
		Weighted<E> newHead = new Weighted<>(head, weight);
		return out.add(newHead);
	}
	
	//**************************************************************************
	// Private members
	//**************************************************************************
	@SuppressWarnings("rawtypes")
	private static final MlList EMPTY = new MlList();
	private final E head;
	private final MlList<E> tail;
	private final int size;
		
	//**************************************************************************
	// Private constructors
	//**************************************************************************
	private MlList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private MlList(E o)
	{
		head = o;
		tail = (MlList<E>) EMPTY;
		size = 1;
	}
	
	private MlList(E o, MlList<E> list)
	{
		head = o;
		tail = list;
		size = list.size + 1;
	}
	
	//**************************************************************************
	// Private static helpers
	//**************************************************************************
	@SuppressWarnings("unchecked")
	private static <E> MlList<E> reverse(MlList<E> list)
	{
		if (list == EMPTY) { return list; }
		return append(reverse(list.tail), (MlList<E>) EMPTY.add(list.head));
	}
	
	//**************************************************************************
	// Private Iterator Type
	//**************************************************************************
	private static class MllIterator<E> implements Iterator<E>
	{
		private MlList<E> next;
		
		public MllIterator(MlList<E> list)
		{
			next = list;
		}
		
		public boolean hasNext()
		{
			return next.isNull();
		}
		
		public E next()
		{
			E out = next.head;
			next = next.tail;
			return out;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException(
					"Cannot remove elements from MlList");
		}
	}
}
