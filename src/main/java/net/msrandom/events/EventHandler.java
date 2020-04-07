package net.msrandom.events;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class EventHandler<SENDER, ARGS extends EventArgs> implements IEventHandler<SENDER, ARGS> {
    private Set<BiConsumer<SENDER, ARGS>> subscribers = new HashSet<>();

    @Override
    public void subscribe(BiConsumer<SENDER, ARGS> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(BiConsumer<SENDER, ARGS> subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void accept(SENDER sender, ARGS args) {
        for (BiConsumer<SENDER, ARGS> subscriber : subscribers) {
            subscriber.accept(sender, args);
        }
    }
}
