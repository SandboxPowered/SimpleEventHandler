package org.sandboxpowered.eventhandler.core;

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
