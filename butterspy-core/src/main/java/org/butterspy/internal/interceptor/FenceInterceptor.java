package org.butterspy.internal.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.butterspy.MethodProxy;
import org.butterspy.internal.invocation.SpyInvocationKey;
import org.butterspy.method.AllowInvocationsConfiguration;
import org.butterspy.method.MethodNotAllowedException;
import org.butterspy.method.matcher.InvocationMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FenceInterceptor extends AbstractCollectingMethodInterceptor {

	private static final Logger log = LoggerFactory
			.getLogger(FenceInterceptor.class);

	private final AllowInvocationsConfiguration config;

	private Set<SpyInvocationKey> invocations = new HashSet<SpyInvocationKey>();
	private Map<Method, Set<SpyInvocationKey>> invocationsByMethod = new HashMap<Method, Set<SpyInvocationKey>>();

	public FenceInterceptor(AllowInvocationsConfiguration config) {
		this.config = config;
	}

	@Override
	Object intercept(MethodProxy methodProxy, SpyInvocationKey spyInvocation)
			throws Throwable {

		InvocationMatcher invocationMatcher = config.getInvocationMatcher();

		int currentInvocationCount = countInvocations(spyInvocation);
		System.out.println("currentInvocationCount: " + currentInvocationCount
				+ " for " + spyInvocation);
		if (invocationMatcher.matches(currentInvocationCount, spyInvocation)) {
			System.out.println(invocationMatcher + " matches");
			register(spyInvocation);
			return methodProxy.invoke(spyInvocation.getSpy(),
					spyInvocation.getArguments());
		}

		System.out.println(invocationMatcher + " doesn't match");

		throw new MethodNotAllowedException("Preventing invocation "
				+ spyInvocation.getMethod() + " with arguments '"
				+ Arrays.asList(spyInvocation.getArguments()) + "' on "
				+ spyInvocation.getSpy() + ". Prevented by "
				+ invocationMatcher);

	}

	void register(SpyInvocationKey spyInvocation) {
		this.invocations.add(spyInvocation);

		Method method = spyInvocation.getMethod();
		if (!invocationsByMethod.containsKey(method)) {
			invocationsByMethod.put(method, new HashSet<SpyInvocationKey>());
		}
		invocationsByMethod.get(method).add(spyInvocation);

		System.out.println("Registered " + spyInvocation);
	}

	int countInvocations(SpyInvocationKey spyInvocation) {
		Method method = spyInvocation.getMethod();
		if (invocationsByMethod.containsKey(method)) {
			return invocationsByMethod.get(method).size();
		}

		return 0;
	}
}
