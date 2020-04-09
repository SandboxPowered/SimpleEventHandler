package org.sandboxpowered.api.events;

import org.sandboxpowered.api.events.core.EventArgs;

public class CancellableEventArgs extends EventArgs {
    private boolean isCanceled;

    public void setCanceled() {
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}
