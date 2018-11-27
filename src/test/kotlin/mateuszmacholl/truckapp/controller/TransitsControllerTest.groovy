package mateuszmacholl.truckapp.controller

import mateuszmacholl.truckapp.model.Transit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDate

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransitsControllerTest extends Specification {
    @Autowired
    private TestRestTemplate restTemplate

    private String path = "/transits"

    def "get all"() {
        when:
        def response = restTemplate.getForEntity(path, String.class)
        then:
        HttpStatus.OK == response.statusCode
        response.body.length() > 0
    }

    def "add"() {
        given:
        def sourceAddress = "Wejherowo"
        def destinationAddress = "Gdynia"
        def price = 450
        def date = "2018-03-16"
        def body = [
                "sourceAddress"     : sourceAddress,
                "destinationAddress": destinationAddress,
                "price"             : price,
                "date"              : date
        ]
        when:
        def response = restTemplate.postForEntity(path, body, Transit.class)
        then:
        HttpStatus.OK == response.statusCode
        response.body.distance == 23.972d
        response.body.date == LocalDate.of(2018, 03, 16)
        response.body.price == price
    }

    def "add, bad request because of incorrect address"() {
        given:
        def sourceAddress = "XXX"
        def destinationAddress = "Gdynia"
        def price = 450
        def date = "2018-03-16"
        def body = [
                "sourceAddress"     : sourceAddress,
                "destinationAddress": destinationAddress,
                "price"             : price,
                "date"              : date
        ]
        when:
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }
}
