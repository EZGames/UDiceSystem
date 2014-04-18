package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;

@Immutable
public interface Roll
{	
	public abstract Die die();
	public abstract Face rolledFace();
	public abstract Iterable<FaceValue> rolledFaceValues();
	public abstract Iterable<Relationship> rolledRelationships();
	public abstract Iterable<Effect> rolledEffects();
}
