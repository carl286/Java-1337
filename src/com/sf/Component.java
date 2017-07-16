package com.sf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Component {
    public static final boolean INSTALLED = true;
    public static final boolean NOT_INSTALLED = false;

    private String componentName;
    private boolean canBeRemovedAutomatically;
    private boolean status;

    //components that depends on current compoent
    private Set<Component> upStreamDependencies;

    //components that current component depends on
    private Set<Component> downStreamDependencies;

    public Component(String name) {
        this.componentName = name;
        status = NOT_INSTALLED;
        upStreamDependencies = new HashSet<>();
        downStreamDependencies = new HashSet<>();
        canBeRemovedAutomatically = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Component) {
            Component that = (Component)obj;
            return this.componentName.equals(that.componentName);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return componentName.hashCode();
    }

    @Override
    public String toString() {
        return componentName;
    }

    public String getComponentName() {
        return this.componentName;
    }

//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    public boolean getStatus() {
        return this.status;
    }

    public void uninstallComponent() {
            this.status = NOT_INSTALLED;
    }

    public void installComponent() {
            this.status = INSTALLED;
    }

    public Set<Component> getUpStreamDependencies() {
        return this.upStreamDependencies;
    }

    public Set<Component> getDownStreamDependencies() {
        return this.downStreamDependencies;
    }

    public void addUpstreamDependency(Component component) {
        upStreamDependencies.add(component);
    }

    public void addDownstreamDependency(Component component) {
        downStreamDependencies.add(component);
    }

    public boolean stillNeeded() {
        boolean stillInUse = false;
        for (Component dependent : this.getUpStreamDependencies()) {
            if (dependent.getStatus() == Component.INSTALLED) {
                stillInUse = true;
                break;
            }
        }
        return stillInUse;
    }

    public boolean getCanBeRemovedAutomatically() {
        return this.canBeRemovedAutomatically;
    }

    public void setCanBeRemovedAutomatically(boolean canBeRemovedAutomatically) {
        this.canBeRemovedAutomatically = canBeRemovedAutomatically;
    }
}
