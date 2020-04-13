package org.sandboxpowered.eventhandler.priority;

import org.sandboxpowered.eventhandler.core.EventArgs;

public interface Cancellable extends EventArgs {
    void cancel();
    boolean isCanceled();
}
