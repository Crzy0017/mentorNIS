package com.example.nismentor.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.nismentor.R
import com.example.nismentor.presentation.ui.loginpage.LoginFragment
import com.example.nismentor.presentation.ui.registerpage.RegisterFragment

class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSignUp = view.findViewById<Button>(R.id.btn_sign_in)
        val buttonLogin = view.findViewById<Button>(R.id.btn_login)

        buttonSignUp.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment(), "RegisterFragment")
                .addToBackStack(null)
                .commit()
        }

        buttonLogin.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment(), "LoginFragment")
                .addToBackStack(null)
                .commit()
        }
    }
}