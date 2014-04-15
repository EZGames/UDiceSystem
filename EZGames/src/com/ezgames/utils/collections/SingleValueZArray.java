package com.ezgames.utils.collections;

import java.util.Iterator;

import com.ezgames.utils.DataChecker;
import com.ezgames.utils.interfaces.TImmutable;

final class SingleValueZArray<T extends TImmutable> extends ZArray<T> implements TImmutable
{
   
   // Public constructors *************************************
   public SingleValueZArray(T o) throws IllegalArgumentException
   {
      DataChecker.checkDataNotNull(o, "Cannot create a ZRollerArray from a null element");
      item = o;
   }
   
   // Public API methods **********************************************
   @SuppressWarnings("unchecked")
   @Override
   public boolean equals(Object o)
   {
      //quick check
      if(o == item)
      {
	 return true;
      }
      
      //type check
      SingleValueZArray<T> other;
      if(o instanceof Iterable<?>)
      {
	 try
	 {
	    other = (SingleValueZArray<T>) o;
	    return item.equals(other.item);
	 }
	 catch(ClassCastException e)
	 {
	    return false;
	 }
      }
      else
      {
	 return false;
      }
   }

   @Override
   public T get(int index)
   {
      if(index != 0)
      {
	 return null;
      }
      return item;
   }

   @Override
   public int hashCode()
   {
      int result = 31;
      result = 31 * result + item.hashCode();
      return result;
   }

   @Override
   public Iterator<T> iterator()
   {
      return new SingleValueArrayIterator(this);
   }

   @Override
   public int size()
   {
      return 1;
   }

   //TODO toString()
   
   // Private fields *************************************************
   private final T item;
   
   // Private Iterator Class *****************************************
   private class SingleValueArrayIterator implements Iterator<T>
   {
      private final SingleValueZArray<T> arr;
      private boolean hasNext = true;
      
      public SingleValueArrayIterator(SingleValueZArray<T> inArr)
      {
	 arr = inArr;
      }

      @Override
      public boolean hasNext()
      {
	 return hasNext;
      }

      @Override
      public T next()
      {
	 if(hasNext)
	 {
	    return arr.item;
	 }
	 return null;
      }

      @Override
      public void remove()
      {
	 throw new UnsupportedOperationException("ZRollerArray is Immutable and does not allow removing elements");
      }
      
   }

}
