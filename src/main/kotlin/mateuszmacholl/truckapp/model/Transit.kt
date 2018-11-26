package mateuszmacholl.truckapp.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Transit(
        val distance: Double,
        val price: Double,
        @JsonFormat(pattern="yyyy-MM-dd")
        val date: LocalDate
) {
    @Id
    @GeneratedValue
    var id: Int? = null
}