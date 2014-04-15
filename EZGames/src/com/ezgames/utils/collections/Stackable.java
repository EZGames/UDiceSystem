package com.ezgames.utils.collections;

import com.ezgames.utils.interfaces.TImmutable;

//TODO Stackable's testing and documentation
//TODO update Stackable to Java 8 interface
public abstract class Stackable<T extends Stackable<T>> implements TImmutable
{
	@SuppressWarnings("unchecked")
	public final MlList<T> and(T another)
	{
		MlList<T> list = MlList.empty();
		list = list.add(another).add((T)this);
		return list;
	}

	@SuppressWarnings("unchecked")
	public final MlList<T> and(Iterable<T> others)
	{
		if(others instanceof MlList<?>)
		{
			return ((MlList<T>)others).add((T)this);
		}
		else
		{
			MlList<T> list = MlList.fromIterable(others);
			return list.add((T)this);
		}
	}
	
	private static class TestClass extends Stackable<TestClass>
	{
		private int val;
		
		public TestClass(int val)
		{
			this.val = val;
		}
		
		public String toString()
		{
			return Integer.valueOf(val).toString();
		}
	}
	
	public static void main(String[] args)
	{
		MlList<TestClass> list = new TestClass(3).and(new TestClass(2).and(new TestClass(1)));
		System.out.println(list);
	}
}