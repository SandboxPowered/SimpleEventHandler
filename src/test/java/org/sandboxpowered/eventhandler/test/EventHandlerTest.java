package org.sandboxpowered.eventhandler.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sandboxpowered.eventhandler.Returnable;
import org.sandboxpowered.eventhandler.ResettableEventHandler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventHandlerTest {

    private static ResettableEventHandler<OnTest> TEST;
    private static ResettableEventHandler<OnReturn> RETURN;

    @BeforeAll
    public static void createEventHandlers() {
        TEST = new ResettableEventHandler<>();
        RETURN = new ResettableEventHandler<>();
    }

    @AfterEach
    public void resetEventHandlers() {
        TEST.reset();
        RETURN.reset();
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

    @Test
    public void testCallbackInfoReturning() {
        RETURN.subscribe((a, b) -> a > b);
        RETURN.subscribe((a, b) -> a < b);

        boolean ret = RETURN.post(onReturn -> onReturn.isLarger(927, 632), (a, b) -> a || b);

        assertTrue(ret);
    }

    public interface OnTest {
        void onTest(String string, Returnable<Boolean> test);
    }

    public interface OnReturn {
        boolean isLarger(int a, int b);
    }
}