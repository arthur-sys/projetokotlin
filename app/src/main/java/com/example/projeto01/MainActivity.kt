package com.example.projeto01

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projeto01.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.botaoEntrar.setOnClickListener {
                auth.signInWithEmailAndPassword(
                    binding.editTextUsuario.text.toString(),
                    binding.editTextSenha.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success")
                            Toast.makeText(baseContext, "Autenticação feita com sucesso .",
                                Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                            Toast.makeText(baseContext, "Autenticação falhou.",
                                Toast.LENGTH_SHORT).show()
                           // updateUI(null)
                        }
                    }
        }


    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = auth.currentUser
       // updateUI(currentUser)
    }

}