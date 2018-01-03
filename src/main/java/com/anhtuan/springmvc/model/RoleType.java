package com.anhtuan.springmvc.model;

public enum RoleType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    private String roleType;

    private RoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
