package com.sbtech.erp.permission.model;


import com.sbtech.erp.permission.domain.core.Action;

public class Permission {
    private final String resource;
    private final Action action;
    private final String description;

    public Permission(String resource, Action action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
    }

    public String getResource() { return resource; }
    public Action getAction() { return action; }
    public String getDescription() { return description; }

    public boolean matches(String targetResource, Action targetAction) {
        return this.resource.equals(targetResource) && this.action == targetAction;
    }
}
