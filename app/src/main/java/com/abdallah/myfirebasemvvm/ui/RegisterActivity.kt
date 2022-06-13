package com.abdallah.myfirebasemvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdallah.myfirebasemvvm.R
import com.abdallah.myfirebasemvvm.databinding.ActivityRegisterBinding
import com.abdallah.myfirebasemvvm.util.Resource
import com.abdallah.myfirebasemvvm.viewModel.MainViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.userRegisterButton.setOnClickListener {
            viewModel.createUser(
                binding.edxtUserName.editText?.text.toString(),
                binding.edxtEmailAddress.editText?.text.toString(),
                binding.edxtPhoneNum.editText?.text.toString(),
                binding.edxtPassword.editText?.text.toString()
            )
        }

        viewModel.userRegistrationStatus.observe(this, Observer {
            when(it){
                is Resource.Loading -> {
                    binding.registerProgress.isVisible = true
                }
                is Resource.Success -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Error -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}