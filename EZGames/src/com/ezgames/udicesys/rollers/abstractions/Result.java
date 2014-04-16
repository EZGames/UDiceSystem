package com.ezgames.udicesys.rollers.abstractions;

import com.ezgames.annotations.Immutable;

@Immutable
public interface Result
{
	String getRollResults();
	String getFinalResult();
}
