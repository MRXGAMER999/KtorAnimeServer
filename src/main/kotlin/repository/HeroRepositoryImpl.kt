package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

class HeroRepositoryImpl(
    override val heroes: Map<Int, List<Hero>>,
    override val page1: List<Hero>,
    override val page2: List<Hero>,
    override val page3: List<Hero>,
    override val page4: List<Hero>,
    override val page5: List<Hero>
) : HeroRepository {

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchHeroes(name: String, page: Int): ApiResponse {
        TODO("Not yet implemented")
    }
}