package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.priority.Priority;

import java.util.function.Consumer;
import java.util.function.Function;

public interface EventHandler<T> {
    void post(Consumer<T> tConsumer);

    void subscribe(T eventCall);

    void subscribe(Priority priority, T eventCall);
}