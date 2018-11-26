package mateuszmacholl.truckapp.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "bing-api")
class BingApiProperties {
    var key: String? = null
}