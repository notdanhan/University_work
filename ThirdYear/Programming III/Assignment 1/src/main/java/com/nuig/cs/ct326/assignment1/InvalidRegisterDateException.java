package com.nuig.cs.ct326.assignment1;

/**
 * @Author Daniel Hannon 19484286
 */
public class InvalidRegisterDateException extends Exception{
    /**
     * Constructor for InvalidRegisterDateException
     * @param errorMessage
     */
    public InvalidRegisterDateException(String errorMessage) {
        super(errorMessage);
    }
}
