package com.example.routes

import com.example.repository.HeroRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject

fun Routing.searchHeroes() {


    get("/boruto/heroes/search") {
        val heroRepository = call.application.get<HeroRepository>()
        val name = call.request.queryParameters["name"]

        val apiResponse = heroRepository.searchHeroes(name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}