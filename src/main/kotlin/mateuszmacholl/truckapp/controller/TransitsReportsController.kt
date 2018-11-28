package mateuszmacholl.truckapp.controller

import mateuszmacholl.truckapp.converter.ConverterContext
import mateuszmacholl.truckapp.converter.LocalDateConverter
import mateuszmacholl.truckapp.converter.TransitAverageReportConverter
import mateuszmacholl.truckapp.converter.TransitReportConverter
import mateuszmacholl.truckapp.dto.DateExtentDto
import mateuszmacholl.truckapp.exception.ApiError
import mateuszmacholl.truckapp.service.TransitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
@RequestMapping(value = ["/transits/reports"])
class TransitsReportsController(private val converterContext: ConverterContext,
                                private val transitService: TransitService) {

    @RequestMapping(value = ["/extent"], method = [RequestMethod.GET])
    fun getAllInExtent(@Validated dateExtent: DateExtentDto, bindingResult: BindingResult): ResponseEntity<*> {
        if(bindingResult.hasErrors()){
            val errors = bindingResult.allErrors.map { it.defaultMessage }
            val apiError = ApiError(HttpStatus.BAD_REQUEST, errors.joinToString())
            return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
        }
        val startLocalDate = converterContext.get(LocalDateConverter::class.java).convert(dateExtent.startDate)
        val endLocalDate = converterContext.get(LocalDateConverter::class.java).convert(dateExtent.endDate)
        val transits = transitService.getAllInExtent(startLocalDate, endLocalDate)
        val report = converterContext.get(TransitReportConverter::class.java).convert(transits)
        return ResponseEntity(report, HttpStatus.OK)
    }

    @RequestMapping(value = ["/daily"], method = [RequestMethod.GET])
    fun getDailyReportInCurrentMonth(): ResponseEntity<*> {
        val groupedByDayInCurrentMonth = transitService.getGroupedByDayInCurrentMonth()
        val reports = groupedByDayInCurrentMonth.map {
            converterContext.get(TransitAverageReportConverter::class.java).convert(it)
        }
        return ResponseEntity(reports, HttpStatus.OK)
    }
}