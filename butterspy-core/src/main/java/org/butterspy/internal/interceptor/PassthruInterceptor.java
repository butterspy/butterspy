package org.butterspy.internal.interceptor;

import java.lang.reflect.Method;
import org.butterspy.MethodProxy;
import org.butterspy.MethodInterceptor;

public class PassthruInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(MethodProxy methodProxy, Object obj, Method method,
			Object[] args) throws Throwable {
		return methodProxy.invoke(obj, args);
	}

}
