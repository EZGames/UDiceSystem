package ezgames.udicesys.diceModels;

import java.util.Iterator;

import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.DataChecker;
import ezgames.utils.collections.MlList;

@Immutable
final class UDie implements Die
{
	//**************************************************************************
	// static factory methods
	//**************************************************************************
	public static Die with(String name, Iterable<Face> faces)
	{
		DataChecker.checkStringDataNotNull(name, "Die Requires non-null, non-empty name");
		DataChecker.checkIterableNotEmptyOrNull(faces, "Die Requires non-null, non-empty list of non-null faces");
		return new UDie(name, faces);
	}
	
	//**************************************************************************
	// Public API Methods
	//**************************************************************************
	@Override
	public Iterator<Face> iterator()
	{
		return faces.iterator();
	}
	
	@Override
	public Iterable<Relationship> listRelationships()
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
		return null;
	}
	
	@Override
	public Roll roll()
	{
		return null;
	}
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private final String name;
	private final Iterable<Face> faces;
	
	//**************************************************************************
	// Private constructors
	//**************************************************************************
	private UDie(String name, Iterable<Face> faces)
	{
		this.name = name;
		this.faces = faces;
	}
}
