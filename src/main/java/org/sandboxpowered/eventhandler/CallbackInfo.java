package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.exception.CancellationException;

public interface CallbackInfo {
    boolean isCancellable();

    boolean isCancelled();

    void cancel() throws CancellationException;
}