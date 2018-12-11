package mateuszmacholl.truckapp.controller

import mateuszmacholl.truckapp.converter.ConverterContext
import mateuszmacholl.truckapp.converter.TransitConverter
import mateuszmacholl.truckapp.dto.TransitDto
import mateuszmacholl.truckapp.service.TransitService
import mateuszmacholl.truckapp.specification.TransitSpec
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping(value = ["/transits"])
class TransitsController(private val converterContext: ConverterContext,
                         private val transitService: TransitService) {
    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getAll(transitSpec: TransitSpec, pageable: Pageable): ResponseEntity<*> {
        val transits = transitService.getAll(transitSpec, pageable)
        return ResponseEntity(transits, HttpStatus.OK)
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun findById(@PathVariable(value = "id") id: Int): ResponseEntity<*> {
        val transit = transitService.findById(id)
        return if(transit.isPresent){
            ResponseEntity(transit, HttpStatus.OK)
        } else
            ResponseEntity("", HttpStatus.NOT_FOUND)
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun add(@RequestBody @Validated transitDto: TransitDto): ResponseEntity<*> {
        val transit = converterContext.get(TransitConverter::class.java).convert(transitDto)
        val savedTransit = transitService.save(transit)
        return ResponseEntity(savedTransit, HttpStatus.OK)
    }
}