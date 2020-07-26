package org.sandboxpowered.eventhandler;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface ReturningEventHandler<T, R> extends EventHandler<T> {
    R post(Function<T, R> function, BiFunction<R, R, R> comparator);
}
