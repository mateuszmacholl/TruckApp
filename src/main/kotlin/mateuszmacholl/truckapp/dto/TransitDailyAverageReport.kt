package mateuszmacholl.truckapp.dto

import java.time.LocalDate

data class TransitDailyAverageReport(
        val date: LocalDate,
        var averageDistance: Double,
        var averagePrice: Double
)