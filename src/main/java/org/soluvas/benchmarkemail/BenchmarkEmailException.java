package org.soluvas.benchmarkemail;

/**
 * Created by ceefour on 12/17/15.
 */
public class BenchmarkEmailException extends RuntimeException {

    public BenchmarkEmailException() {
    }

    public BenchmarkEmailException(String message) {
        super(message);
    }

    public BenchmarkEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public BenchmarkEmailException(Throwable cause, String format, Object... params) {
        super(String.format(format, params), cause);
    }

    public BenchmarkEmailException(Throwable cause) {
        super(cause);
    }

    public BenchmarkEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
