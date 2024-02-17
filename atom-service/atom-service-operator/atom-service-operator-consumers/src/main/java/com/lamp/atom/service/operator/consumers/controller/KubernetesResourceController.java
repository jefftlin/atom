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
package com.lamp.atom.service.operator.consumers.controller;

import com.lamp.atom.schedule.api.config.OperatorScheduleConfig;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RequestMapping("/kubernetesResource")
@RestController("kubernetesResourceController")
@Api(tags = {"任务事件操作接口"})
public class KubernetesResourceController {

    @Autowired
    private OperatorScheduleConfig operatorScheduleConfig;

    /**
     * 初始化Kubernetes集群信息
     */
    @PostConstruct
    public void init() {
        // 创建K8s客户端操作对象

    }

    /**
     * console：算子编辑中
     *
     * @return
     */
    @PostMapping("/queryClusterInformation")
    public void clusterInformation() {

    }

}
