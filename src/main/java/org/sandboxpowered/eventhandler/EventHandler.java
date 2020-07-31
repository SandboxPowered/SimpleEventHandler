package org.sandboxpowered.eventhandler;

import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

public interface EventHandler<T> {

    default <R> R post(Function<T, R> trFunction, BiFunction<R, R, R> rComparator) {
        return post(trFunction, rComparator, Cancellable.ALWAYS_FALSE);
    }

    <R> R post(Function<T, R> trFunction, BiFunction<R, R, R> rComparator, BooleanSupplier isCancelled);

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
