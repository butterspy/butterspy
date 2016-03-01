package org.butterspy.invocation;

public interface InvocationDescriber {

	/**
     * Describes the invocation in the human friendly way.
     *
     * @return the description of this invocation.
     */
	DescribedInvocation describe(SpyInvocation spyInvocation);
}
