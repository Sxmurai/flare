package wtf.flare.impl.binding;

import wtf.flare.core.FlareClient;
import wtf.flare.util.core.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class BindManager implements Wrapper {

    private final List<Binding> bindingList = new ArrayList<>();

    public BindManager() {
        FlareClient.BUS.subscribe(new BindListener(this));
    }

    public void addBinding(Binding binding) {
        bindingList.add(binding);
    }

    public List<Binding> getBindingList() {
        return bindingList;
    }
}
