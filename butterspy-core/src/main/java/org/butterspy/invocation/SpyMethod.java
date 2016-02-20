package org.butterspy.invocation;

import java.lang.reflect.Method;

public interface SpyMethod {

	public String getName();

    public Class<?> getReturnType();

    public Class<?>[] getParameterTypes();

    public Class<?>[] getExceptionTypes();

    public Method getJavaMethod();
}
