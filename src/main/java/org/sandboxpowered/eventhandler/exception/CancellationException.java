package org.sandboxpowered.eventhandler.exception;

/**
 * Exception thrown when a consumer attempts to call <tt>cancel()</tt> on a callback not marked as <tt>cancellable</tt>.
 */
public class CancellationException extends RuntimeException {
    public CancellationException() {
    }

    public CancellationException(String message) {
        super(message);
    }

    public CancellationException(Throwable cause) {
        super(cause);
    }

    public CancellationException(String message, Throwable cause) {
        super(message, cause);
    }
}