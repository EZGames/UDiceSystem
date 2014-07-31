package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.stream.Stream;
import ezgames.annotations.Immutable;
import ezgames.math.hashing.HashGenerator;
import ezgames.math.random.EZRandom;
import ezgames.math.random.SimpleRandom;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.rec.OpenRandomElementChooser;
import ezgames.utils.collections.rec.RandomElementChooser;
import ezgames.utils.collections.rec.SimpleChooserStrategy;
import ezgames.utils.collections.simple.SimpleCollection;

@Immutable
public final class UDie implements Die
{
	//**************************************************************************
	// static factory methods
	//**************************************************************************
	public static Die with( final String name, final SimpleCollection<Face> faces)
	{
		return with(name, faces, new EZRandom());
	}
	
	public static Die with(final String name, final SimpleCollection<Face> faces, final SimpleRandom rand)
	{
		return new UDie(name, faces, rand);
	}
	
	//**************************************************************************
	// Public API Methods
	//**************************************************************************
	@Override
	public boolean equals(Object obj)
	{
		if(null == obj) { return false; }
		
		if(this == obj) { return true; }
		
		if(obj instanceof UDie)
		{
			UDie other = (UDie)obj;
			
			return (name.equals(other.name) && faces.equals(other.faces));
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		HashGenerator hasher = HashGenerator.createWithDefaultHashAlgorithm();
		int hash = hasher.getStartingValue();
		hash = hasher.hash(name, hash);
		hash = hasher.hash(faces, hash);
		
		return hash;
	}
	
	@Override
	public Iterator<Face> iterator()
	{
		return faces.iterator();
	}
	
	@Override
	public SimpleCollection<Relationship> listRelationships()
	{
		MlList<Relationship> rels = MlList.<Relationship>empty();
		for(Face face : faces)
		{
			Iterable<Relationship> faceRels = face.listRelationships();
			for(Relationship rel : faceRels)
			{
				if(!rels.contains(rel))
					rels = rels.add(rel);
			}
		}
		return rels;
	}
	
	@Override
	public String name()
	{
		return name;
	}
	
	@Override
	public Roll roll()
	{
		RandomElementChooser<Face> rec = new OpenRandomElementChooser<>(faces, rand, new SimpleChooserStrategy<>());
		Face chosenFace = rec.choose();
		return new URoll(this, chosenFace);
	}
	
	@Override
	public Stream<Face> stream()
	{
		return faces.stream();
	}
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private final String name;
	private final SimpleCollection<Face> faces;
	private final SimpleRandom rand;
	
	//**************************************************************************
	// Private constructors
	//**************************************************************************
	private UDie(final String name, final SimpleCollection<Face> faces, final SimpleRandom rand)
	{
		this.name = name;
		this.faces = faces;
		this.rand = rand;
	}
}