import java.util.ArrayList;


public class MethodReferenceObjectTest
{
	public static void main(String[] args)
	{
		EventProducer producer = new EventProducer();
		EventListener listener = MethodReferenceObjectTest::referenceListener;
		
		producer.addListener(MethodReferenceObjectTest::referenceListener);
		producer.addListener(listener);
		
		producer.triggerEvent();
		
		producer.printListeners();
		
		System.out.println("\nRemoving Listeners...\n");
		producer.removeListener(listener);
		producer.removeListener(MethodReferenceObjectTest::referenceListener);
		
		producer.printListeners();
		
		/*
		 * As you can tell from the output and code, calling a method reference makes
		 * a new object every time,  To get around this, for times such as trying 
		 * to add and remove the same reference, simple assign the reference to a
		 * variable and use that instead.
		 * 
		 * In general, it's probably better overall to assign the reference to a 
		 * variable any time that the reference is called more than once. It even
		 * gets rid of the class identifier, making it potentially a shorter name
		 * and more useful if the method name is fairly generic. 
		 */
	}
	
	public static void referenceListener()
	{
		System.out.println("listener called");
	}	
	
	private static class EventProducer
	{
		ArrayList<EventListener> listeners = new ArrayList<>();
		
		public void addListener(EventListener listener)
		{
			listeners.add(listener);
		}
		
		public void removeListener(EventListener listener)
		{
			listeners.remove(listener);
		}
		
		public void triggerEvent()
		{
			for(EventListener listener : listeners)
			{
				listener.trigger();
			}
		}
		
		public void printListeners()
		{
			listeners.stream().forEach(System.out::println);
		}
	}
	
	private static interface EventListener
	{
		void trigger();
	}
}