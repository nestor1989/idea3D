package com.idea3d.idea3d.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.idea3d.idea3d.R
import com.idea3d.idea3d.databinding.ActivityLoginBinding
import com.idea3d.idea3d.ui.view.main.MainActivity
import com.idea3d.idea3d.ui.viewModel.LoginViewModel
import com.idea3d.idea3d.utils.Constants.Companion.RC_SIGN_IN
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setThemeMain()
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition {false}

        setButtons()
        setObservable()

    }

    private fun setObservable() {
        loginViewModel.updateUI.observe(this, Observer {
            if (it){
                updateUI(loginViewModel.auth.currentUser)
            }
        })
    }



    private fun setButtons() {

        binding.buttonSignin.setOnClickListener{
            val signInIntent = loginViewModel.mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        binding.buttonSkip.setOnClickListener{
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun updateUI(user: FirebaseUser?) {
        user?.let {
            val intent: Intent = Intent(this, MainActivity::class.java)
            Toast.makeText(
                this,
                user.email,
                Toast.LENGTH_SHORT,
            ).show()
            loginViewModel.updateUI.postValue(false)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                e.printStackTrace()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, /*accessToken=*/ null)
        loginViewModel.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = loginViewModel.auth.currentUser
                    Toast.makeText(this, "Authentication Success" + loginViewModel.auth.currentUser?.email, Toast.LENGTH_SHORT).show()
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    fun setThemeMain(){
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)
    }
}