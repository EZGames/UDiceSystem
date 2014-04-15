package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.utils.interfaces.TImmutable;

public interface Roll extends TImmutable
{	
	public abstract Die die();
	public abstract Face rolledFace();
	public abstract Iterable<FaceValue> rolledFaceValues();
	public abstract Iterable<Relationship> rolledRelationships();
	public abstract Iterable<Effect> rolledEffects();
}
