package com.example.nit3213finalapp.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.nit3213finalapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting all views from layout
        val etUsername = view.findViewById<EditText>(R.id.etUsername)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // check if empty or not
            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            // if login success then navigate to dashboard
            viewModel.loginState.observe(viewLifecycleOwner) { success ->
                if (success) {
                    Log.e("STATUS","success")

                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(viewModel.getKeyPass())
                    findNavController().navigate(action)
                }
            }

            // showing error if login failed
            viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
