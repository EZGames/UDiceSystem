package ezgames.udicesys.diceModels;

public class Weighted<T>
{

	public Weighted(T object, int weight)
	{
		this.object = object;
		this.weight = weight;
	}
	
	public boolean hasSameObjectAs(Weighted<T> other)
	{
		return this.object.equals(other.object);
	}
	
	public final T object;
	public final int weight;
}
