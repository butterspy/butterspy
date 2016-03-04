package test


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

import test.webservicex.globalweather.GlobalWeatherSoap

@Integration
@Rollback
class WeatherIntegrationSpec extends Specification {
	
	def weatherService
	
    def setup() {
    }

    def cleanup() {
    }

    void "should get weather in a city"() {
		
		when:
		Weather weather = weatherService.getWeather("Amsterdam Airport Schiphol", "Netherlands")
        
		then:
		weather.location == "Amsterdam Airport Schiphol, Netherlands (EHAM) 52-18N 004-46E -2M"
		weather.temperature
    }
		
	void "should get cities per country"() {
		
		when:
		List<City> cities = weatherService.getCitiesByCountry("Netherlands")
		
		then:
		cities.size() == 20
		cities.find { it.name == "Hato Airport, Curacao" }
	}
}
