package com.mms.exceptions;

public class NoDataForStatisticException extends RuntimeException {

    public NoDataForStatisticException() {
        super("No data for statistic");
    }

    public NoDataForStatisticException(String message) {
        super(message);
    }
}
