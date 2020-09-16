package org.sandboxpowered.eventhandler;

import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public interface EventHandler<T> {
    BooleanSupplier ALWAYS_FALSE = () -> false;

    default <R> R returnable(BiFunction<T, R, R> eventCaller) {
        return returnable(eventCaller, null, ALWAYS_FALSE);
    }

    default <R> R returnable(BiFunction<T, R, R> eventCaller, R originalValue) {
        return returnable(eventCaller, originalValue, ALWAYS_FALSE);
    }

    <R> R returnable(BiFunction<T, R, R> eventCaller, R originalValue, BooleanSupplier isCancelled);

    boolean cancellable(BooleanFunction<T> eventCaller);

    default void post(Consumer<T> tConsumer) {
        post(tConsumer, ALWAYS_FALSE);
    }

    void post(Consumer<T> tConsumer, BooleanSupplier isCancelled);

    default void subscribe(T eventCall) {
        subscribe(Priority.NORMAL, eventCall);
    }

    void subscribe(Priority priority, T eventCall);

    void unsubscribe(T eventCall);

    boolean hasSubscribers();
}