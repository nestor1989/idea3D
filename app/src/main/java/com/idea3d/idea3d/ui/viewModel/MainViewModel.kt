package com.idea3d.idea3d.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.mapper.GetThingMapper
import com.idea3d.idea3d.data.model.mapper.GetThingsMapper
import com.idea3d.idea3d.data.model.mapper.ThingWithCatMapper
import com.idea3d.idea3d.data.model.mapper.ThingsMapper
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val homeRepository: HomeRepository,
    private val thingsMapper: ThingsMapper
) : ViewModel() {
    @ApplicationContext private val context: Context): ViewModel() {
    val searchThing = MutableLiveData<String>()
    private val page = MutableLiveData<Int>()
    private val category = MutableLiveData<Int>()

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun setThings(thingBy:String){
        searchThing.value = thingBy
    }

    fun setPagination(pages:Int){
        page.value = pages
    }

    fun setCategory(cat:Int){
        category.value = cat
    }

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                if (searchThing.value==Constants.RELEVANT || searchThing.value==Constants.POPULAR || searchThing.value==Constants.NEWEST){
                    val result = homeRepository.getThingsByNews(it, 1, category.value!!)
                    emit(Resource.Success(thingsMapper.mapToUI(result)))
                }else{
                    val result = homeRepository.getThingsByName(it, 1, category.value!!)
                    emit(Resource.Success(thingsMapper.mapToUI(result)))
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchPage = page.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            if (searchThing.value==Constants.RELEVANT || searchThing.value==Constants.POPULAR || searchThing.value==Constants.NEWEST){
                val result = homeRepository.getThingsByNews(searchThing.value!!, page.value!!, category.value!!)
                emit(Resource.Success(thingsMapper.mapToUI(result)))
            }else{
                val result = homeRepository.getThingsByName(searchThing.value!!, page.value!!, category.value!!)
                emit(Resource.Success(thingsMapper.mapToUI(result)))
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun initializeGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(Constants.GOOGLE_AUTH_ID)
        .requestEmail()
        .build()
        mGoogleSignInClient= GoogleSignIn.getClient(context, gso)
    }

    fun logout(){
        auth.signOut()
        mGoogleSignInClient.signOut().addOnCompleteListener {
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
        }
    }


}