package com.enigmacamp.mysimpleespresso.data

class SpentDatabase {
    suspend fun add(spent: Spent) = mySpent.add(spent)

    suspend fun getAll() = mySpent

    companion object {
        val mySpent = mutableListOf<Spent>()
    }
}