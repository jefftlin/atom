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
package com.lamp.atom.schedule.core;

import com.lamp.atom.schedule.api.AtomOperatorSchedule;
import com.lamp.atom.schedule.api.AtomServiceSchedule;
import com.lamp.atom.schedule.api.Schedule;
import com.lamp.atom.schedule.api.ScheduleReturn;
import com.lamp.atom.schedule.api.config.OperatorScheduleConfig;
import com.lamp.atom.schedule.k8s.operator.OperatorKubernetesSchedule;
import com.lamp.atom.schedule.python.operator.rpc.OperatorRpcSchedule;
import com.lamp.atom.service.domain.OperatorRuntimeType;

import java.util.HashMap;
import java.util.Map;

/**
 * 上层决定调用那个
 * @author laohu
 *
 */
public class AtomScheduleService implements AtomOperatorSchedule, AtomServiceSchedule {

	private OperatorKubernetesSchedule kubernetesSchedule;
	
	private OperatorRpcSchedule rpcSchedule;
	
	private OperatorScheduleConfig operatorScheduleConfig;

	private Map<OperatorRuntimeType, AtomOperatorSchedule> atomOperatorScheduleMap = new HashMap<>();

	public AtomScheduleService(OperatorScheduleConfig operatorScheduleConfig) throws Exception {
		this.operatorScheduleConfig = operatorScheduleConfig;
		if(operatorScheduleConfig.getOperatorScheduleKubernetesConfig().isUser()) {
			kubernetesSchedule = new OperatorKubernetesSchedule(this.operatorScheduleConfig.getOperatorScheduleKubernetesConfig());
		}
		rpcSchedule = new OperatorRpcSchedule(this.operatorScheduleConfig.getOperatorScheduleRpcConfig());

		//训练算子
		atomOperatorScheduleMap.put(OperatorRuntimeType.TRAIN, rpcSchedule);
		//推理算子
		atomOperatorScheduleMap.put(OperatorRuntimeType.REASON, kubernetesSchedule);
	}
	
	
	@Override
	public void createService(Schedule schedule) {
		kubernetesSchedule.createService(schedule);
	}

	@Override
	public void closeService(Schedule schedule) {
		kubernetesSchedule.closeService(schedule);
	}

	/**
	 * 1、训练 =》 k8s调度
	 * 2、推理 =》 RPC调度
	 *
 	 * @param schedule
	 * @return
	 */
	@Override
	public ScheduleReturn createOperators(Schedule schedule) {
		return atomOperatorScheduleMap.get(schedule.getOperatorRuntimeType()).createOperators(schedule);
	}

	@Override
	public void startOperators(Schedule schedule) {
		atomOperatorScheduleMap.get(schedule.getOperatorRuntimeType()).startOperators(schedule);
	}

	@Override
	public void suspendOperators(Schedule schedule) {
		atomOperatorScheduleMap.get(schedule.getOperatorRuntimeType()).suspendOperators(schedule);
	}

	@Override
	public void uninstallOperators(Schedule schedule) {
		atomOperatorScheduleMap.get(schedule.getOperatorRuntimeType()).uninstallOperators(schedule);
	}

}
