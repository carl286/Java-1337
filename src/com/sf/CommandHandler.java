package com.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandHandler {
    private static final String DEPEND = "DEPEND";
    private static final String INSTALL = "INSTALL";
    private static final String REMOVE = "REMOVE";
    private static final String LIST = "LIST";
    private static final String END = "END";

    private Map<String, Component> componentMap;
    private List<Component> componentsInstalledList;
    private boolean stoped;

    public CommandHandler() {
        componentMap = new HashMap<>();
        stoped = false;
        componentsInstalledList = new ArrayList<>();
    }

    private void handleDependCommand(String upstreamComponentName, String [] dependencies) {

        Component upstreamComponent = componentMap.getOrDefault(upstreamComponentName, null);
        if (upstreamComponent != null) {
            //get all components that depend on upstreamComponent directly or indirectly
            Set<Component> upStreamDependencies = new HashSet<>();
            LinkedList<Component> queue = new LinkedList<>();
            queue.addAll(upstreamComponent.getUpStreamDependencies());

            while (!queue.isEmpty()) {
                Component upstreamDependency = queue.removeFirst();
                if (!upStreamDependencies.contains(upstreamDependency)) {
                    upStreamDependencies.add(upstreamDependency);
                    queue.addAll(upstreamDependency.getUpStreamDependencies());
                }
            }

            for(String dependencyName : dependencies) {
                Component dependencyCompoent = componentMap.getOrDefault(dependencyName, null);
                if (dependencyCompoent != null && upStreamDependencies.contains(dependencyCompoent)) {
                    System.out.println(dependencyName + " depends on " + upstreamComponentName + ", ignoring command");

                    return;
                }
            }
        } else {
            upstreamComponent = new Component(upstreamComponentName);
            componentMap.put(upstreamComponentName, upstreamComponent);
        }

        //add new component
        for (String dependencyName : dependencies) {
            Component dependencyCompoent = componentMap.get(dependencyName);
            if (dependencyCompoent == null) {
                dependencyCompoent = new Component(dependencyName);
                componentMap.put(dependencyName, dependencyCompoent);
            }

            dependencyCompoent.addUpstreamDependency(upstreamComponent);
            upstreamComponent.addDownstreamDependency(dependencyCompoent);
        }
    }

    /**
     *
     * @param componentToInstall component to be installed
     */
    private void installComponent(Component componentToInstall) {
        if (componentToInstall.getStatus() == Component.INSTALLED)
            return;

        for (Component component : componentToInstall.getDownStreamDependencies()) {
            installComponent(component);
        }

//        componentToInstall.setStatus(Component.INSTALLED);
        System.out.println("Installing " + componentToInstall.getComponentName());
        componentsInstalledList.add(componentToInstall);
        componentToInstall.installComponent();
    }

    private void handleInstallCommand(String componentName) {
        Component component = componentMap.getOrDefault(componentName, null);
        if (component == null) {
            component = new Component(componentName);
            componentMap.put(componentName, component);
            System.out.println("Installing " + componentName);
            componentsInstalledList.add(component);
            component.installComponent();
        } else if (component.getStatus() == Component.INSTALLED) {
            System.out.println(componentName + " is already installed");
        } else {
            component.setCanBeRemovedAutomatically(false);
            installComponent(component);
        }
    }

    //componentToRemove is not needed and can be removed
    private void removeComponent(Component componentToRemove) {
//        if (componentToRemove.getStatus() == Component.NOT_INSTALLED)
//            return true;
//
//        if (componentToRemove.stillNeeded())
//            return false;


        System.out.println("Removing " + componentToRemove.getComponentName());

        componentsInstalledList.remove(componentToRemove);
        componentToRemove.uninstallComponent();
        Set<Component> candidatesToRemove = new HashSet<>(componentToRemove.getDownStreamDependencies());

        boolean newComponentedGotRemoved = true;
        while (newComponentedGotRemoved) {
            Set<Component> componentsRemoved = new HashSet<>();

            Iterator<Component> componentIterator = candidatesToRemove.iterator();

            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component.getCanBeRemovedAutomatically() && !component.stillNeeded()) {
                    System.out.println("Removing " + component.getComponentName());

                    componentsInstalledList.remove(component);
                    component.uninstallComponent();
                    componentsRemoved.add(component);
                }
            }

            newComponentedGotRemoved = componentsRemoved.size() > 0;
            candidatesToRemove.removeAll(componentsRemoved);
        }
//        return true;
    }

    private void handleRemoveCommand(String componentName) {
        Component component = componentMap.getOrDefault(componentName, null);
        if (component == null || component.getStatus() == Component.NOT_INSTALLED) {
            System.out.println(component + " is not installed");
        } else {
            if (component.stillNeeded()) {
                System.out.println(componentName + " is still needed");
            } else {
                component.setCanBeRemovedAutomatically(true);
                removeComponent(component);
            }
        }
    }

    private void handleListCommand() {
        for (Component component : componentsInstalledList) {
                System.out.println(component);
        }

    }

    private void handleEndCommand() {
        stoped = true;
    }

    public boolean isStoped() {
        return stoped;
    }
    public void handleCommand(String [] inputs){
        if (inputs.length > 0) {
            String command = inputs[0];
            if (command.equals(DEPEND)) {
                if (inputs.length < 3) {
                    System.out.println("Not enough parameters for DEPEND command");
                }
                handleDependCommand(inputs[1], Arrays.copyOfRange(inputs, 2, inputs.length));
            } else if (command.equals(INSTALL)) {
                if (inputs.length != 2) {
                    System.out.println("Not enough parameters for INSTALL command");
                }

                handleInstallCommand(inputs[1]);
            } else if (command.equals(REMOVE)) {
                if (inputs.length != 2) {
                    System.out.println("Not enough parameters for REMOVE command");
                }

                handleRemoveCommand(inputs[1]);
            } else if (command.equals(LIST)) {
                handleListCommand();
            } else {
                handleEndCommand();
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
