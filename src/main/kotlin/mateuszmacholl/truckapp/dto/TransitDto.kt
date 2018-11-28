package mateuszmacholl.truckapp.dto

import mateuszmacholl.truckapp.validation.utils.filled.Filled
import java.time.LocalDate


data class TransitDto(
        val price: Double,
        val date: LocalDate,
        @field:Filled
        val sourceAddress: String,
        @field:Filled
        val destinationAddress: String
)