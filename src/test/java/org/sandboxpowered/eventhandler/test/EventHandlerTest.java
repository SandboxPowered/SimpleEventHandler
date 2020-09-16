package org.sandboxpowered.eventhandler.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sandboxpowered.eventhandler.ResettableEventHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventHandlerTest {

    private static ResettableEventHandler<OnTest> TEST;

    @BeforeAll
    public static void createEventHandlers() {
        TEST = new ResettableEventHandler<>();
    }

    @AfterEach
    public void resetEventHandlers() {
        TEST.reset();
    }

    @Test
    public void testCancellable() {
        TEST.subscribe((string) -> string.equals("test"));

        assertTrue(TEST.cancellable(call -> call.onTest("test")));
    }

    public interface OnTest {
        boolean onTest(String string);
    }
}