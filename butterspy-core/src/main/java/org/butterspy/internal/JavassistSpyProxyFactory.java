package org.butterspy.internal;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import org.butterspy.SpyProxyFactory;
import org.butterspy.SpyRecording;
import org.butterspy.SpySettings;

public class JavassistSpyProxyFactory implements SpyProxyFactory {

	@Override
	public <T> T create(Class<T> classForSpy, final T instanceToSpy,
			final SpySettings settings) throws Exception {
		
		ProxyFactory factory = new ProxyFactory();
		if (classForSpy.isInterface()) {
			factory.setInterfaces(new Class<?>[] { classForSpy, Butterspied.class });
		} else {
			factory.setSuperclass(classForSpy);
			factory.setInterfaces(new Class<?>[] { Butterspied.class });
		}

		MethodHandler handler = new MethodHandler() {
			private SpyRecordingImpl recording = new SpyRecordingImpl(
					instanceToSpy, settings);

			private final Method getSpyRecording = Butterspied.class.getMethod(
					"getSpyRecording", new Class<?>[0]);

			@Override
			public Object invoke(Object self, Method method, Method proceed,
					Object[] args) throws Throwable {
				if (method.equals(getSpyRecording)) {
					return recording;
				} else {
					method.setAccessible(true);
					Object result = method.invoke(instanceToSpy, args);
					recording.recordInvocation(self, method, args, result);
					return result;
				}
			}
		};

		return classForSpy.cast(factory.create(new Class<?>[0], new Object[0],
				handler));
	}

	@Override
	public SpyRecording getSpyRecording(Object spy) {
		return ((Butterspied) spy).getSpyRecording();
	}

}
