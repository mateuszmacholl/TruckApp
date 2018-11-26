package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.dto.TransitReport
import mateuszmacholl.truckapp.model.Transit
import org.springframework.stereotype.Service

@Service
class TransitReportConverter: Converter<List<Transit>, TransitReport> {
    override fun convert(from: List<Transit>): TransitReport {
        val totalDistance = from.sumByDouble { it.distance }
        val totalPrice = from.sumByDouble { it.price }
        return TransitReport(
                totalPrice = totalPrice,
                totalDistance = totalDistance
        )
    }
}