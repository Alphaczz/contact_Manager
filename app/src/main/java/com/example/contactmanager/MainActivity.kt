package com.example.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanager.UI.MyRecyclerViewAdapter
import com.example.contactmanager.viewModel.UserViewModel
import com.example.contactmanager.viewModel.userViewModelFactory
import com.example.contactmanager.databinding.ActivityMainBinding
import com.example.contactmanager.room.User
import com.example.contactmanager.room.UserDataBase
import com.example.contactmanager.room.userrepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //room database
        val dao=UserDataBase.getInstance(application).userDAO
        val repo=userrepository(dao)
        val factory=userViewModelFactory(repo)
        userViewModel=ViewModelProvider(this,factory).get(UserViewModel::class.java)
        binding.myUserViewModel=userViewModel
        binding.lifecycleOwner=this
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun listItemClicked(selectedItem: User) {
Toast.makeText(this,"Selected NAme is ${selectedItem.name}",Toast.LENGTH_LONG).show()
   userViewModel.initUpdateAndDelete(selectedItem) }

    private fun DisplayUserList()
    {
    userViewModel.users.observe(this, Observer {
    binding.recyclerview.adapter=MyRecyclerViewAdapter(
        it
    ) { selectedItem: User -> listItemClicked(selectedItem) }
})
    }

}




