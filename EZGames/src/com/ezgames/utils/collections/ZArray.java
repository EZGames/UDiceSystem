package com.ezgames.utils.collections;

import java.util.Iterator;

import com.ezgames.utils.IterableUtil;
import com.ezgames.utils.interfaces.TImmutable;

public abstract class ZArray<T extends TImmutable> implements Iterable<T>
{
   // Public factory constructors *************************************
   /**
    * Creates an instance of a {@code ZRollerArray> containing the 
    * the items contained within the {@code Iterable} specified 
    * @param inList - an {@code Iterable} containing items of type T to be 
    * inserted into the returned {@code ZRollerArray} instance
    * @throws ClientDataException if an element in the specified 
    * {@code Iterable} is null
    */
   public static <E extends TImmutable> ZArray<E> createWithMultipleValues(Iterable<E> inList) throws IllegalArgumentException
   {
      if(inList instanceof ZArray<?>)
      {
    	  return (ZArray<E>)inList;
      }
      
      int numElements =IterableUtil.sizeOf(inList);
      if(numElements == 0)
      {
    	  throw new IllegalArgumentException("Cannot create a ZRollerArray with 0 elements");
      }
      if(numElements == 1)
      {
    	  Iterator<E> iter = inList.iterator();
    	  return new SingleValueZArray<E>(iter.next());
      }
      return new MultiValueZArray<E>(inList);
   }
   
   /**
    * Creates an instance of a {@code ZRollerArray> containing the 
    * the item specified 
    * @param item - the single element stored within the {@code ZRollerArray}
    * @throws ClientDataException if the item specified is null
    */
   public static <E extends TImmutable> ZArray<E> createWithSingleValue(E item) throws IllegalArgumentException
   {
      return new SingleValueZArray<E>(item);
   }
   
   // Public API methods **********************************************
   /**
    * Method overridden to check that both {@code ZRollerArrays} contain the same data,
    * rather than checking that they're simply the same object
    * @param o - the object to compare against this object
    * @return true if and only if o is a {@code ZRollerArray} containing objects in
    * it in the same order as this one that all return {@code true} when compared to 
    * each other with {@code equals()}
    */
   @Override
   public abstract boolean equals(Object o);
   
   /**
    * Returns the element at the specified index
    * @param index - index of the array from which to get the data from
    * @return the element at the specified index or {@code null} if the index out-of-bounds
    */
   public abstract T get(int index);
   
   @Override
   public abstract int hashCode();
	
   /**
    * @return the number of elements stored in the array 
    */
   public abstract int size();
}
