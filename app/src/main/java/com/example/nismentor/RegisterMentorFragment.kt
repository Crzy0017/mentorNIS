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


class RegisterMentorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mentor_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = view.findViewById<EditText>(R.id.et_first_name)
        val lastName = view.findViewById<EditText>(R.id.et_last_name)
        val email = view.findViewById<EditText>(R.id.et_email)
        val password = view.findViewById<EditText>(R.id.et_reg_password)

        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        view.findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            if(firstName.text.toString() != "" && lastName.text.toString() != "" && email.text.toString().matches(emailPattern) && email.text.toString() != "" && password.text.toString() != ""){
                register(firstName.text.toString(), lastName.text.toString(), email.text.toString(), password.text.toString())
            }
        }
    }

    fun register(firstname: String, lastname: String, email: String, password: String){
        val requestQueue = Volley.newRequestQueue(context)
        val url = "https://singularitybooking.herokuapp.com/api/v1/sign-up"
        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("Response", response)
                if(response == "Registration is successful!"){
                    login(email, password)
                }
                //val jsonObject = JSONTokener(response).nextValue() as JSONObject
                /*val valiDate = jsonObject.getJSONObject("error").getString("code")

                if(valiDate == "0"){
                    finish()
                }*/

            },
            Response.ErrorListener { error ->
                Log.d("Error.Response", error.toString())
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            /*override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $verAccessToken"
                println(headers)
                return headers
            }*/

            override fun getBody(): ByteArray {
                val jsonBody = JSONObject()

                jsonBody.put("firstName", firstname)
                jsonBody.put("lastName", lastname)
                jsonBody.put("email", email)
                jsonBody.put("password", password)
                val requestBody = jsonBody.toString()
                return requestBody.toByteArray()
            }
        }
        requestQueue.add(postRequest)

    }

    fun login(username: String, password: String){
        val requestQueue = Volley.newRequestQueue(context)
        val url = "https://singularitybooking.herokuapp.com/api/v1/sign-in"
        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("Response", response)

                val jsonObject = JSONTokener(response).nextValue() as JSONObject

                val firstName = jsonObject.getString("firstName")
                val lastName = jsonObject.getString("lastName")
                val id = jsonObject.getInt("id")
                val token = jsonObject.getString("token")
                println(id)

                val user = User(id, token)

                val bundle = Bundle()
                bundle.putParcelable("user", user)
                val fragment = UserPageFragment()
                fragment.arguments = bundle

                parentFragmentManager.popBackStack()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, "UserPageFragment")
                    .commit()

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