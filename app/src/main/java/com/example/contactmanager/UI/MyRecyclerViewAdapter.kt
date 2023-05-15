package com.example.contactmanager.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.R
import com.example.contactmanager.databinding.CardBinding
import com.example.contactmanager.room.User

class MyRecyclerViewAdapter(private val  usersLis:List<User>, private val clickListener: (User)->Unit):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater=LayoutInflater.from(parent.context)
//        val binding:CardBinding=DataBindingUtil.inflate(layoutInflater, R.layout.card,parent,false)
//             return MyViewHolder(binding)
        val binding:CardBinding=DataBindingUtil.inflate(layoutInflater,R.layout.card,parent,false)
        return MyViewHolder(binding)
}

    override fun getItemCount(): Int {

        return usersLis.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(usersLis[position],clickListener)
    }


}
class MyViewHolder(val binding:CardBinding):RecyclerView.ViewHolder(binding.root)
{
    fun bind(user:User,clickListener: (User) -> Unit){
        binding.nameTxtView.text=user.name
        binding.emailTxtView.text=user.email
        binding.listitemlayout.setOnClickListener{
            clickListener(user)
        }
    }
}
