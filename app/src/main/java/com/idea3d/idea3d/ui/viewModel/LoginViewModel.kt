package com.idea3d.idea3d.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.ui.view.login.LoginActivity
import com.idea3d.idea3d.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(
    private val repo: Repo,
    @ApplicationContext private val context: Context
): ViewModel() {

    var mGoogleSignInClient: GoogleSignInClient
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var updateUI = MutableLiveData<Boolean>()

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.GOOGLE_AUTH_ID)
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(context, gso)

        val currentUser = auth.currentUser
        if (currentUser!=null){
            updateUI.postValue(true)
        }
    }



}