package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject

fun Route.getAllHeroes(){

    get("/anime/heroes"){
        val heroRepository = call.application.get<HeroRepository>()

        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page = page)

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        }  catch (e : NumberFormatException){
            call.respond(
                message = ApiResponse(false, "Only numbers are allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException){
            call.respond(
                message = ApiResponse(false, "Heroes not Found."),
                status = HttpStatusCode.NotFound
            )
        }
    }
}