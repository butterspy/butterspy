package org.butterspy.internal.invocation.describer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.butterspy.invocation.DescribedInvocation;
import org.butterspy.invocation.InvocationDescriber;
import org.butterspy.invocation.SpyInvocation;

/**
 * Describes an invocation in such a way that method calls such as
 * 
 * <pre>
 * spy.name = &quot;Mr Ropy&quot;
 * </pre>
 * 
 * which is recorded as <code>setProperty</code> with arguments
 * <code>[name, Mr. Ropey]</code> is displayed in the proper Groovy <i>property
 * mutator</i> way.<code>
 * 
 * @author Ted Vinke
 *
 */
public class GroovyInvocationDescriber implements InvocationDescriber {

	@Override
	public DescribedInvocation describe(SpyInvocation spyInvocation) {
		Method m = spyInvocation.getMethod();
		
		final String methodName;
		final List<Object> argumentList;
		final List<Object> args = Arrays.asList(spyInvocation.getArguments());
		if (m.getName().equals("setProperty") && spyInvocation.getArguments().length > 1) {
			methodName = args.get(0).toString();
			argumentList = args.subList(1, args.size());
		} else {
			methodName = m.getName();
			argumentList = args;
		}
		
		final StringBuilder builder = new StringBuilder();
		builder.append("#");
		builder.append(spyInvocation.getSequenceNumber());
		builder.append(" [method=");
		builder.append(methodName);
		builder.append(", arguments=");
		builder.append(argumentList);
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
