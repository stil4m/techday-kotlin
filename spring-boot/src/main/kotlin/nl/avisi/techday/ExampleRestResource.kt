package nl.avisi.techday

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleRestResource {

    fun data() =
            Pair("hello", "world")

    @GetMapping(path = arrayOf("/example"))
    fun example() =
            data().let { mapOf(it) }
                    .let { ResponseEntity.ok(it) }
}