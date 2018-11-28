package mateuszmacholl.truckapp.connector

import mateuszmacholl.truckapp.deserializer.BingApiDistanceDeserializer
import mateuszmacholl.truckapp.provider.BingApiProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BingApiConnectorTest {
    private val bingApiProvider = Mockito.mock(BingApiProvider::class.java)
    private val bingApiDistanceDeserializer = Mockito.mock(BingApiDistanceDeserializer::class.java)
    private val bingApiConnector = BingApiConnector(bingApiProvider, bingApiDistanceDeserializer)
    private val from = "Wejherowo"
    private val to = "Gdynia"

    @BeforeEach
    fun init(){
        Mockito.`when`(bingApiProvider.sendRequestForDistance(from, to)).thenReturn("json")
        Mockito.`when`(bingApiDistanceDeserializer.deserialize("json")).thenReturn(23.972)
    }

    @Test
    fun getDistance() {
        //when
        val distance = bingApiConnector.getDistance(from, to)
        assertEquals(23.972, distance)
        //then
    }
}