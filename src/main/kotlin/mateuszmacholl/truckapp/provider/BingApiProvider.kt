package mateuszmacholl.truckapp.provider

import mateuszmacholl.truckapp.config.properties.BingApiProperties
import mateuszmacholl.truckapp.exception.bingApi.BingApiUrlConnectionException
import org.springframework.stereotype.Component
import org.springframework.web.client.getForEntity

@Component
class BingApiProvider(private val bingApiProperties: BingApiProperties): ApiProvider() {
    init {
        apiUrl = "http://dev.virtualearth.net/REST/V1/"
    }

    fun sendRequestForDistance(from: String, to: String): String{
        val url = apiUrl + "Routes/Driving?wp.0=$from&wp.1=$to&key=${bingApiProperties.key}"
        try {
            return restTemplate.getForEntity<String>(url, String::class).body!!
        } catch (ex: Exception) {
            throw BingApiUrlConnectionException("Wrong address to calculate distance", ex)
        }
    }
}