package org.butterspy.internal.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.butterspy.MethodInterceptor;
import org.butterspy.MethodProxy;
import org.butterspy.internal.invocation.SpyInvocationImpl;
import org.butterspy.internal.invocation.SpyInvocationKey;
import org.butterspy.invocation.SpyInvocation;

public class FenceInterceptor implements MethodInterceptor {

	private Set<SpyInvocationKey> invocations = new HashSet<SpyInvocationKey>();

	@Override
	public Object intercept(MethodProxy methodProxy, Object spy, Method method,
			Object[] args) throws Throwable {
		System.out.println("Reporting " + method);

		SpyInvocationKey spyInvocationKey = new SpyInvocationKey(spy, method, args);
		if (invocations.contains(spyInvocationKey)) {
			throw new RuntimeException("Preventing invocation " + method
					+ " with arguments '" + Arrays.asList(args) + "' on " + spy);
		}

		invocations.add(spyInvocationKey);
		return methodProxy.invoke(spy, args);
	}

}
