package mateuszmacholl.truckapp.service

import mateuszmacholl.truckapp.model.Transit
import mateuszmacholl.truckapp.repo.TransitRepo
import mateuszmacholl.truckapp.specification.TransitSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TransitService(private val transitRepo: TransitRepo) {

    fun getAll(transitSpec: TransitSpec, pageable: Pageable): Page<Transit> {
        return transitRepo.findAll(transitSpec, pageable)
    }

    fun save(fileEntity: Transit): Transit {
        return this.transitRepo.save(fileEntity)
    }

    fun getAllInExtent(startDate: LocalDate, endDate: LocalDate): List<Transit> {
        return transitRepo.findByDateBetweenOrderByDateAsc(
                startDate,
                endDate
        )
    }

    fun getGroupedByDayInActualMonth(): Collection<List<Transit>> {
        val startDate = LocalDate.now().withDayOfMonth(1)
        val endDate = LocalDate.now().plusDays(1)
        val transits = getAllInExtent(startDate, endDate)
        return transits.groupBy { it.date }.values
    }
}