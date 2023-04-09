package com.lamp.atom.schedule.api.config;

import com.lamp.atom.schedule.api.deploy.KubernetesOperatorConstants;

public class ReasonOperatorScheduleKubernetesConfig extends AtomScheduleKubernetesConfig implements OperatorScheduleKubernetesConfig {

    private String namespace = KubernetesOperatorConstants.DEFAULT_NAMESPACE + "reason";

    private String cpuContainerName = KubernetesOperatorConstants.DEFAULT_CPU_CONTAINER_NAME + "reason";

    private String gpuContainerName = KubernetesOperatorConstants.DEFAULT_GPU_CONTAINER_NAME + "reason";

    public ReasonOperatorScheduleKubernetesConfig() {}

    public ReasonOperatorScheduleKubernetesConfig(AtomScheduleKubernetesConfig atomScheduleKubernetesConfig) {
        this.setUser(atomScheduleKubernetesConfig.isUser());
        this.setMasterUrl(atomScheduleKubernetesConfig.getMasterUrl());
        this.setConfigYaml(atomScheduleKubernetesConfig.getConfigYaml());
        this.setConfigName(atomScheduleKubernetesConfig.getConfigName());
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getCpuContainerName() {
        return this.cpuContainerName;
    }

    @Override
    public String getGpuContainerName() {
        return this.gpuContainerName;
    }
}
