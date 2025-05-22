package com.sbtech.erp.permission.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Permission {

    private final String resource;
    private final Action action;
    private final String description;

    @Builder(access = AccessLevel.PROTECTED)
    public Permission(String resource, Action action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
    }

    public static Permission create(String resource, Action action, String description){
        return Permission.builder()
                .resource(resource)
                .action(action)
                .description(description)
                .build();
    }

    public boolean matches(String targetResource, Action targetAction) {
        return this.resource.equals(targetResource) && this.action == targetAction;
    }
}
