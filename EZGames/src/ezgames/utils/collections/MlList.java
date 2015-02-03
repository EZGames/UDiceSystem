package ezgames.utils.collections;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import ezgames.annotations.Immutable;
import ezgames.utils.collections.simple.SimpleCollection;
//import func.java.recursion.TailCall;

// TEST
/**
 * <p>
 * {@code MlList} is made to mimic (with method names instead of the silly symbols)
 *  the built-in list type of the ML functional language.</p>
 * <p>
 * Pros:
 * <ul>
 * <li>Works well with recursive functions. To iterate over the collection without
 * the need for an iterator, one can use the {@link #head()} method to get the 
 * element at the front of the collection, and pass the rest of the collection
 * to the next recursive call with {@link #tail()}.</li>
 * <li>Has a great, fluent interface for building small collections up. Being
 * immutable, all methods that <i>would</i> alter the collection simply return a
 * new copy (usually in an extremely efficient manner), which can be used for the
 * next "mutation". For example, you could make a quick collection of numbers
 * with the line <code>MlList&lt;Integer&gt; myList = MlList.startWith(1).add(2).and(3).and(4);</code>
 * Or you could use <code>MlList&lt;Integer&gt; myList = MlList.fromValues(1,2,3,4);</code></li>
 * <li>Works with the {@link Stackable} interface to make the creation of the
 * collections even easier.</li>
 * </ul>
 * Cons:
 * <ul>
 * <li>The object design itself is recursive, so there isn't a constant look-up
 * time associated with {@link #get()} by index.</li>
 * <li>Every time you add an object to the list, it's prepended instead of appended,
 * which is against what one would normally expect. Generally, you want to avoid
 * having order matter when using this collection.</li>
 * </ul>
 * @param <E> - The type of elements stored in the MlList
 */
@Immutable
public class MlList<E> implements SimpleCollection<E>
{
	//**************************************************************************
	// Public static factories
	//**************************************************************************	
	/**
	 * Creates a new {@code MlList} that appends both lists together, placing the
	 * items in the order generally expected of an append.
	 * <p>
	 * <b>Note:</b> This is an unoptimized recursive algorithm. If the left list
	 * is too long, it may trigger a {@code StackOverflowError}. If the order doesn't
	 * matter, then you should use {@link #addAll}, which doesn't suffer from
	 * the same weakness.</p>
	 * @param l1 - the first list to be on the left of the returned list
	 * @param l2 - the second list to be on the right of the returned list
	 * @return a new {@code MlList} with {@code l1}'s elements on the left and 
	 * {@code l2}'s elements on the right.
	 */
	public static <E> MlList<E> append(MlList<E> l1, MlList<E> l2)
	{
		if (l2.isEmpty())
		{
			return l1;
		}
		else if (l1.isEmpty())
		{
			return l2;
		}
		else
		{
			return append(l1.tail, l2).add(l1.head);
		}
	}
	
	/**
	 * @return an empty {@code MlList}; contains no items
	 */
	@SuppressWarnings("unchecked")
	public static <E> MlList<E> empty()
	{
		return (MlList<E>)EMPTY;
	}
	
	/**
	 * Creates a new {@code MlList} from an {@code Iterable}
	 * @param iterable - the {@code Iterable} to create the {@code MlList} from
	 * @return the new {@code MlList}
	 */
	public static <E> MlList<E> fromIterable(Iterable<E> iterable)
	{
		//if it's already an MlList, then just send it back
		if (iterable instanceof MlList) { return (MlList<E>) iterable; }
		
		//let's create the new one
		MlList<E> list = MlList.<E>empty();
		for (E o : iterable)
		{
			list = list.add(o);
		}
		return list;
	}
	
	/**
	 * Creates a new {@code MlList} from {@code varargs} array of values
	 * @param arr - {@code varargs} array of values from which to create a new 
	 * {@code MlList}
	 * @return the new {@code MlList}
	 */
	@SafeVarargs
	public static <E> MlList<E> fromValues(E... arr)
	{		
		if(0 == arr.length)
		{
			return MlList.<E>empty();
		}
		
		MlList<E> list = MlList.<E>empty();
		for(E o : arr)
		{
			list = list.add(o);
		}
		return list;
	}
	
	/**
	 * Creates a new 1-item {@code MlList} containing the given value
	 * @param val - the value to contain in the new {@code MlList}
	 * @return the new {@code MlList}
	 */
	public static <E> MlList<E> startWith(E val)
	{
		if(null == val)
		{
			return MlList.<E>empty();
		}
		else
		{
			return new MlList<E>(val);
		}
	}
	
	// TODO: Make a Stream Collector for MlList
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	/**
	 * Creates a new {@code MlList} with the given element added to the beginning
	 * of this list
	 * @param element - the item to add to the beginning of this list
	 * @return the new {@code MlList} with the given element in front of this list.
	 */
	public MlList<E> add(final E element)
	{
		if (element == null) { return this; }
		MlList<E> out = new MlList<>(element, this);
		return out;
	}
	
	/**
	 * Creates a new {@code MlList} that contains all the elements from this list
	 * and the given list.
	 * @param other - the other list, whose elements are to be added onto this
	 * @return the new {@code MlList} containing all the elements from this list
	 * and the given one
	 */
	public MlList<E> addAll(final MlList<E> other)
	{
		if(other.isEmpty())
		{
			return this;
		}
		else
		{
			return this.add(other.head).addAll(other.tail);
		}
	}
	
	/**
	 * Creates a new {@code MlList} that contains all the elements from this list
	 * and the given {@code Iterable}.
	 * @param other - the other collection, whose elements are to be added onto this
	 * @return the new {@code MlList} containing all the elements from this list
	 * and the given {@code Iterable}
	 */
	public MlList<E> addAll(final Iterable<E> other)
	{
		MlList<E> temp = this;
		for(E item : this)
		{
			temp = temp.add(item);
		}
		return temp;
	}
	
	/**
	 * Same as the {@link add(E)} method. Can be used to make building MlLists more fluent.
	 * For example, {@code list.add(4).and(5).and(6)} reads a little better than
	 * {@code list.add(4).add(5).add(6)}.
	 */
	public MlList<E> and(final E element)
	{
		return add(element);
	}
	
	/**
	 * Creates a new {@code MlList} that appends this and the given list together,
	 * placing the items in the order generally expected of an append.
	 * <p>
	 * <b>Note:</b> This is an unoptimized recursive algorithm. If the left list
	 * is too long, it may trigger a {@code StackOverflowError}. If the order doesn't
	 * matter, then you should use {@link #addAll}, which doesn't suffer from
	 * the same weakness.</p>
	 * @param l1 - the first list to be on the left of the returned list
	 * @param l2 - the second list to be on the right of the returned list
	 * @return a new {@code MlList} with {@code l1}'s elements on the left and 
	 * {@code l2}'s elements on the right.
	 */
	public MlList<E> append(final MlList<E> other)
	{
		return append(this, other);
	}
	
	//specified in SimpleCollection
	public boolean contains(final E obj)
	{
		return indexOf(obj).isPresent();
	}
	
	//specified in Object
	public boolean equals(final Object o)
	{
		//TODO switch over to apache EqualsBuilder
		if (o == this) { return true; }
		
		if (!(o instanceof MlList<?>)) { return false; }
		
		MlList<?> oList = (MlList<?>) o;
		
		if(this == EMPTY)
		{
			if(oList == EMPTY)
			{
				return true;
			}
			return false;
		}
		else
		{
			return tail.equals(oList.tail);
		}
	}
	
	//specified in SimpleCollection
	public E get(int index)
	{
		if(index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			return head;
		}
		else 
		{
			return tail.get(index - 1);
		}
	}
	
	//specified in Object
	public int hashCode()
	{
		//TODO switch over to apache commons HashCodeBuilder
		return 0;
//		HashGenerator hasher = HashGenerator.createWithDefaultHashAlgorithm();
//		int code = hasher.getStartingValue();
//		code = hasher.hash(head, code);
//		return hasher.hash(tail, code);
	}
	
	/**
	 * <b>Note: </b>Used in conjunction with {@link #tail()}, this can be used by
	 * recursive functions to iterate over the collection. 
	 * @return the first object in this collection
	 * @see #tail()
	 * 
	 */
	public E head()
	{
		return head;
	}
	
	//specified in SimpleCollection
	public Optional<Integer> indexOf(E obj)
	{
		return Optional.empty();
		//TODO switch back to Tail Call when build issues are fixed
		//return indexOf(obj, 0).invoke();
	}
	
	/**
	 * @return {@code true} if this collection contains no elements.
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	//specified by Iterable
	public Iterator<E> iterator()
	{
		return new MllIterator<E>(this);
	}
	
	//specified by SimpleCollection
	public int size()
	{
		return size;
	}
	
	//specified by Streamable
	public Stream<E> stream()
	{
		Spliterator<E> split = Spliterators.spliterator(iterator(), size(), Spliterator.IMMUTABLE | Spliterator.ORDERED | Spliterator.SIZED);
		return StreamSupport.stream(split, false);
	}
	
	/**
	 * <b>Note: </b>Used in conjunction with {@link #head()}, this can be used by
	 * recursive functions to iterate over the collection. 
	 * @return {@code MlList} containing all but the first element of this collection
	 * @see #head()
	 */
	public MlList<E> tail()
	{
		return tail;
	}
	
	//specified in Object
	public String toString()
	{
		if (isEmpty()) { return ""; }
		return "[" + head.toString() + "]" + tail.toString();
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
		this(o, EMPTY);
	}
	
	private MlList(E o, MlList<E> list)
	{
		head = o;
		tail = list;
		size = list.size + 1;
	}
	
	//***************************************************************************
	// Private helpers
	//***************************************************************************
	//TODO
//	private TailCall<Optional<Integer>> indexOf(E obj, int currIndex)
//	{
//		if(head == null)
//		{
//			return TailCall.done(Optional.empty());
//		}
//		else if(head.equals(obj))
//		{
//			return TailCall.done(Optional.of(currIndex));
//		}
//		else
//		{
//			return () -> tail.indexOf(obj, currIndex + 1);
//		}
//	}
	
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
			return !next.isEmpty();
		}
		
		public E next()
		{
			E out = next.head;
			next = next.tail;
			return out;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException("Cannot remove elements from MlList");
		}
	}
}
