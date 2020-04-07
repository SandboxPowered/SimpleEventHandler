package net.msrandom.events.additional;

import net.msrandom.events.EventArgs;

public class CancellableEventArgs extends EventArgs {
    private boolean isCanceled;

    public void setCanceled() {
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}
