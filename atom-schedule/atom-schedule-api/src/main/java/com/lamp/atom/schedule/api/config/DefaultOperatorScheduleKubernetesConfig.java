/*
 *Copyright (c) [Year] [name of copyright holder]
 *[Software Name] is licensed under Mulan PubL v2.
 *You can use this software according to the terms and conditions of the Mulan PubL v2.
 *You may obtain a copy of Mulan PubL v2 at:
 *         http://license.coscl.org.cn/MulanPubL-2.0
 *THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 *EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 *MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 *See the Mulan PubL v2 for more details.
 */
package com.lamp.atom.schedule.api.config;

import com.lamp.atom.schedule.api.deploy.KubernetesOperatorConstants;
import lombok.Setter;

@Setter
public class DefaultOperatorScheduleKubernetesConfig extends AtomScheduleKubernetesConfig implements OperatorScheduleKubernetesConfig {

	private String namespace = KubernetesOperatorConstants.DEFAULT_NAMESPACE;

	private String cpuContainerName = KubernetesOperatorConstants.DEFAULT_CPU_CONTAINER_NAME;

	private String gpuContainerName = KubernetesOperatorConstants.DEFAULT_GPU_CONTAINER_NAME;

	public DefaultOperatorScheduleKubernetesConfig() {}

	public DefaultOperatorScheduleKubernetesConfig(AtomScheduleKubernetesConfig atomScheduleKubernetesConfig) {
		this.setUser(atomScheduleKubernetesConfig.isUser());
		this.setMasterUrl(atomScheduleKubernetesConfig.getMasterUrl());
		this.setConfigYaml(atomScheduleKubernetesConfig.getConfigYaml());
		this.setConfigName(atomScheduleKubernetesConfig.getConfigName());
	}

    @Override
	public String getNamespace() {
		return namespace;
	}

    @Override
	public String getCpuContainerName() {
		return cpuContainerName;
	}

    @Override
	public String getGpuContainerName() {
		return gpuContainerName;
	}
}
