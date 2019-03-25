package com.wilsonrc.githubusers.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilsonrc.githubusers.R
import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.utils.InfiniteScrollListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity(), UserOptionsListener {

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory
    lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        usersViewModel = ViewModelProviders.of(this, usersViewModelFactory)
            .get(UsersViewModel::class.java)

        setupUi()
        setupObservers()

    }

    private fun setupUi() {
        usersAdapter = UsersAdapter(this@UsersActivity, arrayListOf<User>(), this@UsersActivity)
        rvUsers?.adapter = usersAdapter
        val linearLayoutManager = LinearLayoutManager(this@UsersActivity)
        rvUsers?.layoutManager = linearLayoutManager

        rvUsers?.addOnScrollListener(
            InfiniteScrollListener(
                { usersViewModel.loadMoreUsers() },
                linearLayoutManager
            )
        )
    }

    private fun setupObservers() {

        usersViewModel.usersResult.observe(this,
            Observer<List<User>> {
                usersAdapter.addUsers(it)
                textNumberOfUsers?.text = "${usersAdapter.itemCount}"
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
