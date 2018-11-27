package mateuszmacholl.truckapp.connector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class BingApiConnectorTest {
    @Autowired
    private lateinit var bingApiConnector: BingApiConnector
    private val from = "Wejherowo"
    private val to = "Gdynia"

    @Test
    fun getDistance() {
        //when
        val distance = bingApiConnector.getDistance(from, to)
        assertEquals(23.972, distance)
        //then
    }
}