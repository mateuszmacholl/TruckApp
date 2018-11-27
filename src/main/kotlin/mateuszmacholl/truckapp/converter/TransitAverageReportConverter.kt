package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dto.TransitAverageReport
import mateuszmacholl.truckapp.model.Transit
import org.springframework.stereotype.Service

@Service
class TransitAverageReportConverter: Converter<List<Transit>, TransitAverageReport> {
    override fun convert(from: List<Transit>): TransitAverageReport {
        val averageDistance = from.sumByDouble { it.distance } / from.size
        val averagePrice = from.sumByDouble { it.price } / from.size
        return TransitAverageReport(
                averageDistance = averageDistance,
                averagePrice = averagePrice,
                date = from.first().date
        )
    }
}