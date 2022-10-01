package wtf.flare.impl.property;

import wtf.flare.util.core.Labeled;

import java.util.function.Supplier;

public class Property<T> implements Labeled {

    private final String[] aliases;
    private T value;
    private final Number min, max;
    private Supplier<Boolean> visibility = null;

    public Property(T value, String... aliases) {
        this(value, null, null, aliases);
    }

    public Property(T value, Number min, Number max, String... aliases) {
        this.aliases = aliases;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    @Override
    public String getName() {
        return aliases[0];
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

    public Property setVisibility(Supplier<Boolean> visibility) {
        this.visibility = visibility;
        return this;
    }

    public boolean isVisible() {
        return visibility == null || visibility.get();
    }
}
