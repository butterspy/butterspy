package org.butterspy.method;

import org.butterspy.method.matcher.AntPathInvocationMatcher;
import org.butterspy.method.matcher.AnyInvocationMatcher;
import org.butterspy.method.matcher.InvocationMatcher;

public final class AllowInvocationsConfiguration {
	
	private InvocationMatcher invocationMatcher = AnyInvocationMatcher.INSTANCE;
	
	public AllowInvocationsConfiguration invocationMatcher(InvocationMatcher invocationMatcher) {
		this.invocationMatcher = invocationMatcher;
		return this;
	}
		
	public AllowInvocationsConfiguration antMatcher(String antPattern) {
		this.invocationMatcher = new AntPathInvocationMatcher(antPattern);
		return this;
	}

	public InvocationMatcher getInvocationMatcher() {
		return invocationMatcher;
	}
	
}
