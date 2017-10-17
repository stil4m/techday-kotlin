package nl.avisi.techday

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleRestResourceTest {

    @Test
    fun foo() {
        val example = ExampleRestResource().data();
        assertEquals(example, Pair("hello", "world"))
    }

}