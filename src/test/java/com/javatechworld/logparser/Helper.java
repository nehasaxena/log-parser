package com.javatechworld.logparser;

import java.lang.reflect.Method;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: Helper
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class Helper {

    public static Object invokeMethod(Object object, String methodName, Class[] parameters, Object[] arguments) throws Exception {

        Method method = object.getClass().getDeclaredMethod(methodName, parameters);
        method.setAccessible(true);

        return method.invoke(object, arguments);
    }
}
