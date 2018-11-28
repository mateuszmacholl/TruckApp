package mateuszmacholl.truckapp.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
abstract class ApiProvider {
    @Autowired
    protected lateinit var restTemplate: RestTemplate
    protected final lateinit var apiUrl: String
}