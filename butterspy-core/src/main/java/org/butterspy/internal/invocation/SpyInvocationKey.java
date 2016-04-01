package org.butterspy.internal.invocation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SpyInvocationKey {
	final Object spy;
	final Method method;
	final Object[] arguments;

	public SpyInvocationKey(Object spy, Method method, Object[] arguments) {
		this.spy = spy;
		this.method = method;
		this.arguments = arguments;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !o.getClass().equals(this.getClass())) {
			return false;
		}

		SpyInvocationKey other = (SpyInvocationKey) o;

		return this.spy.equals(other.spy)
				&& this.method.equals(other.method)
				&& this.equalArguments(other.arguments);
	}

	private boolean equalArguments(Object[] arguments) {
		return Arrays.equals(arguments, this.arguments);
	}

	@Override
	public int hashCode() {
		return 1;
	}

}