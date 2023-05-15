package com.example.contactmanager.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanager.room.User
import com.example.contactmanager.room.userrepository
import kotlinx.coroutines.launch

class UserViewModel (private val repo: userrepository):ViewModel(),Observable
{
    val users= repo.users
    private  var isUpdatedorDelete=false
    private  lateinit var userToUpdateorDelete:User

    @Bindable
    val inputName=MutableLiveData<String?>()

    @Bindable
    val inputEmail=MutableLiveData<String?>()

    @Bindable
    var saveOrUpdateButtonText=MutableLiveData<String>()
    @Bindable
    var clearOrDeleteButton=MutableLiveData<String>()
    init {
        saveOrUpdateButtonText.value="Save"
        clearOrDeleteButton.value="Clear All"
    }
    fun saveOrUpdate(){
        if (isUpdatedorDelete){
           //make update
            userToUpdateorDelete.name=inputName.value!!
            userToUpdateorDelete.email=inputEmail.value!!
            update(userToUpdateorDelete)
        }else
        {
            val name =inputName.value!!
            val email=inputEmail.value!!
            insert(User(0,name, email))
            inputName.value=null
            inputEmail.value=null
        }

    }
    fun insert(user: User)=viewModelScope.launch {
        repo.insert(user)
    }
    fun clearALlorDelete()
    {if (isUpdatedorDelete)
    {
        delete(userToUpdateorDelete)
    }
        else {
        clearAll()
    }   }
    fun clearAll()=viewModelScope.launch { repo.deleteAll() }
    fun update(user: User)=viewModelScope.launch { repo.update(user)
    //reset buttons and fields
    inputEmail.value=null
    inputName.value=null
    isUpdatedorDelete=false
    saveOrUpdateButtonText.value="Save"
        clearOrDeleteButton.value="Clear All"
    }
    fun delete(user: User)=viewModelScope.launch { repo.delete(user)
        inputEmail.value=null
        inputName.value=null
        isUpdatedorDelete=false
        saveOrUpdateButtonText.value="Save"
        clearOrDeleteButton.value="Clear All"}
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
    fun initUpdateAndDelete(user: User)
    {
        inputEmail.value=user.name
        inputName.value=user.email
        isUpdatedorDelete=true
        userToUpdateorDelete=user
        saveOrUpdateButtonText.value="Update"
        clearOrDeleteButton.value="Delete"
    }


}