package com.lamp.atom.service.operator.consumers.controller;

import com.lamp.atom.service.domain.DeployType;
import com.lamp.atom.service.domain.OperatorStatus;
import com.lamp.atom.service.operator.entity.OperatorEntity;
import com.lamp.atom.service.operator.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/operatorEvent")
@RestController("operatorEventController")
public class OperatorEventController {

    @Autowired
    @Qualifier("operatorService")
    private OperatorService operatorService;

    /**
     * console：算子编辑中
     * @param operatorEntity
     * @return
     */
    @PostMapping("/editing")
    public Integer editStart(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.EDITING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * console：算子编辑取消
     * @param operatorEntity
     * @return
     */
    @PostMapping("/editCancel")
    public Integer editCancel(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.EDIT_CANCEL);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * console：算子编辑完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/editFinish")
    public Integer editFinish(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.EDIT_FINISH);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * console：排队
     */
    @PostMapping("/queuing")
    public Integer queuing(@RequestBody OperatorEntity operatorEntity) {
        // 1、调度schedule

        // 2、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.QUEUING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * console：排队取消中
     * @param operatorEntity
     * @return
     */
    @PostMapping("/queueCanceling")
    public Integer queueCanceling(@RequestBody OperatorEntity operatorEntity) {
        // 1、调度schedule

        // 2、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.QUEUE_CANCELING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * schedule 排队取消完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/queueCancelFinish")
    public Integer queueCancelFinish(@RequestBody OperatorEntity operatorEntity) {
        // 1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.QUEUE_CANCEL);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * console 抢占中
     * @param operatorEntity
     * @return
     */
    @PostMapping("/occupying")
    public Integer occupying(@RequestBody OperatorEntity operatorEntity) {
        // 1、调度schedule

        // 2、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.QUEUING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * 只有训练的runtime 算子实例创建中
     * @param operatorEntity
     * @return
     */
    @PostMapping("/caseCreating")
    public Integer caseCreating(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.CASE_CREATING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * 只有训练的runtime 算子实例创建完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/caseCreateFinish")
    public Integer caseCreateFinish(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.CASE_CREATE_FINISH);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * 只有训练的runtime 数据下载中
     * @param operatorEntity
     * @return
     */
    @PostMapping("/dataUploading")
    public Integer dataUploading(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.DATA_UPLOADING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * 只有训练的runtime 数据下载完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/dataUploadFinish")
    public Integer dataUploadFinish(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.DATA_UPLOAD_FINISH);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * runtime 算子运行开始
     * @param operatorEntity
     * @return
     */
    @PostMapping("/running")
    public Integer running(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.RUNNING);
        Integer status = operatorService.updateOperatorEntity(operatorEntity);
        if (status != 1) {
            return status;
        }


        return 1;
    }

    /**
     * runtime 算子测试
     * @param operatorEntity
     * @return
     */
    @PostMapping("/testing")
    public Integer testing(@RequestBody OperatorEntity operatorEntity) {
        //1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.TESTING);
        return operatorService.updateOperatorEntity(operatorEntity);
    }

    /**
     * runtime 算子运行自动完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/runningAutoFinish")
    public Integer runningAutoFinish(@RequestBody OperatorEntity operatorEntity){
        // 1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.RUNNING_AUTO_FINISH);
        Integer status = operatorService.updateOperatorEntity(operatorEntity);
        if (status != 1) {
            return status;
        }

        // 2、调度schedule（释放资源、保存数据集）

        // 3、部署
        DeployType deployType = operatorEntity.getDeployType();
        switch (deployType) {
            case AUTO_DEPLOY:
                //自动部署
                break;
            case GREY_DEPLOY:
                //灰度部署
                break;
            case TOUCH_DEPLOY:
                //触发部署
                break;
            case NOT_DEPLOY:
                //不部署
                break;
        }

        return 1;
    }

    /**
     * console 算子手动完成
     * @param operatorEntity
     * @return
     */
    @PostMapping("/runningFinish")
    public Integer runningFinish(@RequestBody OperatorEntity operatorEntity){
        // 1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.RUNNING_AUTO_FINISH);
        Integer status = operatorService.updateOperatorEntity(operatorEntity);
        if (status != 1) {
            return status;
        }

        // 2、调度schedule（释放资源、保存数据集）

        return 1;
    }

    /**
     * runtime 算子训练异常
     * @param operatorEntity
     * @return
     */
    @PostMapping("/runningException")
    public Integer runningException(@RequestBody OperatorEntity operatorEntity){
        // 1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.RUNNING_AUTO_FINISH);
        Integer status = operatorService.updateOperatorEntity(operatorEntity);
        if (status != 1) {
            return status;
        }

        // 2、调度schedule（释放资源、保存数据集）

        return 1;
    }

    /**
     * runtime 算子训练服务异常
     * @param operatorEntity
     * @return
     */
    @PostMapping("/serviceException")
    public Integer serviceException(@RequestBody OperatorEntity operatorEntity) {
        // 1、修改状态
        operatorEntity.setOperatorStatus(OperatorStatus.RUNNING_AUTO_FINISH);
        Integer status = operatorService.updateOperatorEntity(operatorEntity);
        if (status != 1) {
            return status;
        }

        // 2、调度schedule（释放资源、保存数据集）

        return 1;
    }

}
