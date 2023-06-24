package com.lamp.atom.service.operator.domain.kubernetes;

/**
 * @author jefftlin
 * @date 2023/4/15
 *
 * atom-k8s的通用信息
 */
public interface AbstractOperatorRuntimeInfo {

    /**
     * 实验ID
     */
    Long getExamId();

    /**
     * 算子ID
     * @return
     */
    Long getOperatorId();

    /**
     * 算子名
     * @return
     */
    String getOperatorName();

}
