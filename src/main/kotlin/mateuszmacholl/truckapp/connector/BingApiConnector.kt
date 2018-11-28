package mateuszmacholl.truckapp.connector

import mateuszmacholl.truckapp.deserializer.BingApiDistanceDeserializer
import mateuszmacholl.truckapp.provider.BingApiProvider
import org.springframework.stereotype.Component




@Component
class BingApiConnector(
        private val bingApiProvider: BingApiProvider,
        private val bingApiDistanceDeserializer: BingApiDistanceDeserializer) {

    fun getDistance(from: String, to: String): Double {
        val json = bingApiProvider.sendRequestForDistance(from, to)
        return bingApiDistanceDeserializer.deserialize(json)
    }
}