package com.lamp.atom.service.operator.domain.states;

/**
 * @author jefftlin
 * @date 2023/4/15
 */
public enum ServiceRuntimeState {

    CREATING("creating"),

    RUNNING("running"),

    ABANDONED("abandoned");

    String state;

    ServiceRuntimeState() {
    }

    ServiceRuntimeState(String state) {
        this.state = state;
    }
}
