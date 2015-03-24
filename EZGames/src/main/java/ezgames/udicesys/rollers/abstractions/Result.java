package ezgames.udicesys.rollers.abstractions;

import ezgames.annotations.Immutable;

@Immutable
public interface Result
{
	String getRollResults();
	String getFinalResult();
}
