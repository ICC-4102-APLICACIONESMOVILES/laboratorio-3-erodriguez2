package com.example.eduardo.lab1

import android.arch.persistence.room.*

/**
 * Created by eduardo on 03-04-18.
 */
@Dao
interface DaoAccess {

    @Insert
    fun insertOnlySingleForm (form: Form)
    @Insert
    fun insertMultipleForms (forms: MutableList<Form>)
    @Query("SELECT * FROM Form WHERE id = :arg0")
    fun fetchOneFormsbyFormId (id: Int): Form
    @Update
    fun updateForm (form: Form)
    @Delete
    fun deleteForm (form: Form)
}