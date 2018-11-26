package mateuszmacholl.truckapp.repo

import mateuszmacholl.truckapp.model.Transit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TransitRepo : JpaRepository<Transit, Int>, JpaSpecificationExecutor<Transit> {
    fun findByDateBetweenOrderByDateAsc (start: LocalDate, end: LocalDate): List<Transit>
}