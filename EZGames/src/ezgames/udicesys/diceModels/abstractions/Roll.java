package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.simple.SimpleCollection;

/**
 * The {@code Roll} class represents the result of rolling a {@code Die}.
 */
public interface Roll
{
	/**
	 * @return the Die that was rolled
	 */
	Die die();
	
	/**
	 * @return the Face that was rolled
	 */
	Face rolledFace();
	
	/**
	 * @return a SimpleCollection of all the FaceValues associated with the Face
	 * that was rolled
	 */
	SimpleCollection<FaceValue> rolledFaceValues();
	
	/**
	 * @return a SimpleCollection of all the Relationships associated with the
	 * Face that was rolled
	 */
	SimpleCollection<Relationship> rolledRelationships();
	
	/**
	 * @return a SimpleCollection of all the Effects associated with the Face
	 * that was rolled
	 */
	SimpleCollection<Effect> rolledEffects();
}
