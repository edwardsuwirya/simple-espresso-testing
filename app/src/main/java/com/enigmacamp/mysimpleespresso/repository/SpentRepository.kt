package com.enigmacamp.mysimpleespresso.repository

import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.data.SpentDatabase

class SpentRepository(private val spentDatabase: SpentDatabase) {
    suspend fun addSpent(spent: Spent) = spentDatabase.add(spent)

    suspend fun getFirst5(): List<Spent> {
        val spentList = spentDatabase.getAll()
        return if (spentList.size > 5) {
            spentList.slice(0..4)
        } else {
            spentList
        }

    }
}