package test


import grails.buildtestdata.mixin.Build;
import grails.test.mixin.TestFor
import spock.lang.Specification

import test.security.User

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CustomerService)
@Build([User, Customer])
class CustomerServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "should find customer by user"() {
        
		given:
		def user = User.build(username: "andy101")
		def customer = Customer.build(user: user, name: "Andy")
		
		when:
		def foundCustomer = service.findCustomerForUser(user)
		
		then:
		foundCustomer == customer
    }
}
