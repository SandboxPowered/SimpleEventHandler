package org.sandboxpowered.eventhandler.core;

import java.util.function.BiConsumer;

public interface EventHandlerBase<S, A extends EventArgs> extends BiConsumer<S, A> {

    void subscribe(BiConsumer<S, A> subscriber);

    void unsubscribe(BiConsumer<S, A> subscriber);

    void clear();

    @Override
    void accept(S sender, A args);
}
