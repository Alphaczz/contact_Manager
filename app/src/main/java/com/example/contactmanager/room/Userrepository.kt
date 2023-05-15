package com.example.contactmanager.room

import com.example.contactmanager.room.User
import com.example.contactmanager.room.UserDAO

class userrepository(private val dao: UserDAO)
{
val users=dao.getAllUserFromDB()
suspend fun insert(user: User):Long
{
    return dao.insertUser(user)
}
    suspend fun delete(user: User) {
        return  dao.deleteUser(user)
    }
    suspend fun update(user: User){
        return dao.updateUser(user)
    }
    suspend fun deleteAll()
    {
        return dao.deleteAll()
    }

}