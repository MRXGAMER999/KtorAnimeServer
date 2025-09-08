package com.example.di

import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImpl
import org.koin.dsl.module

val KoinModule = module {
    single<HeroRepository>{
        HeroRepositoryImpl()
    }
}