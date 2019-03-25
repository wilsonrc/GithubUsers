package com.wilsonrc.githubusers.ui.users

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


class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {


    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var successMessage: MutableLiveData<String> = MutableLiveData()
    var usersResult: MutableLiveData<List<User>> = MutableLiveData()
    var usersRestore : MutableLiveData<List<User>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var usersList : MutableList<User> = mutableListOf()

    var nextPage: String? = null

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }


    init {
        initialUserLoad()
    }

    fun restorePreviousData(){
        usersRestore.postValue(usersList)
    }

    private fun initialUserLoad() {
        loading.value = true
        val disposable = usersRepository.getUsers(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getUsersDisposableObserver())
        disposables.add(disposable)
    }

    fun loadMoreUsers() {
        if (nextPage != null) {
            val disposable = usersRepository.loadMore(nextPage as String)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getUsersDisposableObserver())
            disposables.add(disposable)
        }
    }

    private fun getUsersDisposableObserver(): DisposableObserver<UsersRepository.OutPutPaged> {
        return object : DisposableObserver<UsersRepository.OutPutPaged>() {

            override fun onComplete() {
            }

            override fun onNext(t: UsersRepository.OutPutPaged) {
                loading.value = false
                nextPage = t.nextPage
                usersList.addAll(t.users)
                usersResult.postValue(t.users)
            }

            override fun onError(e: Throwable) {
                loading.value = false
                errorMessage.postValue(e.message)
            }

        }
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
                    errorMessage.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    fun saveFavUser(user: User) {
        loading.value = true
        val disposable = usersRepository.saveFavUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loading.value = false
                    successMessage.postValue("User was saved to favorites successfully")
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    errorMessage.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    fun deleteFavUser(user: User) {
        loading.value = true

        if (validateIsNotFav(user)) return

        val disposable = usersRepository.deleteFavUser(user.localId!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loading.value = false
                    successMessage.postValue("User was deleted from favorites successfully")
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    errorMessage.postValue(e.message)
                }

            })
        disposables.add(disposable)
    }

    private fun validateIsNotFav(user: User): Boolean {
        if (user.localId == null) {
            loading.value = false
            errorMessage.postValue("You cannot delete a user that has not been saved before.")
            return true
        }
        return false
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}