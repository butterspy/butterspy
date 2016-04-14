package org.butterspy.method.matcher;

import java.lang.reflect.Method;

import org.butterspy.internal.invocation.SpyInvocationKey;

public class MethodInvocationMatcher implements InvocationMatcher {

	private CardinalityMatcher cardinalityMatcher = new AnyCardinalityMatcher();
	private StringMatcher methodNameMatcher = new AnyMethodNameMatcher();
	private ArgumentMatcher argumentMatcher = new AnyArgumentMatcher();

	public MethodInvocationMatcher() {
	}

	public MethodInvocationMatcher(String methodName) {
		this.methodNameMatcher = new AntMethodNameMatcher(methodName);
	}

	public MethodInvocationMatcher antNameMatcher(String antPattern) {
		this.methodNameMatcher = new AntMethodNameMatcher(antPattern);
		return this;
	}

	public MethodInvocationMatcher argumentMatcher(Object argument) {
		this.argumentMatcher = new AntArgumentMatcher(argument);
		return this;
	}

	public MethodInvocationMatcher max(int value) {
		this.cardinalityMatcher = new MaxCardinalityMatcher(value);
		return this;
	}

	@Override
	public boolean matches(int currentInvocationCount,
			SpyInvocationKey invocation) {
		Method method = invocation.getMethod();
		Object firstArgument = (invocation.getArguments() != null && invocation
				.getArguments().length > 0) ? invocation.getArguments()[0]
				: null;
		boolean cardinalityMatches = cardinalityMatcher
				.matches(currentInvocationCount);
		boolean methodNameMatches = methodNameMatcher.matches(method.getName());
		boolean argumentMatches = argumentMatcher.matches(firstArgument);

		StringBuilder builder = new StringBuilder();
		builder.append("MethodInvocationMatcher matches [cardinalityMatches=");
		builder.append(cardinalityMatches);
		builder.append(", methodNameMatches=");
		builder.append(methodNameMatches);
		builder.append(", argumentMatches=");
		builder.append(argumentMatches);
		builder.append("]");
		System.out.println(builder.toString());

		return cardinalityMatches && methodNameMatches && argumentMatches;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodInvocationMatcher [cardinalityMatcher=");
		builder.append(cardinalityMatcher);
		builder.append(", methodNameMatcher=");
		builder.append(methodNameMatcher);
		builder.append(", argumentMatcher=");
		builder.append(argumentMatcher);
		builder.append("]");
		return builder.toString();
	}

}
