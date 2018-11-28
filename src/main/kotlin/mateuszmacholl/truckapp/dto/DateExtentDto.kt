package mateuszmacholl.truckapp.dto

import mateuszmacholl.truckapp.validation.localDate.dateExtent.DateExtent

@DateExtent(startDate = "startDate", endDate = "endDate")
data class DateExtentDto(
        val startDate: String,
        val endDate: String
)