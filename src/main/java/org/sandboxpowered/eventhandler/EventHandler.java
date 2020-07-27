package org.sandboxpowered.eventhandler;

import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

public interface EventHandler<T> {

    <R> R post(Function<T,R> trFunction, BiFunction<R,R,R> rComparator);

    <R> R post(Function<T,R> trFunction, BiFunction<R,R,R> rComparator, BooleanSupplier isCancelled);

    void post(Consumer<T> tConsumer);

    void post(Consumer<T> tConsumer, BooleanSupplier isCancelled);

    default void subscribe(T eventCall) {
        subscribe(Priority.NORMAL, eventCall);
    }

    void subscribe(Priority priority, T eventCall);

    void unsubscribe(T eventCall);

    boolean hasSubscribers();
}