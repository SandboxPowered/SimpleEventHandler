package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.core.EventArgs;
import org.sandboxpowered.eventhandler.core.EventHandlerBase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EventHandler<S, A extends EventArgs> implements EventHandlerBase<S, A> {
    private final Set<BiConsumer<S, A>> subscribers = new HashSet<>();
    private final AsyncHandler asyncHandle = new AsyncHandler();

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

    public CompletableFuture<Void> acceptAsync(S sender, A args) {
        return CompletableFuture.runAsync(asyncHandle.setup(sender, args));
    }

    private class AsyncHandler implements Runnable {
        private S sender;
        private A args;

        @Override
        public void run() {
            accept(sender, args);
        }

        Runnable setup(S sender, A args) {
            this.sender = sender;
            this.args = args;
            return this;
        }
    }
}
