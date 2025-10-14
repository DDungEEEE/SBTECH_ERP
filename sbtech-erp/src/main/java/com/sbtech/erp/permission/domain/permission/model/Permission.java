package com.sbtech.erp.permission.domain.permission.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Permission {

    private final Long id;
    private final String resource;
    private final Action action;
    private final String description;

    @Builder(access = AccessLevel.PRIVATE)
    public Permission(Long id, String resource, Action action, String description) {
        this.id = id;
        this.resource = resource;
        this.action = action;
        this.description = description;
    }

    public static Permission create(Long id,String resource, Action action, String description){
        return Permission.builder()
                .id(id)
                .resource(resource)
                .action(action)
                .description(description)
                .build();
    }

    public boolean matches(String targetResource, Action targetAction) {
        return this.resource.equals(targetResource) && this.action == targetAction;
    }
}
