package com.lamp.atom.schedule.k8s.operator;

import java.util.ArrayList;
import java.util.List;

import com.lamp.atom.schedule.api.runtime.RuntimeExample;
import com.lamp.atom.schedule.api.runtime.RuntimeStatus;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeploymentRuntimeExample implements RuntimeExample {

	private Deployment deployment;

	public static List<RuntimeExample> createExample(List<Deployment> deploymentList) {
		List<RuntimeExample> list = new ArrayList<RuntimeExample>();
		for (Deployment deployment : deploymentList) {
			list.add(new DeploymentRuntimeExample(deployment));
		}
		return list;
	}

	@Override
	public String getName() {
		return deployment.getFullResourceName();
	}

	@Override
	public RuntimeStatus getRuntimeStatus() {
		return null;
	}
}
