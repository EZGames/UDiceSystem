package ezgames.utils.exceptions;
//package com.github.kolorobot.exceptions.java8;


public class ExceptionNotThrownAssertionError extends AssertionError {
   private static final long serialVersionUID = 1L;

	public ExceptionNotThrownAssertionError() {
        super("Expected exception was not thrown.");
    }
    
    public ExceptionNotThrownAssertionError(Class<? extends Throwable> clazz)
    {
   	 super("Expected exception, " + clazz.getName() + ", was not thrown");
    }
}