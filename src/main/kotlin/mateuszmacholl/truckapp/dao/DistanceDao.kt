package mateuszmacholl.truckapp.dao

import mateuszmacholl.truckapp.connector.BingApiConnector
import org.springframework.stereotype.Component

@Component
class DistanceDao(private val bingApiConnector: BingApiConnector) {
    fun getDistance(from: String, to: String): Double{
        return bingApiConnector.getDistance(from, to)
    }
}