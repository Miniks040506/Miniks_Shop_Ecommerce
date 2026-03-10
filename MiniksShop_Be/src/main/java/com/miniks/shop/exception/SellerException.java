package com.miniks.shop.exception;

public class SellerException extends RuntimeException {

    public SellerException(String message) {
        super(message);
    }

    public SellerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerException(Throwable cause) {
        super(cause);
    }

}
