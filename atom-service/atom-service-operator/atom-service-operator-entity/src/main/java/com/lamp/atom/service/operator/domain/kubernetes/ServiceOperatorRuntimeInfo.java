package com.lamp.atom.service.operator.domain.kubernetes;

import com.lamp.atom.service.operator.domain.states.ServiceRuntimeState;

/**
 * @author jefftlin
 * @date 2023/4/15
 */
public class ServiceOperatorRuntimeInfo extends AbstractOperatorK8sRuntimeInfo {

    /**
     * Service属性
     */
    private ServiceRuntimeState state;

    private String serviceName;

    private String serviceType;

    private String clusterIp;

    public ServiceOperatorRuntimeInfo() {
        super();
    }

    public ServiceOperatorRuntimeInfo(String scheduleNode, String namespcae,
                                      Long examId, Long operatorId, String operatorName) {
        super(scheduleNode, namespcae, ScheduleKind.SERVICE, examId, operatorId, operatorName);
    }

}
