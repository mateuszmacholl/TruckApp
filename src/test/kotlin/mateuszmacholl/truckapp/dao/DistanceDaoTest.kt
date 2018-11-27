package mateuszmacholl.truckapp.dao

import mateuszmacholl.truckapp.connector.BingApiConnector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DistanceDaoTest {
    private val bingApiConnector = Mockito.mock(BingApiConnector::class.java)
    private val distanceDao = DistanceDao(bingApiConnector)
    private val from = "xxx"
    private val to = "zzz"

    @BeforeEach
    fun init(){
        Mockito.`when`(bingApiConnector.getDistance(from, to)).thenReturn(10.0)
    }

    @Test
    fun getDistance(){
        val distance = distanceDao.getDistance(from, to)
        assertEquals(10.0, distance)
    }
}