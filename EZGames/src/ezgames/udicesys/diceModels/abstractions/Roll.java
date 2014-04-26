package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;

@Immutable
public interface Roll
{	
	Die die();
	Face rolledFace();
	Iterable<FaceValue> rolledFaceValues();
	Iterable<Relationship> rolledRelationships();
	Iterable<Effect> rolledEffects();
}
