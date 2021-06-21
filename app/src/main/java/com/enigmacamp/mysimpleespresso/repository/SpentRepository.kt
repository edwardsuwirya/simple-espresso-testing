package com.enigmacamp.mysimpleespresso.repository

import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.data.SpentDatabase

class SpentRepository(private val spentDatabase: SpentDatabase) {
    suspend fun addSpent(spent: Spent) = spentDatabase.add(spent)

    suspend fun getFirst5() = spentDatabase.getAll().slice(0..4)
}