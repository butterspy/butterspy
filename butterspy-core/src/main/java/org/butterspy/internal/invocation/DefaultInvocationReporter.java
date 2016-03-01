package org.butterspy.internal.invocation;

import java.util.List;

import org.butterspy.SpySettings;
import org.butterspy.events.InvocationEvent;
import org.butterspy.events.InvocationListener;
import org.butterspy.invocation.DescribedInvocation;
import org.butterspy.invocation.InvocationDescriber;
import org.butterspy.invocation.SpyInvocation;
import org.butterspy.invocation.SpyInvocationReporter;

/**
 * Reports method invocations to registered listeners.
 * 
 * @author Ted Vinke
 *
 */
public class DefaultInvocationReporter implements SpyInvocationReporter {

	private final List<InvocationListener> listeners;
	private final InvocationDescriber describer;

	public DefaultInvocationReporter(SpySettings settings,
			InvocationDescriber invocationDescriber) {
		this.listeners = settings.getInvocationListeners();
		this.describer = invocationDescriber;
	}

	@Override
	public void report(final SpyInvocation spyInvocation) {

		for (InvocationListener listener : listeners) {
			listener.reportInvocation(new InvocationEvent() {

				@Override
				public SpyInvocation getInvocation() {
					return spyInvocation;
				}

				@Override
				public DescribedInvocation getDescription() {
					return describer.describe(spyInvocation);
				}
			});
		}

	}

}
