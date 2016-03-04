package test

import grails.transaction.Transactional
import test.security.User

@Transactional
class CustomerService {
	
    Customer findCustomerForUser(User user) {
		assert user
		Customer.find { user == user }
    }
}
