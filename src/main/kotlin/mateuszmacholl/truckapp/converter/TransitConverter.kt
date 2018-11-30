package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dto.TransitDto
import mateuszmacholl.truckapp.model.Transit
import mateuszmacholl.truckapp.utils.DistanceCalculator
import org.springframework.stereotype.Service

@Service
class TransitConverter(private val distanceCalculator: DistanceCalculator) : Converter<TransitDto, Transit> {

    override fun convert(from: TransitDto): Transit {
        val distance = distanceCalculator.calc(from.sourceAddress, from.destinationAddress)
        return Transit(
                distance = distance,
                price = from.price,
                date = from.date
        )
    }
}