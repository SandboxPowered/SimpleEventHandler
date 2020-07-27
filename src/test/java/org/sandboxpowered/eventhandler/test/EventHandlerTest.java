package org.sandboxpowered.eventhandler.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sandboxpowered.eventhandler.CallbackInfoReturnable;
import org.sandboxpowered.eventhandler.EventHandler;
import org.sandboxpowered.eventhandler.ResettableEventHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventHandlerTest {

    private static EventHandler<OnTest> EVENT_HANDLER;

    @BeforeAll
    public static void createEventHandler() {
        EVENT_HANDLER = new ResettableEventHandler<>();
    }

    @AfterEach
    public void resetEventHandler() {
        ((ResettableEventHandler<OnTest>) EVENT_HANDLER).reset();
    }

    @Test
    public void testCallbackInfo() {
        EVENT_HANDLER.subscribe((string, test) -> test.setReturnValue(string.equals("test")));

        CallbackInfoReturnable<Boolean> info = new CallbackInfoReturnable<>(false);
        EVENT_HANDLER.post(call -> call.onTest("test", info), info);
        assertEquals(true, info.getReturnValue());
    }

    @Test
    public void testCallbackInfoInverse() {
        EVENT_HANDLER.subscribe((string, test) -> test.setReturnValue(string.equals("test")));

        CallbackInfoReturnable<Boolean> info = new CallbackInfoReturnable<>(false);
        EVENT_HANDLER.post(call -> call.onTest("not test", info), info);
        assertEquals(false, info.getReturnValue());
    }

    public interface OnTest {
        void onTest(String string, CallbackInfoReturnable<Boolean> test);
    }
}