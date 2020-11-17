package br.com.mypets.presentation.home.presenter

import android.content.Context
import android.view.View
import br.com.mypets.data.local.SessionDataSource
import br.com.mypets.data.remote.model.Pets
import br.com.mypets.data.remote.model.User
import br.com.mypets.data.repository.UserRepository
import br.com.mypets.mechanism.livedata.MutableLiveDataResource
import br.com.mypets.mechanism.livedata.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*

class HomePresenter(
    private val context: Context,
    private val userRepository: UserRepository
) {

    fun getMe() = userRepository.getMe()

    fun getCurrentPet() = userRepository.getCurrentPet()

    fun logout() = userRepository.logout()

}











