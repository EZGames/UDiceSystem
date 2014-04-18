package ezgames.utils.collections;

import java.util.Iterator;

import ezgames.utils.DataChecker;

/**
 * {@code ZRollerArray} was designed as a limited collection for internal use
 *  with the library in order to reduce some memory space.  It is very limited 
 *  - use at your own discretion.
 * @author Jacob ZD Zimmerman
 * @param <T> Can be any object, really, but this class was specifically 
 *   designed for the domain objects in the ZRoller library.
 */
final class MultiValueZArray<T> extends ZArray<T>
{
   // Public constructors ********************************************
   public MultiValueZArray(Iterable<T> collectionToCopy) throws IllegalArgumentException
   {
      int numOfElements = getNumberOfElementsIn(collectionToCopy);
      array = new Object[numOfElements];
      insertCollection(collectionToCopy);
   }
   
   // Public API methods **********************************************
   @SuppressWarnings("unchecked")
   @Override
   public final boolean equals(Object o)
   {
      //do the fast check first
      if(o == this) { return true; }
      
      //check type and cast if it's the same type
      MultiValueZArray<T> other;      
      if(o instanceof MultiValueZArray<?>)
      {
	 try
	 { other = (MultiValueZArray<T>) o; }
	 
	 catch(ClassCastException e)
	 { return false; }
      }
      else { return false; }
      
      //check size
      if(other.array.length != array.length)
	 return false;
      
      //check the items contained within
      int size = array.length;
      for(int i = 0; i < size; ++i)
      {
	 if(!array[i].equals(other.array[i])) { return false; }
      }      
      return true;
   }
   
   @SuppressWarnings("unchecked") @Override
   public final T get(int index)
   {
      try
      {
	 return (T) array[index];
      }
      catch(IndexOutOfBoundsException e)
      {
	 return null;
      }
   }
   
   @Override
   public int hashCode()
   {
      int result = 31;
      int size = array.length;
      for(int i = 0; i < size; i++)
      {
	 result = 31 * result + array[i].hashCode();
      }
      return result;
   }
   
   @Override
   public final Iterator<T> iterator()
   {
      return new MultiValueArrayIterator(this);
   }
   
   @Override
   public int size()
   {
      return array.length;
   }

   //TODO toString()
   
   // Private fields **************************************************
   private final Object[] array;//An Object array is much easier to implement than
   		//an array of type T.  With all the public methods restricting
   		//input to only objects of type T, this array is still typesafe

   // Private setters *************************************************
   //puts the given elements at the current location in the array
   private void insertCollection(Iterable<T> collection) throws IllegalArgumentException
   {
      int index = 0;
      for(T element : collection)
      {
	 DataChecker.checkDataNotNull(element, "Cannot create a ZRollerArray with a null element");
	 array[index] = element;
	 ++index;
      }
   }
   
   //returns the number of elements in the given collection
   private int getNumberOfElementsIn(Iterable<T> collection)
   {
      int numOfElements = 0;
      //since we accept all Iterables, not just Collections (that would remove
      //the ability to interact with this class), we must iterate through the
      //given collection and count the number of elements
      for(@SuppressWarnings("unused") T o : collection)
      {
	 ++numOfElements;
      }
      return numOfElements;
   }

   // Private inner class: Iterator ************************************
   private final class MultiValueArrayIterator implements Iterator<T>
   {
      private final MultiValueZArray<T> coll;
      private int nextIndex = 0;
      
      protected MultiValueArrayIterator(MultiValueZArray<T> arr)
      {
	 coll = arr;
      }
      
      @Override
      public boolean hasNext()
      {
	 return nextIndex < coll.array.length;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T next()
      {
	 if(hasNext())
	 {
	    return (T) coll.array[nextIndex++];
	 }
	 else
	 {
	    return null;
	 }
      }

      @Override
      public void remove()
      {
	 throw new UnsupportedOperationException("ZRollerArray is Immutable and does not allow removing elements");
      }
   }
   
   
}
