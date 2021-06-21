package com.enigmacamp.mysimpleespresso.data

class SpentDatabase {
    fun add(spent: Spent) = mySpent.add(spent)

    fun getAll() = mySpent

    companion object {
        val mySpent = mutableListOf<Spent>()
    }
}