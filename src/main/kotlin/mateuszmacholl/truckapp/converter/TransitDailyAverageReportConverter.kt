package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dto.TransitDailyAverageReport
import mateuszmacholl.truckapp.model.Transit
import org.springframework.stereotype.Service

@Service
class TransitDailyAverageReportConverter: Converter<List<Transit>, TransitDailyAverageReport> {
    override fun convert(from: List<Transit>): TransitDailyAverageReport {
        val averageDistance = from.sumByDouble { it.distance } / from.size
        val averagePrice = from.sumByDouble { it.price } / from.size
        return TransitDailyAverageReport(
                averageDistance = averageDistance,
                averagePrice = averagePrice,
                date = from.first().date
        )
    }
}