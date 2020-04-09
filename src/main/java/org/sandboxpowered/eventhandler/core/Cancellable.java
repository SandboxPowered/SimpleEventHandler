package org.sandboxpowered.eventhandler.core;

public interface Cancellable extends EventArgs {
    void setCanceled();
    boolean isCanceled();
}
