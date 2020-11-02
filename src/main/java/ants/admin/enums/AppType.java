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
public enum AppType {

    APP1(1, "应用1"),
    APP2(2, "应用2"),
    APP3(3, "应用3"),
   ;


    private int type;


    private String desc;

    AppType(int status, String desc) {
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
