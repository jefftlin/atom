package com.lamp.atom.service.operator.consumers.scheduler;

public class KubernetesResourceMaintainer {

    // 调度类 调度策略（多久一次）
    // 线程类 registry and start

    /**
     * 1、写一个定时任务去k8s中获取所有部署节点的信息，定时刷新到k8s状态表中
     * 2、根据什么维度去拿？1）命名空间 2）。。。
     *
     * NO 改成用controller查的方式去获取
     */

}
