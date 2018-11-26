package mateuszmacholl.truckapp

import mateuszmacholl.truckapp.config.properties.BingApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication



@SpringBootApplication
@EnableConfigurationProperties(
        BingApiProperties::class
)
class TruckappApplication

fun main(args: Array<String>) {
    runApplication<TruckappApplication>(*args)
}