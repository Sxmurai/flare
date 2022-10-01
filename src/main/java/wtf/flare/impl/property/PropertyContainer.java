package wtf.flare.impl.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyContainer {
    private final List<Property> propertyList = new ArrayList<>();
    private final Map<String, Property> propertyNameMap = new HashMap<>();

    public void offerProperties(Property... properties) {
        for (Property property : properties) {
            propertyList.add(property);

            for (String alias : property.getAliases()) {
                propertyNameMap.put(alias.toLowerCase(), property);
            }
        }
    }

    public <T extends Property> T getProperty(String name) {
        return (T) propertyNameMap.getOrDefault(name.toLowerCase(), null);
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }
}
