package org.butterspy.groovy

import static org.butterspy.Butterspy.*;
import spock.lang.*

/**
 * Tests spying on Groovy classes.
 */
class SpyGroovyClassesSpec extends Specification {
	
	def settings = withSettings().verboseLogging()
	
	static class Customer {
		String name
	}

	void "should be able to spy on Groovy classes"() {
		
		given:
		def spy = spy(new Customer(), settings)
		
        expect:
		def spyRecording = getSpyRecording(spy)
		spyRecording.isRecording()
		
		when: "we invoke a setter"
		spy.name = "Mr. Ropey"
		spyRecording.stop()
		
		then:
		spyRecording.invocations
		
		def invocation = spyRecording.invocations[0] 
		invocation.method.name == "setProperty"
		invocation.arguments[0] == "name"
		invocation.arguments[1] == "Mr. Ropey"
		!invocation.returnedObject
		
		when: "we invoke a getter"
		spyRecording.resume()
		String name = spy.name
		
		then:
		def lastInvocation = spyRecording.invocations.last()
		lastInvocation.method.name == "getName"
		!lastInvocation.arguments
		lastInvocation.returnedObject == name
    }
}