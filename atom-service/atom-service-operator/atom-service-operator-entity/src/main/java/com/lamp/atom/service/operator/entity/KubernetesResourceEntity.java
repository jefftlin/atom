package com.lamp.atom.service.operator.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KubernetesResourceEntity extends BaseEntity {

    private static final long serialVersionUID = 2636535758820866889L;

    /**
     * 集群名
     */
    private String masterName;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * Pod名字
     */
    private String podName;

    /**
     * 运行节点
     */
    private String node;

}
