package org.sandboxpowered.eventhandler;

public interface CallbackInfoReturnable<V> extends CallbackInfo {
    void setReturnValue(V value);

    V getReturnValue();
}
