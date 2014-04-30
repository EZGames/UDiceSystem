package speedTest;

import java.util.List;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.SimpleCollection;
import ezgames.utils.collections.ZArray;

public class MlListVsZArray
{
	
	public static void main(String[] args)
	{
		List<Integer> ints = SpeedTestingUtility.getListOfInts(1000);
		SimpleCollection<Integer> sc = SimpleCollection.fromList(ints);
		
		SpeedTestingUtility.run(()->MlList.fromIterable(ints), "MlList creation", 20);
		SpeedTestingUtility.run(()->ZArray.createFromSimpleCollection(sc), "ZArray creation", 20);
		
		MlList<Integer> mlList = MlList.fromIterable(ints);
		ZArray<Integer> zArray = ZArray.createFromSimpleCollection(sc);
		
		SpeedTestingUtility.run(iterate(mlList), "MlList iteration", 20);
		SpeedTestingUtility.run(iterate(zArray), "ZArray iteration", 20);
		SpeedTestingUtility.run(iterateZ(zArray), "ZArray old-school iteration", 20);
		
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
	
	private static Runnable iterateZ(ZArray<Integer> zarr)
	{
		return new Runnable()
		{
			public void run()
			{
				int size = zarr.size();
				for(int i = 0; i < size; i++)
				{
					Integer a = zarr.get(i);
					a++;
					a--;
				}
			}
		};
	}
}
