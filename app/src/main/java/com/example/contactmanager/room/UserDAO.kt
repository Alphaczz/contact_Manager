package com.example.contactmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contactmanager.room.User

@Dao
interface UserDAO
{
    @Insert
    suspend fun insertUser(user: User) :Long
    // suspend is a key word used to execute to long running operation
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete from user")
    suspend fun deleteAll()
    @Query("Select * from user ")
    fun getAllUserFromDB():LiveData<List<User>>


}