package nl.avisi.techday

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@RestController
class ExampleRestResource {

    @Autowired private lateinit var entityManager : EntityManager

    @GetMapping(path = arrayOf("/example"))
    fun example() =
            "SELECT 1"
                    .let { entityManager.createNativeQuery(it).singleResult }
                    .let { Pair("database_result", it) }
                    .let { mapOf(it) }
                    .let { ResponseEntity.ok(it) }
}