package mateuszmacholl.truckapp.controller

import mateuszmacholl.truckapp.dto.TransitAverageReport
import mateuszmacholl.truckapp.dto.TransitReport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransitsReportsControllerTest extends Specification {
    @Autowired
    private TestRestTemplate restTemplate

    private String path = "/transits/reports"

    def "get all in extent"() {
        given:
        def startDate = "2018-11-01"
        def endDate = "2018-11-30"
        when:
        def response = restTemplate.getForEntity(
                path + "/extent?startDate=" + startDate + "&endDate=" + endDate, TransitReport.class
        )
        then:
        HttpStatus.OK == response.statusCode
        response.body != null
    }

    def "get daily report in current month"() {
        when:
        def response = restTemplate.getForEntity(
                path + "/daily", TransitAverageReport[].class
        )
        then:
        HttpStatus.OK == response.statusCode
        response.body != null
        response.body.length > 0
    }
}
