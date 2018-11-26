package mateuszmacholl.truckapp.specification

import mateuszmacholl.truckapp.model.Transit
import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.domain.Like
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.jpa.domain.Specification

@And(
        Spec(path = "name", spec = Like::class),
        Spec(path = "distance", spec = Equal::class)
)
interface TransitSpec: Specification<Transit>