package com.example.projeto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.projeto01.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private  lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        binding.botaoCadastrar.setOnClickListener{
            if (TextUtils.isEmpty(binding.editTextUsuariocad.text)){
                binding.editTextUsuariocad.error =
                    "campo usuario não pode estar em braco "
            } else if(TextUtils.isEmpty(binding.editTextSenacad.text)){
                binding.editTextSenacad.error =
                    "campo Senha não pode estar em braco "
            }else{
                criarUsuarioESenha(
                    binding.editTextUsuariocad.text.toString(),
                    binding.editTextSenacad.text.toString()) }
        }

        binding.possuicadastro.setOnClickListener{
            abrirprincipal()
        }

    }

    private fun abrirprincipal() {
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intent)

        finish()
    }

    private fun criarUsuarioESenha(email: String,senha : String){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this){
            task->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Usuario criado com sucesso",
                    Toast.LENGTH_SHORT).show()
                limpacaposcad()
                    abrirprincipal()
            }else {
                Toast.makeText(
                    baseContext, "Erro na criação do usuario ",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun limpacaposcad() {
        binding.editTextUsuariocad.text.clear()
        binding.editTextUsuariocad.text.clear()
    }
}