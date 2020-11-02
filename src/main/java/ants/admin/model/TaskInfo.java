package ants.admin.model;

import com.alibaba.dubbo.common.json.JSON;

import java.io.IOException;
import java.io.Serializable;

/**
 * Task info for data transfer.
 * @author Richard Liu(liuyngchng@hotmail.com)
 */
public class TaskInfo implements Serializable {

    private String id;                  //主键
    private String taskName;            //任务名称
    private String appId;               //应用类型
    private String dataSize;            //数据量
    private String originIp;            //发送者IP
    private String targetIp;            //接收者IP
    private String usedTime;            //任务用时;
    private String completePer;         //完成率
    private String createTime;          //开始时间
    private String finishTime;          //完成时间
    private String pushTime;            //推送时间
    private String updateTime;          //更新时间
    private String interruptTime;       //中断次数
    private Integer retryCount;         //重试次数
    private Integer retryTime;          //重试总时间
    private String retryIp;             //重试机器IP
    private Integer sliceSent;          //已发送的包数
    private Integer sliceTotal;         //总包数
    private String restartTime;         //重启时间
    private String cancelTime;          //取消时间
    private Integer taskType;           //任务类型
    private Integer status;             //任务状态
    private Integer del;                //是否已删除， 0未删除， 1已删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public String getCompletePer() {
        return completePer;
    }

    public void setCompletePer(String completePer) {
        this.completePer = completePer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getInterruptTime() {
        return interruptTime;
    }

    public void setInterruptTime(String interruptTime) {
        this.interruptTime = interruptTime;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public String getRetryIp() {
        return retryIp;
    }

    public void setRetryIp(String retryIp) {
        this.retryIp = retryIp;
    }

    public Integer getSliceSent() {
        return sliceSent;
    }

    public void setSliceSent(Integer sliceSent) {
        this.sliceSent = sliceSent;
    }

    public Integer getSliceTotal() {
        return sliceTotal;
    }

    public void setSliceTotal(Integer sliceTotal) {
        this.sliceTotal = sliceTotal;
    }

    public String getRestartTime() {
        return restartTime;
    }

    public void setRestartTime(String restartTime) {
        this.restartTime = restartTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public String toString() {

        try {
            return JSON.json(this);
        } catch (IOException e) {
            return "";
        }
    }
}
