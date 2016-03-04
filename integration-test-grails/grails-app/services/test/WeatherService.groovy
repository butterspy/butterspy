package test

import grails.transaction.Transactional
import groovy.transform.ToString;
import test.webservicex.globalweather.GlobalWeatherSoap

@Transactional
class WeatherService {
	
	GlobalWeatherSoap weatherClient
	
	Weather getWeather(Address address) {
		return getWeather(address.city, address.country)
	}

    Weather getWeather(String cityName, String countryName) {
		String xml = weatherClient.getWeather(cityName, countryName)
		
		// <CurrentWeather>
		// 	<Location>Amsterdam Airport Schiphol, Netherlands (EHAM) 52-18N 004-46E -2M</Location>
		// 	<Time>Mar 01, 2016 - 07:55 AM EST / 2016.03.01 1255 UTC</Time>
		// 	<Wind> from the S (180 degrees) at 18 MPH (16 KT) (direction variable):0</Wind>
		// 	<Visibility> 1 mile(s):0</Visibility>
		// 	<SkyConditions> overcast</SkyConditions>
		// 	<Temperature> 37 F (3 C)</Temperature>
		// 	<DewPoint> 35 F (2 C)</DewPoint>
		// 	<RelativeHumidity> 93%</RelativeHumidity>
		// 	<Pressure> 29.85 in. Hg (1011 hPa)</Pressure>
		// 	<Status>Success</Status>
		// </CurrentWeather>
		
		def currentWeather = new XmlSlurper().parseText(xml)
		
		return new Weather(
			location: currentWeather['Location'],
			temperature: currentWeather['Temperature']
		)
    }
	
	List<City> getCitiesByCountry(String countryName) {
		String xml = weatherClient.getCitiesByCountry(countryName)
		
		// <NewDataSet>
		//   <Table>
		//     <Country>Netherlands</Country>
		//     <City>Amsterdam Airport Schiphol</City>
		//   </Table>
		//   <Table>
		//     <Country>Netherlands</Country>
		//     <City>Maastricht Airport Zuid Limburg</City>
		//   </Table>
		
		//   ...
		
		//   <Table>
		//     <Country>Netherlands Antilles</Country>
		//     <City>Juliana Airport, Saint Maarten</City>
		//   </Table>
		// </NewDataSet>
		
		def newDataSet = new XmlSlurper().parseText(xml)
		newDataSet.Table.collect {
			new City(name: it.City, countryName: it.Country)
		}
	}
}
@ToString
class Weather {
	String location
	String temperature
}
@ToString
class City {
	String name
	String countryName
}