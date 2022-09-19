package com.test.mvn

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/hello")
    fun hello(): String {
        return "index"
    }
}