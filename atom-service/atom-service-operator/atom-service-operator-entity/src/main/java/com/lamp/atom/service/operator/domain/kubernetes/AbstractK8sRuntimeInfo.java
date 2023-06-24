package com.lamp.atom.service.operator.domain.kubernetes;

/**
 * @author jefftlin
 * @date 2023/6/23
 */
public interface AbstractK8sRuntimeInfo {

    /**
     * 调度节点
     */
    String getScheduleNode();

    /**
     * 命名空间
     */
    String getNamespace();

    /**
     * 类型：Job/Deployment
     */
    ScheduleKind getScheduleKind();

}
