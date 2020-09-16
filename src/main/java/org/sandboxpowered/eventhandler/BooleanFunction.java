package org.sandboxpowered.eventhandler;

@FunctionalInterface
public interface BooleanFunction<T> {
    boolean apply(T object);
}
