package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.exception.CancellationException;

import java.util.function.BooleanSupplier;

public class CallbackInfo implements BooleanSupplier {
    private final boolean cancellable;
    private boolean cancelled;

    public CallbackInfo(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public boolean getAsBoolean() {
        return isCancelled();
    }

    public void cancel() throws CancellationException {
        if (!isCancellable()) {
            throw new CancellationException("Attempted to cancel non-cancellable callback");
        } else {
            cancelled = true;
        }
    }
}