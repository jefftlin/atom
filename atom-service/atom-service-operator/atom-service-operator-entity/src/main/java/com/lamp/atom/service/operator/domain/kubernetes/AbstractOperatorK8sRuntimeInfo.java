package com.lamp.atom.service.operator.domain.kubernetes;

/**
 * @author jefftlin
 * @date 2023/6/24
 */
public class AbstractOperatorK8sRuntimeInfo implements AbstractK8sRuntimeInfo, AbstractOperatorRuntimeInfo {

    /**
     * K8s环境属性
     */
    private String scheduleNode;

    private String namespace;

    private ScheduleKind scheduleKind;

    /**
     * 算子属性
     */
    private Long examId;

    private Long operatorId;

    private String operatorName;

    public AbstractOperatorK8sRuntimeInfo() {
    }

    public AbstractOperatorK8sRuntimeInfo(String scheduleNode, String namespcae, ScheduleKind scheduleKind,
                                          Long examId, Long operatorId, String operatorName) {
        this.scheduleNode = scheduleNode;
        this.namespace = namespcae;
        this.scheduleKind = scheduleKind;

        this.examId = examId;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    @Override
    public String getScheduleNode() {
        return null;
    }

    @Override
    public String getNamespace() {
        return null;
    }

    @Override
    public ScheduleKind getScheduleKind() {
        return null;
    }

    @Override
    public Long getExamId() {
        return null;
    }

    @Override
    public Long getOperatorId() {
        return null;
    }

    @Override
    public String getOperatorName() {
        return null;
    }
}
