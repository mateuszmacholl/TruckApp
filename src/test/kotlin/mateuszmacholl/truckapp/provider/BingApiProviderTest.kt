package mateuszmacholl.truckapp.provider

import mateuszmacholl.truckapp.exception.bingApi.BingApiUrlConnectionException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class BingApiProviderTest {
    @Autowired
    private lateinit var bingApiProvider: BingApiProvider
    private val from = "Wejherowo"
    private val to = "Gdynia"
    private val incorrectAddress = "xxxyyy"

    @Test
    fun sendRequestForDistance() {
        val json = bingApiProvider.sendRequestForDistance(from, to)
        assertTrue(json.isNotEmpty())
    }

    @Test
    fun sendRequestForDistance_incorrectAddress_throwBingApiUrlConnectionException(){
        //then
        Assertions.assertThrows<BingApiUrlConnectionException>(BingApiUrlConnectionException::class.java) {
            //when
            bingApiProvider.sendRequestForDistance(incorrectAddress, to)
        }
    }
}