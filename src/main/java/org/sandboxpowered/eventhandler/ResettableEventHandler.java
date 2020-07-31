package org.sandboxpowered.eventhandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

public class ResettableEventHandler<T> implements EventHandler<T> {
    private static final BooleanSupplier ALWAYS_TRUE = () -> true;
    private static final BooleanSupplier ALWAYS_FALSE = () -> false;
    private final Map<Priority, Set<T>> subscribers = new HashMap<>();
    private final Map<T, Priority> reversePriority = new HashMap<>();

    @Override
    public <R> R post(Function<T, R> trFunction, BiFunction<R, R, R> rComparator) {
        return post(trFunction, rComparator, ALWAYS_FALSE);
    }

    @Override
    public <R> R post(Function<T, R> trFunction, BiFunction<R, R, R> rComparator, BooleanSupplier isCancelled) {
        R value = null;
        boolean canceled = false;
        for (int i = Priority.VALUES.length - 1; i >= 0; --i) {
            if (canceled) break;
            Priority priority = Priority.VALUES[i];
            Set<T> set = subscribers.get(priority);
            if (set != null) {
                for (T subscriber : set) {
                    R rVal = trFunction.apply(subscriber);
                    value = value == null ? rVal : rComparator.apply(value, rVal);
                    if (isCancelled.getAsBoolean()) {
                        canceled = true;
                        break;
                    }
                }
            }
        }
        return value;
    }

    @Override
    public void post(Consumer<T> tConsumer) {
        post(tConsumer, ALWAYS_FALSE);
    }

    @Override
    public void post(Consumer<T> tConsumer, BooleanSupplier isCancelled) {
        boolean canceled = false;
        for (int i = Priority.VALUES.length - 1; i >= 0; --i) {
            if (canceled) break;
            Priority priority = Priority.VALUES[i];
            Set<T> set = subscribers.get(priority);
            if (set != null) {
                for (T subscriber : set) {
                    tConsumer.accept(subscriber);
                    if (isCancelled.getAsBoolean()) {
                        canceled = true;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void subscribe(Priority priority, T subscriber) {
        subscribers.computeIfAbsent(priority, k -> new HashSet<>()).add(subscriber);
        reversePriority.put(subscriber, priority);
    }

    @Override
    public void unsubscribe(T subscriber) {
        Priority priority = reversePriority.get(subscriber);
        Set<T> set = subscribers.get(priority);
        set.remove(subscriber);
        reversePriority.remove(subscriber);
        if (set.isEmpty()) subscribers.remove(priority);
    }

    public void reset() {
        subscribers.clear();
        reversePriority.clear();
    }

    @Override
    public boolean hasSubscribers() {
        return !subscribers.isEmpty();
    }
}