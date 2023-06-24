package com.lamp.atom.service.operator.domain.kubernetes;

/**
 * @author jefftlin
 * @date 2023/6/23
 */
public enum ScheduleKind {

    POD("pod"),

    SERVICE("service");

    String kind;

    ScheduleKind() {
    }

    ScheduleKind(String kind) {
        this.kind = kind;
    }

}
