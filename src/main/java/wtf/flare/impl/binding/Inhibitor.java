package wtf.flare.impl.binding;

@FunctionalInterface
public interface Inhibitor {
    void invoke(Binding binding);
}
