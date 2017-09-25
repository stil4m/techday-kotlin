package nl.avisi.techday

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleRestResource {

    @GetMapping(path = arrayOf("/example"))
    fun example() =
            Pair("hello", "world")
                    .let { mapOf(it) }
                    .let { ResponseEntity.ok(it) }
}