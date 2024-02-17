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
package com.lamp.atom.schedule.k8s.operator.builder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.lamp.atom.schedule.api.Schedule;
import com.lamp.atom.schedule.api.config.OperatorScheduleKubernetesConfig;

import com.lamp.atom.schedule.api.deploy.KubernetesOperatorConstants;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarSource;
import io.fabric8.kubernetes.api.model.ObjectFieldSelector;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.batch.v1.JobBuilder;
import io.fabric8.kubernetes.api.model.batch.v1.JobFluent.MetadataNested;
import io.fabric8.kubernetes.api.model.batch.v1.JobFluent.SpecNested;
import lombok.Setter;

/**
 * 训练,数据算子,主要用于晚上
 * @author laohu
 *
 */
public class SessionOperatorKubernetesBuilder extends AbstractOperatorKubernetesBuilder<JobBuilder, Job> {

	@Setter
	private OperatorScheduleKubernetesConfig operatorKubernetesConfig;
	
	@Setter
	private Schedule schedule;


	@Override
	public void job() {
		job.withApiVersion("batch/v1");
		// 训练 是job volcano
		// 推理 是deployment , 推理还有扩容，缩容 ， 起多少个，都不一样呀
		job.withKind("Job");
	}

	@Override
	public void metadata() {
		MetadataNested<JobBuilder> metadata = job.withNewMetadata();
		// nvidia.com/gpu
		metadata
				// GPU 还是cpu镜像
				.withName(KubernetesOperatorConstants.SESSION_OPERATOR_NAME_PREFIX
					+ this.schedule.getNodeName()
					+ "-"
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())))
				// 标签，需要几个
				// 第一个 算子的名字+序列
				// 的哥
				.withLabels(schedule.getLabels());
		metadata.endMetadata();
	}

	@Override
	public void spec() {
		ResourceRequirements resourceRequirements = new ResourceRequirements();
		Map<String, Quantity> requests = new HashMap<>();
		resourceRequirements.setRequests(requests);
		for (Entry<String, String> e : schedule.getHardwareConfig().entrySet()) {
			requests.put(e.getKey(), new Quantity(e.getValue()));
		}

		Map<String, Quantity> limits = new HashMap<>();
		resourceRequirements.setLimits(limits);
		for (Entry<String, String> e : schedule.getLimits().entrySet()) {
			limits.put(e.getKey(), new Quantity(e.getValue()));
		}
		List<EnvVar> envList = new ArrayList<EnvVar>();
		for(Entry<String, String> e : schedule.getEnvs().entrySet()) {
			envList.add(new EnvVar(e.getKey(), e.getValue(), null));
		}
		envList.add(new EnvVar("docker", "true", null));
		envList.add(new EnvVar("runtime_model", "standalone", null));
		envList.add(new EnvVar("operator-data", JSON.toJSONString(schedule.getObject()), null));
		ObjectFieldSelector podIP = new ObjectFieldSelector();
		podIP.setFieldPath("status.podIP");
		podIP.setApiVersion("v1");
		EnvVarSource nodeIp = new EnvVarSource();
		nodeIp.setFieldRef(podIP);
		envList.add(new EnvVar("node_ip", null, nodeIp));
		
		ObjectFieldSelector podId = new ObjectFieldSelector();
		podId.setFieldPath("status.podIP");
		podId.setApiVersion("v1");
		EnvVarSource nodeId = new EnvVarSource();
		nodeId.setFieldRef(podId);
		envList.add(new EnvVar("node_id", null, nodeId));
		
		
		
		String value = schedule.getHardwareConfig().get("nvidia.com/gpu");
		SpecNested<JobBuilder> spec = job.withNewSpec();
		spec.withNewTemplate()
				.withNewSpec()
				.withRestartPolicy("Never")
				.withHostNetwork(true)
				.addNewContainer()
				.withName(this.schedule.getNodeName())
				.withImage(Objects.isNull(value)
						? this.operatorKubernetesConfig.getCpuContainerName()
						: this.operatorKubernetesConfig.getGpuContainerName())
				.withResources(resourceRequirements)
				.withEnv(envList)
				.endContainer()
				.endSpec()
				.endTemplate()
				.endSpec();
	}

	@Override
	public void preBuild() {
		this.job = new JobBuilder();
	}
}
