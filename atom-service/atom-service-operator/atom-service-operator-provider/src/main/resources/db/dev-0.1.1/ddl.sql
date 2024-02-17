-- 空间信息表
CREATE TABLE `organization` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父id',
  `organization_name` varchar(255) NOT NULL DEFAULT '' COMMENT '组织名',
  `organization_alias` varchar(31) NOT NULL DEFAULT '' COMMENT '组织别名',
  `organization_type` varchar(31) NOT NULL DEFAULT '' COMMENT '组织类型',
  `explaination` varchar(31) NOT NULL DEFAULT '' COMMENT '说明',
  `create_id` varchar(31) NOT NULL DEFAULT '' COMMENT '创建人ID',
  `create_name` varchar(31) NOT NULL DEFAULT '' COMMENT '创建名',
  `owner_id` varchar(31) NOT NULL DEFAULT '' COMMENT '拥有人ID',
  `owner_name` varchar(31) NOT NULL DEFAULT '' COMMENT '拥有人名',
  `organization_status` enum('CREATING','CREATE_FINISH','CREATE_CANCEL') NOT NULL COMMENT '组织状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 节点表
CREATE TABLE `node` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `node_template_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '节点模板',
  `node_name` varchar(31) NOT NULL DEFAULT '' COMMENT '节点名',
  `node_type` enum('CUSTOM','SYSTEM','OTHER') NOT NULL COMMENT '算子来源类型',
  `node_model` enum('OFFLINE','REALTIME','NEAR_REALTIME') NOT NULL COMMENT '算子运行模式',
  `node_epoch` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '执行次数',
  `node_plan_runtimes` varchar(31) NOT NULL DEFAULT '' COMMENT '训练预计时长',
  `node_status` enum('EDITING') NOT NULL COMMENT '节点状态',
  `operator_priority` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '训练优先级',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 算子表
CREATE TABLE `operator` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '算子id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `operator_template_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '算子模板',
  `operator_name` varchar(31) NOT NULL DEFAULT '' COMMENT '算子名',
  `operator_source_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '算子id',
  `operator_source_type` enum('CUSTOM','SYSTEM','OTHER') NOT NULL COMMENT '算子来源类型',
  `operator_runtime_type` enum('TRAIN','REASONING','FEATURES','PYTHON','FLINK','PY_LINK','ALINK','PYTHON_DATA') NOT NULL COMMENT '算子类型',
  `operator_model` enum('OFFLINE','REALTIME','NEAR_REALTIME') NOT NULL COMMENT '算子运行模式',
  `level` int(11) NOT NULL DEFAULT '3',
  `resources_account_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '资源账户id',
  `code_mode` enum('PIP','GIT','CODE') NOT NULL DEFAULT 'PIP' COMMENT '加载模式',
  `code_address` varchar(255) NOT NULL DEFAULT '' COMMENT '加载地址',
  `code_version` varchar(31) NOT NULL DEFAULT '' COMMENT '代码版本',
  `module_name` varchar(31) NOT NULL DEFAULT '' COMMENT '模块名',
  `execute_object` varchar(31) NOT NULL DEFAULT '' COMMENT '执行对象',
  `operator_conf` varchar(1023) NOT NULL DEFAULT '' COMMENT '算子配置',
  `environment_conf` varchar(1023) NOT NULL DEFAULT '' COMMENT '算子配置',
  `model_conf` varchar(1023) NOT NULL DEFAULT '' COMMENT '模型配置',
  `operator_epoch` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '训练轮数',
  `operator_plan_runtimes` varchar(31) NOT NULL DEFAULT '' COMMENT '训练预计时长',
  `operator_runtime_status` enum('EDITING','QUEUING','TRAINING','TESTING','EDIT_CANCEL','QUEUE_CANCEL','OCCUPYING','TRAIN_AUTO_FINISH','TRAIN_FINISH','TRAIN_EXCEPTION','SERVICE_EXCEPTION') NOT NULL COMMENT '训练状态',
  `operator_priority` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '训练优先级',
  `deploy_type` enum('NOT_DEPLOY','TOUCH','AUTO') NOT NULL COMMENT '部署类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 运行实例表
CREATE TABLE `runtime` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '算子id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `case_source_type` enum('SERVICE','NODE') NOT NULL DEFAULT 'SERVICE' COMMENT '来源类型（服务，节点）',
  `source_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '服务配置id',
  `node_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '节点id',
  `server_ip` varchar(31) NOT NULL DEFAULT '' COMMENT '服务器IP',
  `server_port` varchar(31) NOT NULL DEFAULT '' COMMENT '服务器端口',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `estimate_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预计开始时间',
  `estimate_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预计结束时间',
  `operator_runtime_status` enum('EDITING','QUEUING','TRAINING','TESTING','EDIT_CANCEL','QUEUE_CANCEL','OCCUPYING','TRAIN_AUTO_FINISH','TRAIN_FINISH','TRAIN_EXCEPTION','SERVICE_EXCEPTION') NOT NULL COMMENT '运行状态',
  `label` varchar(63) NOT NULL DEFAULT '' COMMENT '服务标签',
  `start_id` bigint(20) unsigned NOT NULL COMMENT '启动人id',
  `start_name` varchar(31) NOT NULL COMMENT '启动人名',
  `end_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '关闭人id',
  `end_name` varchar(31) NOT NULL DEFAULT '' COMMENT '关闭人名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 模型表
CREATE TABLE `model` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '模型id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `node_id` bigint(20) DEFAULT '0' COMMENT '节点id',
  `runtime_id` bigint(20) DEFAULT '0' COMMENT '运行实例id',
  `operator_id` bigint(20) DEFAULT '0' COMMENT '训练id',
  `model_create_type` enum('REASON','TRAIN','UPLOAD') NOT NULL COMMENT '模型创建类型',
  `model_name` varchar(63) NOT NULL DEFAULT '' COMMENT '模型名',
  `model_version` varchar(31) NOT NULL DEFAULT '' COMMENT '模型版本名',
  `model_score` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '打分',
  `model_type` enum('PRE_TRAINING','TERMINAL','USER','COMMON') NOT NULL COMMENT '模型类型',
  `model_technology_type` enum('MULTI_MODE','NLP') DEFAULT NULL,
  `model_address` varchar(255) NOT NULL DEFAULT '' COMMENT '模型地址',
  `model_status` varchar(31) NOT NULL DEFAULT '已生成' COMMENT '模型状态',
  `connect_id` bigint(20) unsigned NOT NULL COMMENT '连接id',
  `connect_status` varchar(31) NOT NULL DEFAULT '' COMMENT '连接状态',
  `operator_result` enum('CONTAIN_OPTIMAL','NOT_CONTAIN_OPTIMAL') NOT NULL COMMENT '训练结果',
  `resource_type` varchar(31) NOT NULL DEFAULT '' COMMENT '资源类型',
  `resource_value` varchar(31) NOT NULL DEFAULT '' COMMENT '资源值',
  `resource_size` varchar(31) NOT NULL DEFAULT '' COMMENT '资源大小',
  `produce_way` varchar(31) NOT NULL DEFAULT '' COMMENT '产生方式',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_model_id` (`id`),
  KEY `idx_client_id` (`connect_id`),
  KEY `idx_train_id` (`operator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 连接表
CREATE TABLE `connection` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '源数据id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `operation_type` varchar(255) NOT NULL DEFAULT '' COMMENT '操作类型',
  `connect_name` varchar(255) NOT NULL COMMENT '数据源名',
  `connect_type` enum('FILE','MYSQL','REDIS','S3','ALIYUNOSS') NOT NULL COMMENT '源类型：MySQL，Redis',
  `connect_addr` varchar(255) NOT NULL COMMENT '源数据地址',
  `connect_account` varchar(63) NOT NULL DEFAULT '' COMMENT '源登录账户',
  `connect_password` varchar(63) NOT NULL DEFAULT '' COMMENT '源登录密码',
  `connect_space` varchar(63) NOT NULL DEFAULT '' COMMENT '源登录空间：关系型数据库的数据库，oss的bucket,redis的index,es的index',
  `mode` varchar(255) NOT NULL COMMENT '模式',
  `colony_type` enum('SINGLE','GROUP') NOT NULL COMMENT '集群模式',
  `source_conf` json NOT NULL COMMENT '源数据配置',
  `source_route` varchar(255) NOT NULL COMMENT '源数据路径',
  `source_size` varchar(31) NOT NULL DEFAULT '0' COMMENT '源数据大小',
  `source_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '源数据条数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据源表
CREATE TABLE `datasource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据源ID',
  `space_id` bigint(20) unsigned NOT NULL COMMENT '算子ID',
  `connection_id` bigint(20) unsigned NOT NULL COMMENT '连接ID',
  `connection_name` varchar(45) NOT NULL COMMENT '连接名',
  `source_name` varchar(45) NOT NULL COMMENT '数据源名',
  `source_type` enum('INPUT','OUTPUT') NOT NULL COMMENT '数据源类型：输入、输出',
  `source_space` varchar(31) NOT NULL DEFAULT '' COMMENT '源空间类型',
  `source_conf` varchar(255) NOT NULL DEFAULT '' COMMENT '数据源配置',
  `task_init_execute` varchar(255) NOT NULL DEFAULT '' COMMENT '初始化',
  `operate_execute_before` varchar(255) NOT NULL DEFAULT '' COMMENT '任务开始',
  `data_execute_before` varchar(255) NOT NULL DEFAULT '' COMMENT '数据执行之前',
  `operate_execute` varchar(255) NOT NULL DEFAULT '' COMMENT '执行内容',
  `data_execute_after` varchar(255) NOT NULL DEFAULT '' COMMENT '数据执行之后',
  `operate_execute_after` varchar(255) NOT NULL DEFAULT '' COMMENT '任务结束',
  `data_format` varchar(255) NOT NULL DEFAULT '' COMMENT '数据格式',
  `operator_read_num` int(11) NOT NULL COMMENT '算子从source 一次读取数量',
  `connect_read_num` int(11) NOT NULL COMMENT '从连接中一次读取数据量',
  `disposable` tinyint(1) NOT NULL COMMENT 'source从connect是否一次行读取',
  `paginate_read_num` int(11) NOT NULL COMMENT '分页加载数量',
  `async_load` tinyint(1) NOT NULL COMMENT '是否异步从connect读取数据',
  `order` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 服务配置表
CREATE TABLE `service_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务id',
  `si_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '服务id',
  `space_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '空间id',
  `si_name` varchar(31) NOT NULL DEFAULT '0' COMMENT '服务名',
  `si_type` varchar(31) NOT NULL COMMENT '服务类型',
  `si_runtime_pattern` enum('COMMAND','KUBERNETES') NOT NULL COMMENT '服务运行模式',
  `si_node_num` int(11) NOT NULL DEFAULT '1' COMMENT '创建几个节点',
  `si_image_name` int(11) NOT NULL COMMENT '镜像名',
  `si_cpu` int(11) NOT NULL COMMENT '服务cpu配置量',
  `si_gpu` int(11) NOT NULL COMMENT '服务gpu配置量',
  `si_memory` int(11) NOT NULL COMMENT '服务内存配置量，单位是M',
  `si_display_memory` int(11) NOT NULL COMMENT '服务现存配置量',
  `si_label` varchar(63) NOT NULL DEFAULT '' COMMENT '服务标签',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 资源关系表
CREATE TABLE `resource_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源关系ID',
  `relate_id` bigint(20) unsigned NOT NULL COMMENT '关联ID',
  `relate_type` enum('OPERATOR','DATASOURCE','MODEL','CONNECTION','NODE','ORGANIZATION','SERVICE_INFO','MAX_SERVICE_INFO','MIN_SERVICE_INFO') NOT NULL COMMENT '关联类型',
  `be_related_id` bigint(20) unsigned NOT NULL COMMENT '被关联ID',
  `be_related_type` enum('OPERATOR','DATASOURCE','MODEL','CONNECTION','NODE','ORGANIZATION','SERVICE_INFO','MAX_SERVICE_INFO','MIN_SERVICE_INFO') NOT NULL COMMENT '被关联类型',
  `relation_type` enum('NODE_RELATION','RESOURCE_RELATION') NOT NULL COMMENT '关系类型（节点关系、资源关系）',
  `relation_status` varchar(31) NOT NULL DEFAULT '' COMMENT '关联状态',
  `order` int(11) NOT NULL DEFAULT '1' COMMENT '顺序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
