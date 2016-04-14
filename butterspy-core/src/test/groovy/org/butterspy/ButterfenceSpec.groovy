package org.butterspy

import static org.butterspy.Butterspy.*

import org.butterspy.method.AllowInvocationsConfiguration
import org.butterspy.method.MethodNotAllowedException
import org.butterspy.method.matcher.MethodInvocationMatcher
import org.butterspy.internal.Butterspied

import spock.lang.Ignore
import spock.lang.IgnoreRest;
import spock.lang.Specification
import groovy.transform.ToString

/**
 * Tests {@link Butterspy} for fencing spies.
 * 
 * @author Ted Vinke
 *
 */
class ButterfenceSpec extends Specification {

	static AllowInvocationsConfiguration createConfig() {
		new AllowInvocationsConfiguration()
	}
	
	def "method by name should be prevented at first occurence"() {

		given:
		AllowInvocationsConfiguration config = createConfig()
				.invocationMatcher(new MethodInvocationMatcher('setProperty').max(0))
		Cat cat = fence(new Cat(), config)

		when:
		cat.name = "Giggles"

		then:
		thrown(MethodNotAllowedException)
	}
	
	def "method by name should be prevented at second occurence"() {
		
		given:
		AllowInvocationsConfiguration config = createConfig()
				.invocationMatcher(new MethodInvocationMatcher('setProperty').max(1))
		Cat cat = fence(new Cat(), config)

		when: "basically twice setPropery is called, because we're in Groovy world"
		cat.name = "Giggles"
		cat.age = 2

		then:
		thrown(MethodNotAllowedException)
	}
	
	@IgnoreRest
	def "method by name and argument should be prevented at first occurence"() {
		
		given:
		AllowInvocationsConfiguration config = createConfig()
				.invocationMatcher(new MethodInvocationMatcher('setProperty').argumentMatcher("Giggles").max(1))
		Cat cat = fence(new Cat(), config)

		when: "basically twice setPropery is called, because we're in Groovy world"
		cat.name = "Giggles"
		println "---------------------------"
		cat.age = 2

		then:
		true
	}

	@Ignore
	def "create and record some methods"() {

		given:
		Cat cat = fence(new Cat())

		when:
		cat.name = "Giggles"
		cat.name = "Wobbles"
		cat.name = "Giggles"

		then:
		SpyRecording recording = getSpyRecording(cat)
		recording.stop()
	}
}