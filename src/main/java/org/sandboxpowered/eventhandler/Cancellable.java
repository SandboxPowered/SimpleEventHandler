package org.sandboxpowered.eventhandler;

import java.util.function.BooleanSupplier;

public class Cancellable implements BooleanSupplier {
    static final BooleanSupplier ALWAYS_FALSE = () -> false;
    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public boolean getAsBoolean() {
        return isCancelled();
    }

    public void cancel() {
        cancelled = true;
    }
}