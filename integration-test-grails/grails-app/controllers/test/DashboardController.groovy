package test

import grails.converters.*
import grails.plugin.springsecurity.annotation.Secured;

@Secured('permitAll')
//@Secured('ROLE_USER')
class DashboardController {
	
	def springSecurityService
	def customerService
	def weatherService

    def index() { 
		
		println "Principal2: " + springSecurityService.principal
		println "User2: " + springSecurityService.currentUser
		
		def user = springSecurityService.currentUser
		println "Logged in as: " + user
		assert user
		Customer customer = customerService.findCustomerForUser(user)
		assert customer
		
		def result = [customer: customer.name]
		
		if (customer.workAddress) {
			Weather weather = weatherService.getWeather(customer.workAddress)
			result.weather = weather
		}
		
		render result as JSON
	}
}
