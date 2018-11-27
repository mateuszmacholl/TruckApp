package mateuszmacholl.truckapp.controller

import mateuszmacholl.truckapp.converter.ConverterContext
import mateuszmacholl.truckapp.converter.LocalDateConverter
import mateuszmacholl.truckapp.converter.TransitAverageReportConverter
import mateuszmacholl.truckapp.converter.TransitReportConverter
import mateuszmacholl.truckapp.service.TransitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
@RequestMapping(value = ["/transits/reports"])
class TransitsReportsController(private val converterContext: ConverterContext,
                                private val transitService: TransitService) {

    @RequestMapping(value = ["/extent"], method = [RequestMethod.GET])
    fun getAllInExtent(@RequestParam startDate: String,
                       @RequestParam endDate: String): ResponseEntity<*> {
        val startLocalDate = converterContext.get(LocalDateConverter::class.java).convert(startDate)
        val endLocalDate = converterContext.get(LocalDateConverter::class.java).convert(endDate)
        val transits = transitService.getAllInExtent(startLocalDate, endLocalDate)
        val report = converterContext.get(TransitReportConverter::class.java).convert(transits)
        return ResponseEntity(report, HttpStatus.OK)
    }

    @RequestMapping(value = ["/daily"], method = [RequestMethod.GET])
    fun getDailyReportInActualMonth(): ResponseEntity<*> {
        val groupedByDayInActualMonth = transitService.getGroupedByDayInActualMonth()
        val reports = groupedByDayInActualMonth.map {
            converterContext.get(TransitAverageReportConverter::class.java).convert(it)
        }
        return ResponseEntity(reports, HttpStatus.OK)
    }
}