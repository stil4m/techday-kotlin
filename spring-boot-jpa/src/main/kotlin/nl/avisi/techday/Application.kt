package nl.avisi.techday

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun init() = CommandLineRunner {
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}