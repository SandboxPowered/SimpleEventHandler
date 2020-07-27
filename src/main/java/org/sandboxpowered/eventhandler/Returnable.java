package org.sandboxpowered.eventhandler;

public class Returnable<V> {
    private V returnValue;

    public Returnable() {
        this(null);
    }

    public Returnable(V returnValue) {
        this.returnValue = returnValue;
    }

    public V getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(V value) {
        this.returnValue = value;
    }
}