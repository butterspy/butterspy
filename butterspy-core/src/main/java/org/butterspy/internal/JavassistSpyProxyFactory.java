package org.butterspy.internal;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import org.butterspy.MethodInterceptor;
import org.butterspy.MethodProxy;
import org.butterspy.SpyProxyFactory;
import org.butterspy.SpyRecording;
import org.butterspy.SpySettings;
import org.butterspy.internal.interceptor.PassthruInterceptor;

public class JavassistSpyProxyFactory implements SpyProxyFactory {

	@SuppressWarnings("unchecked")
	public <T> T create(final T instanceToSpy, final SpySettings settings)
			throws Exception {
		return create((Class<T>) instanceToSpy.getClass(), instanceToSpy,
				settings);
	}

	@Override
	public <T> T create(final Class<T> classForSpy, final T instanceToSpy,
			final SpySettings settings) throws Exception {

		ProxyFactory factory = new ProxyFactory();
		if (classForSpy.isInterface()) {
			factory.setInterfaces(new Class<?>[] { classForSpy,
					Butterspied.class });
		} else {
			factory.setSuperclass(classForSpy);
			factory.setInterfaces(new Class<?>[] { Butterspied.class });
		}

		MethodHandler handler = new MethodHandler() {
			private SpyRecordingImpl recording = new SpyRecordingImpl(
					instanceToSpy, settings);

			private final Method getSpyRecording = Butterspied.class.getMethod(
					"getSpyRecording", new Class<?>[0]);

			private final MethodInterceptor interceptor = settings
					.getMethodInterceptor();

			@Override
			public Object invoke(Object self, Method method, Method proceed,
					Object[] args) throws Throwable {
				if (method.equals(getSpyRecording)) {
					return recording;
				} else {

					method.setAccessible(true);
					MethodProxy methodProxy = createMethodProxy(method);

					Object result = interceptor.intercept(methodProxy, instanceToSpy, method, args);
					recording.onInvoked(self, method, args, result);
					return result;
				}
			}
		};

		return classForSpy.cast(factory.create(new Class<?>[0], new Object[0],
				handler));
	}

	private MethodProxy createMethodProxy(final Method method) {
		return new MethodProxy() {

			@Override
			public Object invoke(Object instanceToSpy, Object[] args)
					throws Throwable {
				return method.invoke(instanceToSpy, args);
			}
		};
	}

	@Override
	public SpyRecording getSpyRecording(Object spy) {
		return ((Butterspied) spy).getSpyRecording();
	}

}
