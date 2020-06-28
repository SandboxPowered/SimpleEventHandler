package org.sandboxpowered.eventhandler;

import org.sandboxpowered.eventhandler.priority.Cancellable;
import org.sandboxpowered.eventhandler.priority.Priority;
import org.sandboxpowered.eventhandler.priority.PriorityHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class PriorityEventHandler<S, A extends Cancellable> implements PriorityHandler<S, A> {
    private final Map<Priority, Set<BiConsumer<S, A>>> subscribers = new HashMap<>();
    private final Map<BiConsumer<S, A>, Priority> reversePriority = new HashMap<>();

    public void subscribe(BiConsumer<S, A> subscriber, Priority priority) {
        subscribers.computeIfAbsent(priority, k -> new HashSet<>()).add(subscriber);
        reversePriority.put(subscriber, priority);
    }

    @Override
    public void unsubscribe(BiConsumer<S, A> subscriber) {
        Priority priority = reversePriority.get(subscriber);
        Set<BiConsumer<S, A>> set = subscribers.get(priority);
        set.remove(subscriber);
        reversePriority.remove(subscriber);
        if (set.isEmpty()) subscribers.remove(priority);
    }

    @Override
    public void clear() {
        subscribers.clear();
        reversePriority.clear();
    }

    @Override
    public void accept(S sender, A args) {
        boolean canceled = false;
        for (int i = Priority.values().length - 1; i >= 0; --i) {
            if (canceled) break;
            Priority priority = Priority.values()[i];
            if (subscribers.containsKey(priority)) {
                for (BiConsumer<S, A> subscriber : subscribers.get(priority)) {
                    if (args.isCanceled()) {
                        canceled = true;
                        break;
                    }
                    subscriber.accept(sender, args);
                }
            }
        }
    }
}