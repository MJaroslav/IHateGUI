package io.github.mjaroslav.ihategui.core.control;

import io.github.mjaroslav.ihategui.core.Control;
import io.github.mjaroslav.sharedjava.util.Property;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public abstract class Label extends Control {
    protected final Property<String> textProperty = new Property<>("");
    protected final Property<Boolean> wordWrapProperty = new Property<Boolean>().setConvention(false);

    public Label(@NotNull String text) {
        textProperty.set(text);
    }

    public void setText(@NotNull String text) {
        textProperty.set(text);
    }

    public String getText() {
        return textProperty.get();
    }

    public boolean isWordWrap() {
        return wordWrapProperty.get();
    }

    public void setWordWrap(boolean value) {
        wordWrapProperty.set(value);
    }
}
