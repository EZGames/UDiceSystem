package com.ezgames.utils.exceptions;

@SuppressWarnings("serial")
public class ImportExportException extends Exception
{
   public ImportExportException(String message)
   {
	  super(message);
   }

   public ImportExportException()
   {
	  super();
   }
   
   public ImportExportException(String message, Throwable cause)
   {
	  super(message, cause);
   }
   
   public ImportExportException(Throwable cause)
   {
	  super(cause);
   }
}
