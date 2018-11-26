package mateuszmacholl.truckapp.connector

import mateuszmacholl.truckapp.config.properties.BingApiProperties
import mateuszmacholl.truckapp.deserializer.BingApiDistanceDeserializer
import mateuszmacholl.truckapp.exception.bingApi.BingApiUrlConnectionException
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity




@Component
class BingApiConnector(
        private val bingApiProperties: BingApiProperties,
        private val restTemplate: RestTemplate,
        private val bingApiDistanceDeserializer: BingApiDistanceDeserializer) {

    val apiUrl = "http://dev.virtualearth.net/REST/V1/"

    fun getDistance(from: String, to: String): Double {
        val url = apiUrl + "Routes/Driving?wp.0=$from&wp.1=$to&key=${bingApiProperties.key}"
        try {
            val response = restTemplate.getForEntity<String>(url, String::class).body
            return bingApiDistanceDeserializer.deserialize(response!!)
        } catch (ex: Exception) {
            throw BingApiUrlConnectionException("Wrong address to calculate distance", ex)
        }
    }
}