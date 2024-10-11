package com.requestapi.demo.exception;

public class InvalidPlanetsException extends RuntimeException {
   public InvalidPlanetsException (String message){
      super(message);
   }

   public InvalidPlanetsException () {
      super("Autor procurado nao encontrado.");
   }
}