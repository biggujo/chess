package com.models.piecesfield;

public class ComponentsManager {
    private Components components;

    public static ComponentsManager from(Field field) {
        return new ComponentsManager(Components.from(field));
    }

    private ComponentsManager(Components components) {
        this.components = components;
    }

    public void updateWith(Field field) {
        this.components = Components.from(field);
    }

    public Components getComponents() {
        return components;
    }
}
