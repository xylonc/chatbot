package main.java.Exceptions;

public class ChatBotexception extends Exception{
    public ChatBotexception(String message){
        super(message);
    }

    public ChatBotexception(String message, Throwable cause){
        super(message, cause);
    }
}
