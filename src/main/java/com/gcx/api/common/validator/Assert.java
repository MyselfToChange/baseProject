package com.gcx.api.common.validator;

import com.gcx.api.common.exception.ParameterException;
import com.gcx.api.common.util.StringUtils;


/**
 *<p>Title:Assert</p>
 *<p>Description:数据校验</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年3月21日</p>
 */
public abstract class Assert {

    public static void isBlank(String str, String message){
        if (StringUtils.isEmpty(str)) {
            throw new ParameterException(message);
        }
    }

    public static void isNull(Object object, String message){
        if (object == null) {
            throw new ParameterException(message);
        }
    }
}
