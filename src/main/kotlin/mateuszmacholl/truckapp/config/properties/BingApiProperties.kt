package mateuszmacholl.truckapp.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "bing-api")
class BingApiProperties {
    var key: String? = null
}