package com.example.projeto01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto01.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityPrincipalBinding
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.botaoSaira.setOnClickListener{
            auth.signOut()

            val intent = Intent(this,
                MainActivity::class.java)

            startActivity(intent)

            finish()


        }

    }
}