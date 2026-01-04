package main.java.Exceptions;

public class InvalidInputException extends ChatBotexception {
    public InvalidInputException(String message){
        super(message);
    }

    public InvalidInputException(String message , Throwable cause){
        super(message , cause);
    }
}
