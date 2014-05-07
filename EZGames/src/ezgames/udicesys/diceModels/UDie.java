package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.stream.Stream;
import ezgames.annotations.Immutable;
import ezgames.hashing.HashGenerator;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.DataChecker;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;

@Immutable
final class UDie implements Die
{
	//**************************************************************************
	// static factory methods
	//**************************************************************************
	public static Die with( final String name, final SimpleCollection<Face> faces) throws NullArgumentException
	{
		DataChecker.checkStringDataNotNull(name, "Die Requires non-null, non-empty name");
		DataChecker.checkIterableNotEmptyOrNull(faces, "Die Requires non-null, non-empty list of non-null faces");
		return new UDie(name, faces);
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
	{	//TODO: need to work on REC before finishing this
		try
		{
			return new URoll(this, faces.get(0));
		}
		catch (IndexOutOfBoundsException | NullArgumentException e)
		{
			return null;
		}
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
	
	//**************************************************************************
	// Private constructors
	//**************************************************************************
	private UDie(final String name, final SimpleCollection<Face> faces)
	{
		this.name = name;
		this.faces = faces;
	}
}