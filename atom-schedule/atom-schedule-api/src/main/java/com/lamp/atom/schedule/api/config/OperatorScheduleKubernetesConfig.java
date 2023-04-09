package com.lamp.atom.schedule.api.config;

public interface OperatorScheduleKubernetesConfig {

    public String getNamespace();

    public String getCpuContainerName();

    public String getGpuContainerName();
}
