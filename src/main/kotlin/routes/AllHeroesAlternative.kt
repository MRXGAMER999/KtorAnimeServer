package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepositoryAlternative
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroesAlternative() {
    val heroRepositoryAlternative: HeroRepositoryAlternative by application.inject()

    // Single category endpoint (existing)
    get("/anime/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 5
            val category = call.request.queryParameters["category"] ?: "Boruto"

            val apiResponse = heroRepositoryAlternative.getAllHeroes(
                page = page,
                limit = limit,
                category = category
            )
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Heroes not Found."),
                status = HttpStatusCode.NotFound
            )
        } catch (e: Exception) {
            call.respond(
                message = ApiResponse(success = false, message = e.stackTrace.toString()),
                status = HttpStatusCode.NotFound
            )
        }
    }

    // Multiple categories endpoint (NEW!)
    get("/anime/heroes/categories") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 10
            
            // Parse multiple categories from query parameter
            // Example: /anime/heroes/categories?categories=Demon Slayer,Boruto
            val categoriesParam = call.request.queryParameters["categories"]
            val categories = if (categoriesParam.isNullOrEmpty()) {
                emptyList()
            } else {
                categoriesParam.split(",").map { it.trim() }
            }

            val apiResponse = heroRepositoryAlternative.getAllHeroesByCategories(
                page = page,
                limit = limit,
                categories = categories
            )
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Heroes not Found."),
                status = HttpStatusCode.NotFound
            )
        } catch (e: Exception) {
            call.respond(
                message = ApiResponse(success = false, message = "Error: ${e.message}"),
                status = HttpStatusCode.InternalServerError
            )
        }
    }

    // Get all available categories
    get("/anime/heroes/categories/list") {
        try {
            val apiResponse = heroRepositoryAlternative.getAvailableCategories()
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: Exception) {
            call.respond(
                message = ApiResponse(success = false, message = "Error: ${e.message}"),
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}