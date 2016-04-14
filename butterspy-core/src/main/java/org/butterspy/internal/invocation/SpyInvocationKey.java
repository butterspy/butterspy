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

	public Object getSpy() {
		return spy;
	}

	public Method getMethod() {
		return method;
	}
	
	public String getMethodName() {
		return method.getName();
	}

	public Object[] getArguments() {
		return arguments;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpyInvocationKey [spy=");
		builder.append(spy);
		builder.append(", method=");
		builder.append(method);
		builder.append(", arguments=");
		builder.append(Arrays.toString(arguments));
		builder.append("]");
		return builder.toString();
	}
	
	
	
}