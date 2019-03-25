package com.wilsonrc.githubusers.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wilsonrc.githubusers.R
import com.wilsonrc.githubusers.data.models.User
import kotlinx.android.synthetic.main.item_github_user.view.*

class UsersAdapter(
    private val context: Context,
    users: List<User>,
    private val userOptionsListener: UserOptionsListener
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var usersList = ArrayList<User>()

    init {
        this.usersList = users as ArrayList<User>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_github_user,
            parent, false
        )
        return UsersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun replace(users: List<User>) {
        usersList.clear()
        usersList.addAll(users)
        notifyDataSetChanged()
    }

    fun addUsers(users: List<User>) {
        val initPosition = usersList.size
        usersList.addAll(users)
        notifyItemRangeInserted(initPosition, usersList.size)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindUser(usersList[position], context, userOptionsListener)
    }

    class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindUser(user: User, context: Context?, userOptionsListener: UserOptionsListener) {
            with(user) {
                context?.let {
                    Glide.with(context).load(avatarUrl).into(itemView.imageAvatar)
                }
                itemView.textUserName.text = name
                itemView.btnImageStar.setOnClickListener {
                    userOptionsListener.onFavoriteClicked(this)
                }
            }
        }
    }
}