package org.sandboxpowered.eventhandler.core;

import java.util.function.BiConsumer;

public interface PriorityHandler<S, A extends Cancellable> extends EventHandlerBase<S, A> {
    void subscribe(BiConsumer<S, A> subscriber, Priority priority);
}
