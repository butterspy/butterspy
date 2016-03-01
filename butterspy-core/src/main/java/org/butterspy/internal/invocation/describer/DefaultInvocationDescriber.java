package org.butterspy.internal.invocation.describer;

import java.util.Arrays;

import org.butterspy.invocation.DescribedInvocation;
import org.butterspy.invocation.InvocationDescriber;
import org.butterspy.invocation.SpyInvocation;

/**
 * 
 * Default, String-based invocation describer.
 * 
 * @author Ted Vinke
 *
 */
public class DefaultInvocationDescriber implements InvocationDescriber {

	@Override
	public DescribedInvocation describe(final SpyInvocation spyInvocation) {
		
		final StringBuilder builder = new StringBuilder();
		builder.append("#");
		builder.append(spyInvocation.getSequenceNumber());
		builder.append(" [method=");
		builder.append(spyInvocation.getMethod());
		builder.append(", arguments=");
		builder.append(Arrays.asList(spyInvocation.getArguments()));
		builder.append(", returnedObject=");
		builder.append(spyInvocation.getReturnedObject());
		builder.append("]");
		
		return new DescribedInvocation() {

			@Override
			public String toString() {
				return builder.toString();
			}
			
		};
	}

}
