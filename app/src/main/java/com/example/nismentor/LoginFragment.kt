package com.example.nismentor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.JSONTokener

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = view.findViewById<EditText>(R.id.et_username)
        val password = view.findViewById<EditText>(R.id.et_password)

        view.findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
            if(username.text.toString() != "" && password.text.toString() != ""){
                login(username.text.toString(), password.text.toString())
            }
        }
    }

    fun login(username: String, password: String){
        val requestQueue = Volley.newRequestQueue(context)
        val url = ""
        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("Response", response)
                if(response != "com.android.volley.AuthFailureError") {
                    val jsonObject = JSONTokener(response).nextValue() as JSONObject

                    val firstname = jsonObject.getString("firstName")
                    val lastname = jsonObject.getString("lastName")
                    val id = jsonObject.getInt("id")
                    val token = jsonObject.getString("token")

                    val user = User(id, token)

                    val bundle = Bundle()
                    bundle.putParcelable("user", user)
                    val fragment = UserPageFragment()
                    fragment.arguments = bundle

                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment, "UserpageFragment")
                        .addToBackStack("LoginFragment")
                        .commit()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Error.Response", error.toString())
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                val jsonBody = JSONObject()

                jsonBody.put("username", username)
                jsonBody.put("password", password)
                val requestBody = jsonBody.toString()
                return requestBody.toByteArray()
            }
        }
        requestQueue.add(postRequest)
    }
}