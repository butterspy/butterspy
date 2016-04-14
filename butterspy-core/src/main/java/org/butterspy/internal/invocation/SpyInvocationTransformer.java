package org.butterspy.internal.invocation;

public interface SpyInvocationTransformer {

	SpyInvocationKey transform(SpyInvocationKey invocation);
}
