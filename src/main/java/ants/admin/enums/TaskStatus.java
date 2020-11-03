/**
 * Copyright (c) 2014-2017, test.com
 * All rights reserved.
 */
package ants.admin.enums;


/**
 * 任务状态
 * WD状态
 *    INIT(0, "未提交"),
 *     INIT(0, "提交成功"),
 *     INIT(0, "传送提交成功"),
 *     INIT(0, "发送成功"),
 *     INIT(0, "投递成功"),
 * ants status
 * //-- 1:start, 2:interrupt, 3:finish, -1:stop
 * @author Richard Liu(liuyongcheng@test.com)
 * @version $Id$
 * @since 2020.10.1
 * 1
 */
public enum TaskStatus {

    INIT(0, "初始化"),
    SUBMITTED(1, "提交成功"),       //客户端SDK已经数据全部发送到了服务端,尚未实现
    START(2, "发送中"),            //服务端正在将数据向对端服务端发送中
    INTERRUPT(3, "已停止"),        //人工停止
    CANCELED(4, "已取消"),         //人工取消
    FAILED(5, "发生失败"),            //异常停止
    FINISH(6, "发送成功"),          //服务端将数据全部发送至对端的服务端
    DELIVERED(7, "投递成功"),        //尚未实现, 对端服务端将数据或消息推送至对端的客户端
    DELETED(8, "已删除"),                      //已删除

   ;

    private int status;

    private String desc;

    TaskStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 取枚举
     * @param status
     * @return {@link TaskStatus}
     */
    public static TaskStatus getStatus(int status) {
        for (TaskStatus s:TaskStatus.values()) {
            if (s.getStatus() == status) {
                return s;
            }
        }

        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
