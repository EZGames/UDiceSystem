package com.ezgames.utils.exceptions;

@SuppressWarnings("serial")
public class ImpossibleException extends RuntimeException
{
   public ImpossibleException(String message, Exception cause)
   {
     super("This exception should not occur: " + message + ". If this exception has been thrown, email sad2project@gmail.com with all the details you can provide.", cause);
   }
}
