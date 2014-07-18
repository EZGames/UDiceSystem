package ezgames.utils.tuples;

import static ezgames.test.matchers.Validates.*;
import static ezgames.test.matchers.exceptions.Throws.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.junit.Test;
import ezgames.test.mocks.Validatable;

public class Tuple2Test
{
	private static Tuple2<String, Exception> getTuple()
	{
		return Tuple2.of(string, exception);
	}
	
	private final static String string = "string";
	private final static Exception exception= new IllegalArgumentException("message", new NullPointerException());
	private final static Tuple2<String, Exception> tuple = getTuple();
	
	// ***** test of() **********************************************************
	@Test
	public void shouldCreateTupleWithStringAndException()
	{
		Tuple2<String, Exception> tuple = Tuple2.of(string, exception);
		
		assertThat(tuple.one, is(string));
		assertThat(tuple.two, is(exception));
	}
	
	@Test
	public void shouldCreateTupleWithNulls()
	{
		Tuple2<String, Exception> tuple = Tuple2.of(null, null);
		
		assertThat(tuple.one, is(nullValue()));
		assertThat(tuple.two, is(nullValue()));
	}
	
	// ***** test doWithOne() ***************************************************
	@Test
	public void shouldConsumeOneUsingDoWithOne()
	{
		TestConsumer<String> consumer = new TestConsumer<>(); //See inner class at the end
		
		tuple.doWithOne(consumer);
		
		assertThat(consumer, validates());
		assertThat(consumer.consumedValue, is(string));
	}
	
	@Test
	public void shouldReturnOriginalTupleUsingDoWithOne()
	{
		TestConsumer<String> consumer = new TestConsumer<>(); //See inner class at the end
		
		Tuple2<String, Exception> newTuple = tuple.doWithOne(consumer);
		
		assertThat(newTuple, is(tuple));
	}
	
	@Test
	public void shouldConsumeNullUsingDoWithOne()
	{
		TestConsumer<String> consumer = new TestConsumer<>();
		Tuple2<String, Exception> tuple = Tuple2.of(null, exception);
		
		tuple.doWithOne(consumer);
		
		assertThat(consumer, validates());
		assertThat(consumer.consumedValue, is(nullValue()));
	}
	
	@Test
	public void shouldThrowFromNullUsingDoWithOne()
	{
		Consumer<String> consumer = (s) -> s.getBytes();
		Tuple2<String, Exception> tuple = Tuple2.of(null, exception);
		
		assertThat(() -> tuple.doWithOne(consumer), throwsA(NullPointerException.class));
	}
	
	// ***** test doWithOneIfNotNull() ******************************************
	@Test
	public void shouldConsumeOneUsingDoWithOneIfNotNull()
	{
		TestConsumer<String> consumer = new TestConsumer<>(); //See inner class at the end
		
		tuple.doWithOneIfNotNull(consumer);
		
		assertThat(consumer, validates());
		assertThat(consumer.consumedValue, is(string));
	}
	
	@Test
	public void shouldReturnOriginalTupleUsingDoWithOneIfNotNull()
	{
		TestConsumer<String> consumer = new TestConsumer<>(); //See inner class at the end
		
		Tuple2<String, Exception> newTuple = tuple.doWithOneIfNotNull(consumer);
		
		assertThat(newTuple, is(tuple));
	}
	
	@Test
	public void shouldNotConsumeOneUsingDoWithOneIfNotNull()
	{
		TestConsumer<String> consumer = new TestConsumer<>();
		Tuple2<String, Exception> tuple = Tuple2.of(null, exception);
		
		tuple.doWithOneIfNotNull(consumer);
		
		assertThat(consumer, doesNotValidate());
	}
	
	// ***** test transformOne() ************************************************
	@Test
	public void shouldTransformOneToInt()
	{
		int length = tuple.transformOne(String::length);
		
		assertThat(length, is(equalTo(string.length())));
	}
	
	@Test
	public void shouldThrowWhenTryingToTransformNullOne()
	{
		Tuple2<String, Exception> tuple = Tuple2.of(null, exception);
		
		assertThat(() -> tuple.transformOne(String::length), throwsA(NullPointerException.class));
	}
	
	// ***** test operateOnOne() ************************************************
	@Test
	public void shouldOperateOnOne()
	{
		TestOperator<String> operator = new TestOperator<String>(string);
		
		String result = tuple.operateOnOne(operator);
		
		assertThat(result, is(string));
		assertThat(operator, validates());
	}
	
	@Test
	public void shouldThrowWhenTryingToOperateOnNullOne()
	{
		Tuple2<String, Exception> tuple = Tuple2.of(null, exception);
		
		assertThat(() -> tuple.operateOnOne(String::toString), throwsA(NullPointerException.class));
	}	
	
	// ***** test doWithTwo() ***************************************************
	@Test
	public void shouldConsumeExceptionUsingDoWithTwo()
	{
		TestConsumer<Exception> consumer = new TestConsumer<>();//See inner class at the end
		
		tuple.doWithTwo(consumer);
		
		assertThat(consumer, validates());
		assertThat(consumer.consumedValue, is(exception));
	}
	
	@Test
	public void shouldReturnOriginalTupleUsingDoWithTwo()
	{
		TestConsumer<Exception> consumer = new TestConsumer<>();//See inner class at the end
		
		Tuple2<String, Exception> newTuple = tuple.doWithTwo(consumer);
		
		assertThat(newTuple, is(tuple));
	}
	
	@Test
	public void shouldConsumeNullUsingDoWithTwo()
	{
		TestConsumer<Exception> consumer = new TestConsumer<>();//See inner class at the end
		Tuple2<String, Exception> tuple = Tuple2.of(string, null);
		
		tuple.doWithTwo(consumer);
		
		assertThat(consumer, validates());
		assertThat(consumer.consumedValue, is(nullValue()));
	}
	
	@Test
	public void shouldThrowFromNullUsingDoWithTwo()
	{
		Consumer<Exception> consumer = (ex) -> ex.toString();
		Tuple2<String, Exception> tuple = Tuple2.of(string, null);
		
		assertThat(() -> tuple.doWithTwo(consumer), throwsA(NullPointerException.class));
	}
	
	//TODO: doWithTwoIfNotNull, transformTwo, and operateOnTwo
	
	
	//***************************************************************************
	// Private helper classes
	//***************************************************************************
	private static class TestConsumer<T> implements Consumer<T>, Validatable
	{
		public void accept(T arg0)
		{
			isCalled = true;
			consumedValue = arg0;
		}
		
		public boolean validate()
		{
			return isCalled;
		}
		
		private boolean isCalled = false;
		public T consumedValue = null;
	}
	
	private static class TestOperator<T> implements UnaryOperator<T>, Validatable
	{
		public TestOperator(T expectedValue)
		{
			this.expectedValue = expectedValue;
		}	

		public T apply(T arg0)
		{
			actualValue = arg0;
			return arg0;
		}

		public boolean validate()
		{
			if(expectedValue == null && actualValue == null)
			{
				return true;
			}
			if(actualValue != null)
			{
				return actualValue.equals(expectedValue);
			}
			else
			{
				return false;
			}
		}
		
		private T expectedValue;
		private T actualValue;
	}
}
