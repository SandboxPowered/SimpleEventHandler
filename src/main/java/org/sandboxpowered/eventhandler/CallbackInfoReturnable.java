package org.sandboxpowered.eventhandler;

public class CallbackInfoReturnable<V> extends CallbackInfo {
    private V returnValue;

    public CallbackInfoReturnable(boolean cancellable) {
        this(cancellable, null);
    }

    public CallbackInfoReturnable(boolean cancellable, V returnValue) {
        super(cancellable);
        this.returnValue = returnValue;
    }

    public V getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(V value) {
        this.returnValue = value;
    }
}