package io.github.mjaroslav.ihategui.core.util;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("UnusedReturnValue") // Builder-like calling of methods
@NoArgsConstructor
@EqualsAndHashCode(of = {"value", "convention", "conventionProperty"})
@ToString(of = {"value", "convention", "conventionProperty"})
public class Property<T> {
    protected final Consumer<Runnable> SHARED = runnable -> {
        val prevValue = get();
        runnable.run();
        val nextValue = get();
        if (!Objects.equals(prevValue, nextValue)) triggerListeners(nextValue);
    };

    protected final Set<Consumer<@NotNull T>> listeners = new HashSet<>();
    protected T value;
    protected T convention;
    protected Property<T> conventionProperty;

    public Property(T value) {
        this.value = value;
    }

    public T get() {
        return conventionProperty != null ? conventionProperty.get() : value != null ? value : convention;
    }

    public @NotNull Property<T> set(@NotNull T newValue) {
        SHARED.accept(() -> value = newValue);
        return this;
    }

    public @NotNull Property<T> setConvention(@NotNull T convention) {
        SHARED.accept(() -> this.convention = convention);
        return this;
    }

    public @NotNull Property<T> setConventionProperty(@Nullable Property<T> conventionProperty) {
        SHARED.accept(() -> {
            if (this.conventionProperty != null) listeners.forEach(this.conventionProperty::unregisterListener);
            this.conventionProperty = conventionProperty;
            if (conventionProperty != null) listeners.forEach(this.conventionProperty::registerListener);
        });
        return this;
    }

    public @NotNull Property<T> registerListener(@NotNull Consumer<T> listener) {
        listeners.add(listener);
        return this;
    }

    public @NotNull Property<T> unregisterListener(@NotNull Consumer<T> listener) {
        listeners.remove(listener);
        return this;
    }

    protected void triggerListeners(@NotNull T value) {
        listeners.forEach(listener -> listener.accept(value));
    }
}
