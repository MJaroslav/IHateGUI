package io.github.mjaroslav.ihategui.core;

import io.github.mjaroslav.sharedjava.tuple.pair.IPair;
import io.github.mjaroslav.sharedjava.util.Property;
import org.jetbrains.annotations.NotNull;

public interface Root {
    @NotNull Property<IPair> getSize();

    Container getContainer();

    void setContainer(@NotNull Container container);

    Container getSelectedContainer();

    void setSelectedContainer(Container container);

    Node getSelectedNode();

    void setSelectedNode(Node node);

    void initialize();
}
