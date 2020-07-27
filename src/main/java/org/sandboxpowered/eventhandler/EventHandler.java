package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.priority.Priority;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public interface EventHandler<T> {
    void post(Consumer<T> tConsumer);

    void post(Consumer<T> tConsumer, BooleanSupplier isCancelled);

    default void subscribe(T eventCall) {
        subscribe(Priority.NORMAL, eventCall);
    }

    void subscribe(Priority priority, T eventCall);
    void unsubscribe(T eventCall);
}