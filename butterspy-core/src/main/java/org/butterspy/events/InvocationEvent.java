package org.butterspy.events;

import org.butterspy.invocation.DescribedInvocation;
import org.butterspy.invocation.SpyInvocation;

/**
 * Represent a method call on a mock.

 * @author Ted Vinke
 *
 */
public interface InvocationEvent {
	
	DescribedInvocation getDescription();

	SpyInvocation getInvocation();
}
