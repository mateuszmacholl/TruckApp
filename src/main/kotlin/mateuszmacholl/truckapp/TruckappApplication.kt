package mateuszmacholl.truckapp

import mateuszmacholl.truckapp.config.properties.BingApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
@EnableConfigurationProperties(
        BingApiProperties::class
)
@EnableCaching
class TruckappApplication

fun main(args: Array<String>) {
    runApplication<TruckappApplication>(*args)
}