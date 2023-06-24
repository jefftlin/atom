package com.lamp.atom.service.operator.domain.kubernetes;

import com.lamp.atom.service.operator.domain.states.PodRuntimeState;

/**
 * @author jefftlin
 * @date 2023/4/15
 */
public class PodOperatorRuntimeInfo extends AbstractOperatorK8sRuntimeInfo {

    /**
     * Pod属性
     */
    private PodRuntimeState state;

    private String podName;

    private String label;

    private String cpuUsage;

    private String gpuUsage;

    private String menOccupied;

    public PodOperatorRuntimeInfo() {
        super();
    }

    public PodOperatorRuntimeInfo(String scheduleNode, String namespcae,
                                  Long examId, Long operatorId, String operatorName) {
        super(scheduleNode, namespcae, ScheduleKind.POD, examId, operatorId, operatorName);
    }
}
