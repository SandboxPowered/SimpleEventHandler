package net.msrandom.events;

import java.util.function.BiConsumer;

public interface IEventHandler<SENDER, ARGS extends EventArgs> extends BiConsumer<SENDER, ARGS> {

    void subscribe(BiConsumer<SENDER, ARGS> subscriber);

    void unsubscribe(BiConsumer<SENDER, ARGS> subscriber);

    @Override
    void accept(SENDER sender, ARGS args);

    void clear();
}
