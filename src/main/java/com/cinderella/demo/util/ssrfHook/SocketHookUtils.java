package com.cinderella.demo.util.ssrfHook;

import java.lang.reflect.Method;

/**
 * @author Cinderella
 * @time 2021/8/23 16:41
 * @Description
 **/
class SocketHookUtils {

    /**
     * Poll the parent class to find the reflection method.
     * SocksSocketImpl -> PlainSocketImpl -> AbstractPlainSocketImpl
     *
     * @author liergou @2020-04-04 01:43
     */
    static Method findMethod(Class<?> clazz, String findName, Class<?>[] args) {

        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(findName, args);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

}
