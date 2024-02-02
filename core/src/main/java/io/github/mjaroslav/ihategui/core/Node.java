package io.github.mjaroslav.ihategui.core;


import io.github.mjaroslav.sharedjava.util.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Node {
    protected final Property<Boolean> dirty = new Property<Boolean>().setConvention(false);
    protected Container parent;
    protected Root root;

    public boolean isDirty() {
        return dirty.get();
    }

    public void markDirty() {
        dirty.set(true);
    }

    public abstract void update();
}
