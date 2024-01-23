package io.github.mjaroslav.ihategui.core.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MonoPair<T> extends Pair<T, T> {
    public void set(T a, T b) {
        setA(a);
        setB(b);
    }
}
