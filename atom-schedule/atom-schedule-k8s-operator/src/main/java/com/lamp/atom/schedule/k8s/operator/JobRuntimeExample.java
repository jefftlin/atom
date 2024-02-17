package com.lamp.atom.schedule.k8s.operator;

import java.util.ArrayList;
import java.util.List;

import com.lamp.atom.schedule.api.runtime.RuntimeExample;
import com.lamp.atom.schedule.api.runtime.RuntimeStatus;

import io.fabric8.kubernetes.api.model.batch.v1.Job;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JobRuntimeExample implements RuntimeExample{

	private Job job;
	
	
	public static  List<RuntimeExample> createExample(List<Job> jobList){
		List<RuntimeExample> list = new ArrayList<RuntimeExample>();
		for(Job job : jobList) {
			list.add(new JobRuntimeExample(job));
		}
		return list;
	}

	
	
	@Override
	public String getName() {
		return job.getFullResourceName();
	}

	@Override
	public RuntimeStatus getRuntimeStatus() {
		return null;
	}
}
