package mateuszmacholl.truckapp.dto

import java.time.LocalDate


data class TransitDto(
        val price: Double,
        val date: LocalDate,
        val sourceAddress: String,
        val destinationAddress: String
)