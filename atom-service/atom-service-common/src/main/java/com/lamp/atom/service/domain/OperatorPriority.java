package com.lamp.atom.service.domain;

public enum OperatorPriority {

    HIGH("高"),

    MIDDLE("中"),

    LOW("低");

    private String name;

    OperatorPriority(String name) {
        this.name = name;
    }
}
