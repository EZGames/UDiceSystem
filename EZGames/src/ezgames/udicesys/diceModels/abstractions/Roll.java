package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.utils.collections.simple.SimpleCollection;

@Immutable
public interface Roll
{	
	Die die();
	Face rolledFace();
	SimpleCollection<FaceValue> rolledFaceValues();
	SimpleCollection<Relationship> rolledRelationships();
	SimpleCollection<Effect> rolledEffects();
}
