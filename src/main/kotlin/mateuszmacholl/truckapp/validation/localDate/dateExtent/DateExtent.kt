package mateuszmacholl.truckapp.validation.localDate.dateExtent

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Constraint(validatedBy = [DateExtentValidator::class])
annotation class DateExtent(
        val message: String = "",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [],
        val startDate: String,
        val endDate: String
)