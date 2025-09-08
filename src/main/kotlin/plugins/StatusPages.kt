package com.example.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages(){
    install(StatusPages){
        status(HttpStatusCode.NotFound){
            call.respond(
                message = "Page not Found",
                status = HttpStatusCode.NotFound
            )
        }

    }
}
