package com.example.assigmenttestvascomm.ui.auth.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assigmenttestvascomm.R
import com.example.assigmenttestvascomm.databinding.FragmentLoginBinding
import com.example.assigmenttestvascomm.ui.auth.model.remote.request.LoginRequestBody
import com.example.assigmenttestvascomm.ui.auth.model.remote.response.LoginResponse
import com.example.assigmenttestvascomm.ui.auth.repository.remote.AuthRepository
import com.example.assigmenttestvascomm.ui.auth.view_model.AuthViewModel
import com.example.assigmenttestvascomm.ui.auth.view_model.AuthViewModelFactory
import com.example.assigmenttestvascomm.ui.home.view.HomeActivity
import com.example.assigmenttestvascomm.utils.ResponseState
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

//    private lateinit var sharedPref: PreferencesHelper

    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        responseLoginObserver()

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.register)
        }

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.etEmailInput.text.toString()
            val inputPassword = binding.etPasswordInput.text.toString()

            if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()
            ) {
                // Process login
                val requestBody = LoginRequestBody()
                requestBody.email = inputEmail
                requestBody.password = inputPassword

                viewModel.processLogin(requestBody)

            } else {
                snackbar = Snackbar.make(
                    binding.parentView,
                    "Lengkapi form",
                    Snackbar.LENGTH_SHORT
                )
                snackbar?.show()
            }
        }

    }

    private fun responseLoginObserver() {

        viewModel.loginResponse.observe(viewLifecycleOwner) { stateData ->

            when (stateData) {
                is ResponseState.Loading -> {
                    // Show loading

                }

                is ResponseState.Success -> {
                    // success
                    snackbar = Snackbar.make(
                        binding.parentView,
                        "Sukses",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar?.show()

                    saveDataLogin(stateData.data!!)

                    // intent to main activity
                    startActivity(Intent(activity, HomeActivity::class.java))

                }

                is ResponseState.Error -> {

                    // error
                    snackbar = Snackbar.make(
                        binding.parentView,
                        "${stateData.message}",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar?.show()
                }
            }

        }

    }

    private fun initializeViewModel() {

//        sharedPref = PreferencesHelper(requireContext())
//        val token: String? = sharedPref.getString(Constants.TOKEN)
//        Log.d("LoginFragment", "initializeViewModel: Token: $token")

        val repository = AuthRepository()
        val authModelFactory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this,
            authModelFactory
        )[AuthViewModel::class.java]

    }

    private fun saveDataLogin(dataLogin: LoginResponse.Response) {

//        // save into database (sharedPreferences)
//        val token = dataLogin.token // get Token
//

    }

}