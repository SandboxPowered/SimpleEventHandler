package org.sandboxpowered.eventhandler;

import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public interface EventHandler<T> {

    default <R> R post(BiFunction<T, R, R> eventCaller) {
        return post(eventCaller, null, Cancellable.ALWAYS_FALSE);
    }

    default <R> R post(BiFunction<T, R, R> eventCaller, R originalValue) {
        return post(eventCaller, originalValue, Cancellable.ALWAYS_FALSE);
    }

    <R> R post(BiFunction<T, R, R> eventCaller, R originalValue, BooleanSupplier isCancelled);

    default void post(Consumer<T> tConsumer) {
        post(tConsumer, Cancellable.ALWAYS_FALSE);
    }

    void post(Consumer<T> tConsumer, BooleanSupplier isCancelled);

    default void subscribe(T eventCall) {
        subscribe(Priority.NORMAL, eventCall);
    }

    void subscribe(Priority priority, T eventCall);

    void unsubscribe(T eventCall);

    boolean hasSubscribers();
}