package com.abdallah.myfirebasemvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdallah.myfirebasemvvm.R
import com.abdallah.myfirebasemvvm.databinding.ActivityLoginBinding
import com.abdallah.myfirebasemvvm.util.Resource
import com.abdallah.myfirebasemvvm.viewModel.MainViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonLogin.setOnClickListener {
            viewModel.signInUser(
                binding.editTextLoginEmail.editText?.text.toString(),
                binding.editTextLoginPass.editText?.text.toString()

            )
        }

        viewModel.userSignUpStatus.observe(this, Observer{
            when(it){
                is Resource.Loading -> {
                    binding.loginProgressBar.isVisible = true
                }
                is Resource.Success -> {
                    binding.loginProgressBar.isVisible = false
                    Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Error -> {
                    binding.loginProgressBar.isVisible = false
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}