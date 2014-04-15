package com.ezgames.utils.collections;

/**
 * RCE stands for Randomly Choosable Element.  Classes that implement RceCollection
 * are used to store elements that are meant to be randomly chosen.  As an
 * example, the use the interface was originally designed for is choosing a 
 * random face on a die.
 * RceCollections have a very minimal collection interface, in case the user
 * wants to make the collection Immutable, since any of the other Collection 
 * features can be added by implementing Collection.
 * @author jzimmerman
 *
 * @param <T>
 */
public interface RceCollection<T> extends Iterable<T>
{
	/**
	 * Returns true if this collection contains the given element. More formally, returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)).
	 * @param obj - element whose presence in this collection is to be tested
	 * @return true if this collection contains the given element
	 * @throws NullPointerException - if the given element is null and this collection does not permit null elements (optional)
	 */
	public boolean contains(T obj);
	/**
	 * @param index - the index from which to pull the element from
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException - if the given index is out of range {@code (index < 0 || index >= size())}
	 */
	public T get(int index);
	/**
	 * Returns the index of the first occurrence of the given element in this collection, or -1 if this collection does not contain the element.
	 * @param obj - the element to search for
	 * @return the index of the first occurrence of the given element in this collection or -1, if this collection does not contain the element.
	 */
	public int indexOf(T obj);
	/**
	 * @return a randomly chosen element from the collection
	 */
	public T random();
	/**
	 * @return the number of elements in the collection
	 */
	public int size();
}
