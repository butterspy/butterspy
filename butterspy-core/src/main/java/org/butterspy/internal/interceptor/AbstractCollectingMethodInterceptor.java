package org.butterspy.internal.interceptor;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.butterspy.MethodInterceptor;
import org.butterspy.MethodProxy;
import org.butterspy.internal.invocation.SpyInvocationKey;

abstract class AbstractCollectingMethodInterceptor implements MethodInterceptor {

	protected Set<SpyInvocationKey> invocations = new HashSet<SpyInvocationKey>();

	@Override
	public Object intercept(MethodProxy methodProxy, Object spy, Method method,
			Object[] args) throws Throwable {
		System.out.println("Reporting " + method);

		SpyInvocationKey spyInvocationKey = new SpyInvocationKey(spy, method,
				args);
		Object returnedObject = intercept(methodProxy, spyInvocationKey);
		invocations.add(spyInvocationKey);
		return returnedObject;
	}

	abstract Object intercept(MethodProxy methodProxy,
			SpyInvocationKey spyInvocation) throws Throwable;

}
