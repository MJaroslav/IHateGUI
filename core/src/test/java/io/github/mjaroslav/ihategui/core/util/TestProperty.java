package io.github.mjaroslav.ihategui.core.util;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProperty {
    @Test
    void get_value() {
        val prop = new Property<>(1);
        assertEquals(1, prop.get(), "Property value after initialization");
        assertNotEquals(-1, prop.get(), "Property value after initialization");
        prop.set(-1);
        assertEquals(-1, prop.get(), "Property value after changing value");
        assertNotEquals(1, prop.get(), "Property value after changing value");
    }

    @Test
    void get_convention() {
        val prop = new Property<Integer>().setConvention(1);
        assertEquals(1, prop.get(), "Property value after convention initialization");
        assertNotEquals(-1, prop.get(), "Property value after convention initialization");
        prop.setConvention(-1);
        assertEquals(-1, prop.get(), "Property value after changing convention");
        assertNotEquals(1, prop.get(), "Property value after changing convention");
        prop.set(1);
        assertEquals(1, prop.get(), "Property value after changing value");
        assertNotEquals(-1, prop.get(), "Property value after changing value");
    }

    @Test
    void get_conventionProperty() {
        val sourceProp = new Property<>(1);
        val prop = new Property<Integer>().setConventionProperty(sourceProp);
        assertEquals(1, prop.get(), "Property value after source property initialization");
        assertNotEquals(-1, prop.get(), "Property value after source property initialization");
        sourceProp.set(-1);
        assertEquals(-1, prop.get(), "Property value after changing source property");
        assertNotEquals(1, prop.get(), "Property value after changing source property");
    }

    @Test
    void registerListener() {
        val prop = new Property<String>();
        assertDoesNotThrow(() -> prop.set("1"), "No listeners registered");
        prop.registerListener(newValue -> {
            throw new RuntimeException(newValue);
        });
        assertThrows(RuntimeException.class, () -> prop.set("-1"), "Listener not registered");
        assertDoesNotThrow(() -> prop.set("-1"), "Listener runs when nothing changed");
        val sourceProp = new Property<>("1");
        assertThrows(RuntimeException.class, () -> prop.setConventionProperty(sourceProp), "Source property with different value");
        assertThrows(RuntimeException.class, () -> sourceProp.set("-1"), "Changing source property value");
        assertDoesNotThrow(() -> sourceProp.set("-1"), "Listener runs when nothing changed");
    }
}
