package com.lamp.atom.service.operator.domain.kubernetes;

import com.lamp.atom.service.operator.domain.states.AtomRuntimeState;

/**
 * @author jefftlin
 * @date 2023/4/15
 *
 * atom runtime的通用信息
 */
@Deprecated
public class AbstractAtomRuntimeInfo {

    /**
     * 服务名
     */
    private String siName;

    /**
     * 镜像名
     */
    private String siImageName;

    /**
     * 算子ID
     */
    private Long operatorId;

    /**
     * 算子名
     */
    private String operatorName;

    /**
     * 算子状态
     */
    private AtomRuntimeState atomRuntimeState;

    /**
     * CPU
     */
    private String siCpu;

    /**
     * GPU
     */
    private String siGpu;

    /**
     * Memory
     */
    private String siMemory;

}
