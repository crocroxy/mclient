package gg.croxy.settings;

import java.util.function.Predicate;

public class Setting<T> {
    private final String name;
    private final String description;
    private T value;
    private final T defaultValue;
    private T minimum;
    private T maximum;
    private Predicate<T> validator;

    public Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public Setting(String name, String description, T defaultValue, T minimum, T maximum) {
        this(name, description, defaultValue);
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public void setValue(T value) {
        if (validator != null && !validator.test(value)) return;
        if (minimum != null && value instanceof Comparable) {
            if (((Comparable<T>) value).compareTo(minimum) < 0) return;
        }
        if (maximum != null && value instanceof Comparable) {
            if (((Comparable<T>) value).compareTo(maximum) > 0) return;
        }
        this.value = value;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public T getValue() { return value; }
    public T getDefaultValue() { return defaultValue; }
    public T getMinimum() { return minimum; }
    public T getMaximum() { return maximum; }
}