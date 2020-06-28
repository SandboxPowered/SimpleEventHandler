package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.core.EventArgs;
import org.sandboxpowered.eventhandler.core.EventHandlerBase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class EventHandler<S, A extends EventArgs> implements EventHandlerBase<S, A> {
    private final Set<BiConsumer<S, A>> subscribers = new HashSet<>();

    @Override
    public void subscribe(BiConsumer<S, A> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(BiConsumer<S, A> subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void clear() {
        subscribers.clear();
    }

    @Override
    public void accept(S sender, A args) {
        for (BiConsumer<S, A> subscriber : subscribers) {
            subscriber.accept(sender, args);
        }
    }

    public CompletableFuture<A> acceptAsync(S sender, A args) {
        return CompletableFuture.supplyAsync(() -> {
            accept(sender, args);
            return args;
        });
    }
}
