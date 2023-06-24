package com.lamp.atom.service.operator.domain.states;

/**
 * @author jefftlin
 * @date 2023/4/15
 */
public enum PodRuntimeState {

    PENDING("pending"),

    RUNNING("running"),

    COMPLETED("completed"),

    FAILED("failed"),

    UNKNOWN("unKnown");

    String state;

    PodRuntimeState() {
    }

    PodRuntimeState(String state) {
        this.state = state;
    }

}
