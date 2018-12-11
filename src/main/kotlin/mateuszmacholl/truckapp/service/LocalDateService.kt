package mateuszmacholl.truckapp.service

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LocalDateService {
    fun getCurrentDateWithFirstDayOfMonth(): LocalDate {
        return LocalDate.now().withDayOfMonth(1)
    }

    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }
}