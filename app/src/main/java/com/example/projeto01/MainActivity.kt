package com.example.projeto01

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.botaoEntrar.setOnClickListener {
            if (TextUtils.isEmpty(binding.editTextUsuario.text)){
               binding.editTextUsuario.error =
                   "campo usuario não pode estar em braco "
            } else if(TextUtils.isEmpty(binding.editTextSenha.text)){
                binding.editTextSenha.error =
                    "campo Senha não pode estar em braco "
            }else{
                loginUsuarioESenha(
                    binding.editTextUsuario.text.toString(),
                    binding.editTextSenha.text.toString()) }
        }

    }

    private fun loginUsuarioESenha(usuario: String,senha : String) {


        auth.signInWithEmailAndPassword(
            usuario,
            senha
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Autenticação feita com sucesso .",
                        Toast.LENGTH_SHORT).show()
                    abrePrincipal()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Autenticação falhou.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // updateUI(null)
                }
            }
    }

    fun abrePrincipal(){
        binding.editTextUsuario.text.clear()
        binding.editTextSenha.text.clear()

        val intent = Intent(this,
            PrincipalActivity::class.java)
        startActivity(intent)

        finish()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser != null){
            if (currentUser.email?.isNotEmpty()   == true)
            Toast.makeText(
                baseContext, "usuario " + currentUser.email + "logado",
                Toast.LENGTH_SHORT
            ).show()
            abrePrincipal()
        }
       // updateUI(currentUser)
    }

}