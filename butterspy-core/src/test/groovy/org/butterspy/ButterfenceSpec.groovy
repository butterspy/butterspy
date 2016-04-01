package org.butterspy

import static org.butterspy.Butterspy.*

import org.butterspy.internal.Butterspied

import spock.lang.Ignore;
import spock.lang.Specification
import groovy.transform.ToString

/**
 * Tests {@link Butterspy} for fencing spies.
 * 
 * @author Ted Vinke
 *
 */
class ButterfenceSpec extends Specification {

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
		
		println cat
		
		recording
		recording.invocations
		recording.invocations.each { invocation ->
			println invocation
		}
	}
}