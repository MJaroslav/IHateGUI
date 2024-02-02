package io.github.mjaroslav.ihategui.core;


import io.github.mjaroslav.sharedjava.tuple.pair.IPair;
import io.github.mjaroslav.sharedjava.util.Property;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class Control extends Node {
    protected final Property<IPair> positionProperty = new Property<IPair>().setConvention(new IPair());
    protected final Property<IPair> sizeProperty = new Property<IPair>().setConvention(new IPair());

    public void setPosition(int x, int y) {
        positionProperty.get().set(x, y);
    }

    public void setPosition(@NotNull IPair position) {
        positionProperty.set(position);
    }
}
