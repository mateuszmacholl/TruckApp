package mateuszmacholl.truckapp.validation.localDate.dateExtent

import mateuszmacholl.truckapp.converter.ConverterContext
import mateuszmacholl.truckapp.converter.LocalDateConverter
import org.springframework.beans.BeanWrapperImpl
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DateExtentValidator : ConstraintValidator<DateExtent, Any> {
    @Autowired
    private lateinit var converterContext: ConverterContext
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun initialize(constraint: DateExtent) {
        this.startDate = constraint.startDate
        this.endDate = constraint.endDate
    }

    @Throws(Exception::class)
    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()
        val startDateValue = BeanWrapperImpl(value).getPropertyValue(startDate) as String
        val endDateValue = BeanWrapperImpl(value).getPropertyValue(endDate) as String
        try {
            val start = converterContext.get(LocalDateConverter::class.java).convert(startDateValue)
            val end = converterContext.get(LocalDateConverter::class.java).convert(endDateValue)

            if (end.isBefore(start)) {
                context.buildConstraintViolationWithTemplate("End date must be after start date")
                        .addConstraintViolation()
                return false
            } else if(end.isEqual(start)){
                context.buildConstraintViolationWithTemplate("Dates can't be equal")
                        .addConstraintViolation()
                return false
            }
        } catch (ex: Exception) {
            throw Exception("Date is in incorrect format", ex)
        }
        return true
    }

}
