package com.lamp.atom.schedule.api.config;

import com.lamp.atom.schedule.api.Schedule;
import com.lamp.atom.service.domain.OperatorRuntimeType;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.Objects;

public class OperatorScheduleKubernetesConfigFactory {

    public static class KubernetesClientInstance {

        private AtomScheduleKubernetesConfig atomScheduleKubernetesConfig;

        private KubernetesClient client;

        public KubernetesClient getInstance(AtomScheduleKubernetesConfig atomScheduleKubernetesConfig) {
            this.atomScheduleKubernetesConfig = atomScheduleKubernetesConfig;
            if (Objects.nonNull(atomScheduleKubernetesConfig.getMasterUrl())) {
                client = new DefaultKubernetesClient(atomScheduleKubernetesConfig.getMasterUrl());
            } else if (Objects.nonNull(atomScheduleKubernetesConfig.getConfigYaml())) {
                Config config = Config.fromKubeconfig(atomScheduleKubernetesConfig.getConfigYaml());
                client = new DefaultKubernetesClient(config);
            }
            return client;
        }

    }

    /**
     * Set different NameSpace、CpuContainerName and GpuContainerName by OperatorRuntimeType
     * （根据算子类型不同设置不同的工作空间、cpu容器名和gpu容器名）
     *
     * @param schedule
     * @param atomScheduleKubernetesConfig
     * @return
     */
    public static OperatorScheduleKubernetesConfig createKubernetesConfig(Schedule schedule,
                                                                          AtomScheduleKubernetesConfig atomScheduleKubernetesConfig) {
        OperatorScheduleKubernetesConfig operatorScheduleKubernetesConfig = new DefaultOperatorScheduleKubernetesConfig(atomScheduleKubernetesConfig);

        OperatorRuntimeType operatorRuntimeType = schedule.getOperatorRuntimeType();
        switch (operatorRuntimeType) {
            case TRAIN:
                operatorScheduleKubernetesConfig = new TrainOperatorScheduleKubernetesConfig(atomScheduleKubernetesConfig);
                break;
            case REASON:
                operatorScheduleKubernetesConfig = new ReasonOperatorScheduleKubernetesConfig(atomScheduleKubernetesConfig);
                break;
            default:
                break;
        }

        return operatorScheduleKubernetesConfig;
    }
}
