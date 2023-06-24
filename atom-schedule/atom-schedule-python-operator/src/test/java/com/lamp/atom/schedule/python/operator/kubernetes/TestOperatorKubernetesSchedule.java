package com.lamp.atom.schedule.python.operator.kubernetes;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.lamp.atom.schedule.api.config.DefaultOperatorScheduleKubernetesConfig;
import com.lamp.atom.service.domain.OperatorRuntimeType;
import org.junit.Test;

import com.lamp.atom.schedule.api.Schedule;
import com.lamp.atom.schedule.api.deploy.Deploy;

public class TestOperatorKubernetesSchedule {

	Schedule schedule = new Schedule();

	DefaultOperatorScheduleKubernetesConfig kubernetesConfig = new DefaultOperatorScheduleKubernetesConfig();

	{
		schedule.setNodeName("test");

		Deploy deploy = new Deploy();
		deploy.setCount(1);
		schedule.setDeploy(deploy);

		Map<String, String> labels = new HashMap<>();
		labels.put("test", "test");
		schedule.setLabels(labels);

		Map<String, String> hardwareConfig = new HashMap<>();
		hardwareConfig.put("cpu", "100m");
		hardwareConfig.put("memory", "100Mi");
		schedule.setHardwareConfig(hardwareConfig);

		Map<String, String> envs = new HashMap<>();
		envs.put("nacos_config", "{'nacos_address':'127.0.0.1','nacos_namespace':'atom'}");
		schedule.setEnvs(envs);

		Map<String, String> limits = new HashMap<>();
		limits.put("cpu", "100m");
		limits.put("memory", "100Mi");
		schedule.setLimits(limits);

		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("kubernetesConfig.yaml");
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);
			kubernetesConfig.setConfigYaml(new String(data));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCreateService() throws Exception {
		OperatorKubernetesSchedule kubernetesSchedule = new OperatorKubernetesSchedule(kubernetesConfig);
		Deploy deploy = new Deploy();
		deploy.setCount(1);
		schedule.setDeploy(deploy);
		schedule.setOperatorRuntimeType(OperatorRuntimeType.REASON);
		kubernetesSchedule.createService(schedule);
	}
	
	@Test
	public void testCreateOperators() throws Exception {
		OperatorKubernetesSchedule kubernetesSchedule = new OperatorKubernetesSchedule(kubernetesConfig);

		kubernetesSchedule.createOperators(schedule);
	}
}
