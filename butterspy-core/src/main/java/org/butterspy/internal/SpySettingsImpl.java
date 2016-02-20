package org.butterspy.internal;

import java.util.ArrayList;
import java.util.List;

import org.butterspy.SpySettings;
import org.butterspy.internal.debugging.VerboseSpyInvocationLogger;
import org.butterspy.listeners.InvocationListener;

public class SpySettingsImpl<T> implements SpySettings {

	private static final long serialVersionUID = -2861156147776921893L;

	private String name;
	private List<InvocationListener> invocationListeners = new ArrayList<InvocationListener>();

	@Override
	public SpySettings name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public SpySettings verboseLogging() {
		if (!invocationListenersContainsType(VerboseSpyInvocationLogger.class)) {
			invocationListeners(new VerboseSpyInvocationLogger());
		}
		return this;
	}

	public SpySettings invocationListeners(
			InvocationListener... listeners) {
		if (listeners == null || listeners.length == 0) {
			throw new IllegalArgumentException(
					"Invocation listeners need at least one listener.");
		}
		for (InvocationListener listener : listeners) {
			if (listener == null) {
				throw new IllegalArgumentException(
						"Invocation listener can not be null.");
			}
			this.invocationListeners.add(listener);
		}
		return this;
	}

	private boolean invocationListenersContainsType(Class<?> clazz) {
		for (InvocationListener listener : invocationListeners) {
			if (listener.getClass().equals(clazz)) {
				return true;
			}
		}
		return false;
	}

	public List<InvocationListener> getInvocationListeners() {
		return this.invocationListeners;
	}

	public boolean hasInvocationListeners() {
		return !invocationListeners.isEmpty();
	}

}
