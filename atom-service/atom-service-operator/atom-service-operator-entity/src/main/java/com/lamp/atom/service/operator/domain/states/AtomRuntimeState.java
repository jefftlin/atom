package com.lamp.atom.service.operator.domain.states;

import lombok.Getter;

@Getter
public enum AtomRuntimeState {

    UNSTARTED("UNSTARTED", "未启动"),
    STARTING("STARTING", "启动中"),
    RUNNING("RUNNING", "运行中"),
    STOPPING("STOPPING", "停止中"),
    STOPPED("STOPPED", "已停止"),
    SUCCESS("SUCCESS", "已完成"),
    FAILED("FAILED", "已失败");

    AtomRuntimeState(String state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    /**
     * State in kubernetes schedule
     */
    private String state;

    /**
     * description
     */
    private String desc;

}
