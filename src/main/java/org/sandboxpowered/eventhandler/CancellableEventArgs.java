package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.core.Cancellable;

public class CancellableEventArgs implements Cancellable {
    private boolean isCanceled;

    @Override
    public void setCanceled() {
        isCanceled = true;
    }

    @Override
    public boolean isCanceled() {
        return isCanceled;
    }
}
