package org.butterspy.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.butterspy.SpyRecording;
import org.butterspy.SpySettings;
import org.butterspy.internal.invocation.SpyInvocationImpl;
import org.butterspy.invocation.SpyInvocation;
import org.butterspy.invocation.SpyMethod;

class SpyRecordingImpl implements SpyRecording {

	/** Count the amount of methods called globally. */
	private static final AtomicInteger sequenceCounter = new AtomicInteger();

	private final Object spy;
	private final SpySettings settings;
	private final List<SpyInvocation> invocations = new ArrayList<SpyInvocation>();
	private boolean recording = true;

	SpyRecordingImpl(Object spy, SpySettings settings) {
		this.spy = spy;
		this.settings = settings;
	}

	void recordInvocation(Object spy, final Method method,
			Object[] arguments, Object result) {
		if (recording) {

			SpyMethod spyMethod = new SpyMethod() {

				@Override
				public String getName() {
					return method.getName();
				}

				@Override
				public Class<?> getReturnType() {
					return method.getReturnType();
				}

				@Override
				public Class<?>[] getParameterTypes() {
					return method.getParameterTypes();
				}

				@Override
				public Class<?>[] getExceptionTypes() {
					return method.getExceptionTypes();
				}

				@Override
				public Method getJavaMethod() {
					return method;
				}

			};

			SpyInvocation spyInvocation = new SpyInvocationImpl(spy,
					spyMethod, arguments, result,
					sequenceCounter.incrementAndGet());
			invocations.add(spyInvocation);
		}
	}

	@Override
	public String getName() {
		if (settings.getName() != null
				&& !settings.getName().trim().equalsIgnoreCase("")) {
			return settings.getName();
		} else {
			return spy.toString();
		}
	}

	@Override
	public void start() {
		this.recording = true;
	}

	@Override
	public void restart() {
		stop();
		this.invocations.clear();
		start();
	}

	@Override
	public void stop() {
		this.recording = false;
	}

	@Override
	public boolean isRecording() {
		return recording;
	}

	@Override
	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	@Override
	public List<SpyInvocation> getInvocations() {
		return Collections.unmodifiableList(invocations);
	}

}