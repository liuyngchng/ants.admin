/**
 * Copyright (c) 2014-2017, test.com
 * All rights reserved.
 */
package ants.admin.enums;


/**
 * 审核状态
 * @author Richard Liu(liuyongcheng@test.com)
 * @version $Id$
 * @since 2016.12.14
 */
public enum TaskType {

    RCV(0, "接收"),
    SND(1, "接收"),
   ;


    private int type;


    private String desc;

    TaskType(int status, String desc) {
        this.type = status;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
