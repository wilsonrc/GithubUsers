package com.wilsonrc.githubusers.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver


class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel() {


    var usersError: MutableLiveData<String> = MutableLiveData()
    var usersResult: MutableLiveData<List<User>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }


    fun usersResult(): LiveData<List<User>> {
        return usersResult
    }

    fun usersError(): LiveData<String> {
        return usersError
    }

    fun loadUsers() {
        loading.value = true
        val disposable = usersRepository.getUsers(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<User>>() {

                override fun onComplete() {
                }

                override fun onNext(users: List<User>) {
                    loading.value = false
                    usersResult.postValue(users)
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    usersError.postValue(e.message)
                }

            })
        disposables.add(disposable)

    }

    fun loadFavUsers() {
        loading.value = true
        val disposable = usersRepository.getFavUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<User>>() {
                override fun onSuccess(favUsers: List<User>) {
                    loading.value = false
                    usersResult.postValue(favUsers)
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    usersError.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    fun saveFavUser(user: User) {
        loading.value = true
        val disposable = usersRepository.saveFavUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    usersError.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    fun deleteFavUser(user: User) {
        loading.value = true

        if (validateIsNotFav(user)) return

        val disposable = usersRepository.deleteFavUser(user.localId!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    usersError.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    private fun validateIsNotFav(user: User): Boolean {
        if (user.localId == null) {
            loading.value = false
            usersError.postValue("You cannot delete a user that has not been saved before.")
            return true
        }
        return false
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}