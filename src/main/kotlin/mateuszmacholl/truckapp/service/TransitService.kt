package mateuszmacholl.truckapp.service

import mateuszmacholl.truckapp.model.Transit
import mateuszmacholl.truckapp.repo.TransitRepo
import mateuszmacholl.truckapp.specification.TransitSpec
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class TransitService(private val transitRepo: TransitRepo,
                     private val localDateService: LocalDateService) {


    fun getAll(transitSpec: TransitSpec, pageable: Pageable): Page<Transit> {
        return transitRepo.findAll(transitSpec, pageable)
    }

    @Cacheable(value = ["transits"], key = "#id")
    fun findById(id: Int): Optional<Transit> {
        return transitRepo.findById(id)
    }

    @CachePut(value = ["transits"], key = "#transit.id")
    @CacheEvict(value = ["reports"], allEntries = true)
    fun save(transit: Transit): Transit {
        return this.transitRepo.save(transit)
    }

    @Cacheable(value = ["reports"])
    fun getAllInExtent(startDate: LocalDate, endDate: LocalDate): List<Transit> {
        val fixedStartDate = startDate.plusDays(1)
        val fixedEndDate = endDate.plusDays(1)
        return transitRepo.findByDateBetweenOrderByDateAsc(
                fixedStartDate,
                fixedEndDate
        )
    }

    fun getGroupedByDayInCurrentMonth(): Collection<List<Transit>> {
        val startDate = localDateService.getCurrentDateWithFirstDayOfMonth()
        val endDate = localDateService.getCurrentDate()
        val transits = getAllInExtent(startDate, endDate)
        return transits.groupBy { it.date }.values
    }
}