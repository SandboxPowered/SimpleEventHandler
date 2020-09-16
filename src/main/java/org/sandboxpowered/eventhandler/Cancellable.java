package org.sandboxpowered.eventhandler;

import java.util.function.BooleanSupplier;

public interface Cancellable extends BooleanSupplier {
    BooleanSupplier ALWAYS_FALSE = () -> false;

    @Override
    default boolean getAsBoolean() {
        return isCancelled();
    }

    boolean isCancelled();

    void cancel();
}