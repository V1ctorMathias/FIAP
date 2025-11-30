package com.vinheriaagnello.data // Garanta que o pacote está correto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val tipo: String,
    val uva: String,
    val pais: String,
    val valor: String
)
