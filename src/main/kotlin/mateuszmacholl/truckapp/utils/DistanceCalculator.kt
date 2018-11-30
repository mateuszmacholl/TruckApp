package mateuszmacholl.truckapp.utils

import mateuszmacholl.truckapp.connector.BingApiConnector
import org.springframework.stereotype.Component

@Component
class DistanceCalculator(private val bingApiConnector: BingApiConnector) {

    fun calc(from: String, to: String): Double {
        return bingApiConnector.getDistance(from, to)
    }
}