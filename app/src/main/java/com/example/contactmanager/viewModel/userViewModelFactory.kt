package com.example.contactmanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanager.room.userrepository

class userViewModelFactory(private val repo:userrepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }


    }


