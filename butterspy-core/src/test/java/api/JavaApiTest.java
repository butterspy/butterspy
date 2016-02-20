package api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.butterspy.Butterspy;
import org.butterspy.SpyRecording;
import org.butterspy.invocation.SpyInvocation;
import org.junit.Test;

/**
 * Test the {@link Butterspy} API from Java.
 * 
 * <p>
 * This is a unit test verifying the proper API is public and usable.
 * <p>
 * That's why this test class is in some "api" package and not in
 * "org.butterfly".
 * 
 * @author Ted Vinke
 *
 */
public class JavaApiTest {

	@Test
	public void createAndRecordSomeMethods() throws Exception {

		// given:
		Cat cat = Butterspy.spy(Cat.class, new Cat("Pebbles"));
				
		// expect:
		SpyRecording recording = Butterspy.getSpyRecording(cat);
		assertThat("we are recording", recording.isRecording(), is(true));

		// when: we invoke some methods
		cat.getName();
		cat.setName("Giggles");
		cat.setAge(2);
		String greeting = cat.greeting();
		assertThat(greeting, is("Hello, Giggles!"));

		// and: we stop recording
		recording.stop();
		assertThat("we stopped recording", recording.isRecording(), is(false));
		cat.setAge(3); // will not be recorded

		// then: verify the invocations
		List<SpyInvocation> methods = recording.getInvocations();
		for (SpyInvocation method : methods) {
			System.out.println(method);
		}

		assertThat(methods.size(), is(4));
		// ugly String comparison here should be removed as soon as possible ;-)
		assertThat(
				methods.get(0).toString(),
				is("#1 [method=public java.lang.String api.Cat.getName(), arguments=[], returnedObject=Pebbles]"));
		assertThat(
				methods.get(1).toString(),
				is("#2 [method=public void api.Cat.setName(java.lang.String), arguments=[Giggles], returnedObject=null]"));
		assertThat(
				methods.get(2).toString(),
				is("#3 [method=public void api.Cat.setAge(int), arguments=[2], returnedObject=null]"));
		assertThat(
				methods.get(3).toString(),
				is("#4 [method=public java.lang.String api.Cat.greeting(), arguments=[], returnedObject=Hello, Giggles!]"));

	}
}