package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.priority.Cancellable;

public class CancellableEventArgs implements Cancellable {
    private boolean isCanceled;

    @Override
    public void cancel() {
        isCanceled = true;
    }

    @Override
    public boolean isCanceled() {
        return isCanceled;
    }
}
