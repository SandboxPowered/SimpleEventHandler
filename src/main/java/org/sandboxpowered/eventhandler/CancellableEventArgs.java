package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.core.EventArgs;

public interface CancellableEventArgs extends EventArgs {
    void setCanceled();
    boolean isCanceled();
}
