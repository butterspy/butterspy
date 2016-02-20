package org.butterspy

import static org.butterspy.Butterspy.*
import org.butterspy.internal.Butterspied
import spock.lang.Specification;

import groovy.transform.ToString;

/**
 * Tests {@link Butterspy}.
 * 
 * @author Ted Vinke
 *
 */
class ButterspySpec extends Specification {

	def "create and record some methods"() {
		
		given:
		Cat cat = spy(Cat.class, new Cat())
		SpyRecording recording = getSpyRecording(cat)
		
		when:
		recording.start()
		cat.getName()
		cat.name = "Giggles"
		cat.age = 2
		String greeting = cat.greeting()
		recording.stop()
		
		then: "Cat is spied upon"
		cat instanceof Butterspied
		println cat
		
		recording
		recording.invocations
		recording.invocations.each { invocation ->
			println invocation
		}
	}
}
@ToString
class Cat {
	
	String name
	int age
	
	String greeting() {
		"Hello, $name!"
	}
}