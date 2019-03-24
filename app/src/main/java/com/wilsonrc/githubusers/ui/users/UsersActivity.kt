package com.wilsonrc.githubusers.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilsonrc.githubusers.R
import com.wilsonrc.githubusers.data.models.User
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity() , UserOptionsListener{

    @Inject
    lateinit var usersViewModelFactory : UsersViewModelFactory
    lateinit var usersViewModel : UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        usersViewModel = ViewModelProviders.of(this, usersViewModelFactory)
            .get(UsersViewModel::class.java)

        setupObservers()

        usersViewModel.loadUsers()
    }

    private fun setupObservers() {

        usersViewModel.usersResult.observe(this,
            Observer<List<User>> {
                textNumberOfUsers?.text = "${it?.size ?: 0}"
                rvUsers.adapter = UsersAdapter(this@UsersActivity, it, this@UsersActivity)
                rvUsers.layoutManager = LinearLayoutManager(this@UsersActivity)
            })

        usersViewModel.successMessage.observe(this, Observer<String> {
            Toast.makeText(this@UsersActivity, it, Toast.LENGTH_LONG).show()
        })

        usersViewModel.errorMessage.observe(this, Observer<String> {
            Toast.makeText(this@UsersActivity, it, Toast.LENGTH_LONG).show()
        })

    }

    override fun onFavoriteClicked(user: User) {
        usersViewModel.saveFavUser(user)
    }
}
