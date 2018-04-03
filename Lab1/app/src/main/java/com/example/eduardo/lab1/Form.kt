package com.example.eduardo.lab1

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by eduardo on 03-04-18.
 */


@Entity
class Form {
    @PrimaryKey(autoGenerate = true)
    var id = 1
    lateinit var name: String
    lateinit var date: String
    lateinit var category: String
    lateinit var comment: String
}