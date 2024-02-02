package io.github.mjaroslav.ihategui.core;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public abstract class Container extends Node {
    protected final Set<Node> childs = new HashSet<>();

    public void addNode(@NotNull Node node) {
        childs.add(node);
    }

    public void removeNode(@Nullable Node node) {
        childs.remove(node);
    }

    public abstract void calculateLayout();
}
