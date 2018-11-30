package mateuszmacholl.truckapp.dao

import mateuszmacholl.truckapp.connector.BingApiConnector
import mateuszmacholl.truckapp.utils.DistanceCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DistanceCalculatorTest {
    private val bingApiConnector = Mockito.mock(BingApiConnector::class.java)
    private val distanceCalculator = DistanceCalculator(bingApiConnector)
    private val from = "xxx"
    private val to = "zzz"

    @BeforeEach
    fun init(){
        Mockito.`when`(bingApiConnector.getDistance(from, to)).thenReturn(10.0)
    }

    @Test
    fun getDistance(){
        val distance = distanceCalculator.calc(from, to)
        assertEquals(10.0, distance)
    }
}