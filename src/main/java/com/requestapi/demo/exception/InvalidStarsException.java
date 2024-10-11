package com.requestapi.demo.exception;

public class InvalidStarsException extends RuntimeException {
   public InvalidStarsException (String message){
      super(message);
   }

   public InvalidStarsException () {
      super("Autor procurado nao encontrado.");
   }
}