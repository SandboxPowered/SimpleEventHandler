package org.sandboxpowered.eventhandler.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sandboxpowered.eventhandler.ResettableEventHandler;
import org.sandboxpowered.eventhandler.Returnable;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testCallbackInfo() {
        TEST.subscribe((string, test) -> test.setReturnValue(string.equals("test")));

        Returnable<Boolean> info = new Returnable<>(false);
        TEST.post(call -> call.onTest("test", info));
        assertTrue(info.getReturnValue());
    }

    @Test
    public void testCallbackInfoInverse() {
        TEST.subscribe((string, test) -> test.setReturnValue(string.equals("test")));

        Returnable<Boolean> info = new Returnable<>(false);
        TEST.post(call -> call.onTest("not test", info));
        assertFalse(info.getReturnValue());
    }

    public interface OnTest {
        void onTest(String string, Returnable<Boolean> test);
    }
}