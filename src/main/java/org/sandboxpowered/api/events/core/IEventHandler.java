package org.sandboxpowered.api.events.core;

import java.util.function.BiConsumer;

public interface IEventHandler<S, A extends EventArgs> extends BiConsumer<S, A> {

    void subscribe(BiConsumer<S, A> subscriber);

    void unsubscribe(BiConsumer<S, A> subscriber);

    void clear();

    @Override
    void accept(S sender, A args);
}
