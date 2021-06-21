package com.enigmacamp.mysimpleespresso.data

import java.util.*

data class Spent(
    val spentId: String = UUID.randomUUID().toString(),
    val spentDate: Date,
    val spentAmount: Double,
    val spentDescription: String
)