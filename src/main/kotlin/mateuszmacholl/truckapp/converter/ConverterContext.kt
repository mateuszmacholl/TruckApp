package mateuszmacholl.truckapp.converter

import mateuszmacholl.truckapp.utils.FactoryContext
import org.springframework.stereotype.Service

@Service //factory class
class ConverterContext: FactoryContext<Converter<*, *>>()