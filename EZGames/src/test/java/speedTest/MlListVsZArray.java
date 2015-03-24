package speedTest;

import java.util.List;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.collections.zarray.ZArray;

public class MlListVsZArray
{
	private static List<Integer> ints = SpeedTestingUtility.getListOfInts(1000);
	private static SimpleCollection<Integer> sc;
	
	public static void main(String[] args)
	{
		try
		{
			sc = SimpleCollection.from(ints);
			SpeedTestingUtility.getReady();
			SpeedTestingUtility.run(MlListVsZArray::createZArray, "ZArray creation", 100);
			SpeedTestingUtility.run(MlListVsZArray::createMlList, "MlList creation", 100);
			
			MlList<Integer> mlList = MlList.fromIterable(ints);
			ZArray<Integer> zArray = ZArray.createFromSimpleCollection(sc);
			
			SpeedTestingUtility.run(iterate(zArray), "ZArray iteration", 100);
			SpeedTestingUtility.run(iterate(mlList), "MlList iteration", 100);
			SpeedTestingUtility.run(iterateOldSchool(zArray), "ZArray old-school iteration", 50);
			SpeedTestingUtility.run(iterateOldSchool(mlList), "MlList old-school iteration", 50);
		}
		catch (Exception ex){}
	}
	
	private static void createZArray()
	{
		try
		{
			ZArray.createFromSimpleCollection(sc);
		}
		catch (Exception e){}
	}
	
	private static void createMlList()
	{
		MlList.fromIterable(ints);
	}
	
	private static Runnable iterate(Iterable<Integer> iter)
	{
		return new Runnable()
		{
			public void run()
			{
				for(Integer i : iter)
				{
					i++;
					i--;
				}
			}
		};
	}
	
	private static Runnable iterateOldSchool(SimpleCollection<Integer> coll)
	{
		return new Runnable()
		{
			public void run()
			{
				int size = coll.size();
				for(int i = 0; i < size; i++)
				{
					Integer a = coll.get(i);
					a++;
					a--;
				}
			}
		};
	}
}
