package com.nuig.cs.ct326.assignment1;

/**
 * @Author Daniel Hannon 19484286
 */
public class NegativePurchaseAmountException extends Exception{
    /**
     * Constructor for NegativePurchaseAmountException
     * @param errorMsg
     */
    public NegativePurchaseAmountException(String errorMsg) {
        super(errorMsg);
    }
}
