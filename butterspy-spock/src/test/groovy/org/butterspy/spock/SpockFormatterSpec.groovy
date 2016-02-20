package org.butterspy.spock

import static org.butterspy.Butterspy.*

import org.butterspy.spock.SpockFormatter
import org.butterspy.invocation.SpyInvocation

import spock.lang.IgnoreRest
import spock.lang.Specification


/**
 * 
 * Tests {@link SpockFormatter}.
 * 
 * @author Ted Vinke
 *
 */
class SpockFormatterSpec extends Specification {

	Publisher publisher = new Publisher()

	void "should send messages to all subscribers"() {

		given:
		Subscriber subscriber = Mock()
		Subscriber subscriber2 = Mock()

		and:
		publisher.subscribers << subscriber
		publisher.subscribers << subscriber2

		when:
		publisher.send("hello")

		then:
		1 * subscriber.receive("hello")
		1 * subscriber2.receive("hello")
	}

	void "should format simple interaction"() {

		given:
		Subscriber subscriber = spy("subscriber", Subscriber, new SubscriberImpl())
		Subscriber subscriber2 = spy("subscriber2", Subscriber, new SubscriberImpl())

		and:
		publisher.subscribers << subscriber
		publisher.subscribers << subscriber2

		when:
		publisher.send("hello")

		then:
		def recording = subscriber.spyRecording
		def invocations = recording.invocations
		println "${invocations.size()} invocations"
		for (SpyInvocation invocation : invocations) {
			println invocation
		}

		and:
		def f = new SpockFormatter()
		f.format(subscriber.spyRecording) == '1 * subscriber.receive("hello")'
		f.format(subscriber2.spyRecording) == '1 * subscriber2.receive("hello")'
	}

	void "should format combined simple interactions"() {

		given:
		Subscriber subscriber = spy("subscriber", Subscriber, new SubscriberImpl())
		Subscriber subscriber2 = spy("subscriber2", Subscriber, new SubscriberImpl())

		when: "invoke some methods on multiple objects"
		subscriber2.receive("first")
		subscriber.receive("second")
		subscriber2.receive("third")

		then:
		def expected = new StringBuilder()
		expected << '1 * subscriber2.receive("first")\n'
		expected << '1 * subscriber.receive("second")\n'
		expected << '1 * subscriber2.receive("third")'

		def f = new SpockFormatter()
		f.format(subscriber.spyRecording, subscriber2.spyRecording) == expected.toString()
	}
}
class Publisher {
	List<Subscriber> subscribers = []
	void send(String message) {
		subscribers*.receive(message)
	}
}

interface Subscriber {
	void receive(String message)
}
class SubscriberImpl implements Subscriber {

	@Override
	public void receive(String message) {
		println "${this} received $message"
	}
}