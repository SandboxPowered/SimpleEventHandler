package org.sandboxpowered.eventhandler;

public class ExampleUsage {
    public static EventHandler<OnTest> EVENT = new ResettableEventHandler<>();

    public static void main(String[] args) {
        EVENT.subscribe(ExampleUsage::onTest);


        CallbackInfoReturnable<Boolean> callbackInfo = new CallbackInfoReturnable<>(false, false);

        EVENT.post(onTest -> onTest.onTest("hi", callbackInfo), callbackInfo);

        System.out.println(callbackInfo.getReturnValue()); // true


        CallbackInfoReturnable<Boolean> callbackInfo2 = new CallbackInfoReturnable<>(false, false);

        EVENT.post(onTest -> onTest.onTest("no", callbackInfo2), callbackInfo2);

        System.out.println(callbackInfo.getReturnValue()); // false
    }


    public static void onTest(String string, CallbackInfoReturnable<Boolean> test) {
        test.setReturnValue(string.equals("hi"));
    }

    public interface OnTest {
        void onTest(String string, CallbackInfoReturnable<Boolean> test);
    }
}