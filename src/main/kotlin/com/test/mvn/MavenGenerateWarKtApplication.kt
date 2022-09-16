package com.test.mvn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MavenGenerateWarKtApplication

fun main(args: Array<String>) {
    runApplication<MavenGenerateWarKtApplication>(*args)
}