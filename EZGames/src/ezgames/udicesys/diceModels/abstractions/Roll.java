package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.simple.SimpleCollection;

public interface Roll
{
	Die die();
	Face rolledFace();
	SimpleCollection<FaceValue> rolledFaceValues();
	SimpleCollection<Relationship> rolledRelationships();
	SimpleCollection<Effect> rolledEffects();
}
