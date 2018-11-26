package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dao.DistanceDao
import mateuszmacholl.truckapp.dto.TransitDto
import mateuszmacholl.truckapp.model.Transit
import org.springframework.stereotype.Service

@Service
class TransitConverter(private val distanceDao: DistanceDao) : Converter<TransitDto, Transit> {

    override fun convert(from: TransitDto): Transit {
        val distance = distanceDao.getDistance(from.sourceAddress, from.destinationAddress)
        return Transit(
                distance = distance,
                price = from.price,
                date = from.date
        )
    }
}