package com.epitech.reddit_epitechapp

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.epitech.reddit_epitechapp.utils.Constants.Companion.ACCESS_TOKEN
import com.epitech.reddit_epitechapp.utils.Constants.Companion.ACCESS_TOKEN_URL
import com.epitech.reddit_epitechapp.utils.Constants.Companion.AUTH_URL
import com.epitech.reddit_epitechapp.utils.Constants.Companion.CLIENT_ID
import com.epitech.reddit_epitechapp.utils.Constants.Companion.REDIRECT_URI
import com.epitech.reddit_epitechapp.utils.Constants.Companion.STATE
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.NullPointerException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }

    private fun startChange(token: String) {
        startActivity(Intent(this, Main2Activity::class.java).apply {
            putExtra(ACCESS_TOKEN, token)
        })
    }

    private fun getAccessToken(code: String) {

        val authString = "$CLIENT_ID:"
        val encodedAuthString: String = Base64.encodeToString(
            authString.toByteArray(),
            Base64.NO_WRAP
        )
        startClient(Request.Builder()
            .addHeader("User-Agent", "RedditTech App")
            .addHeader("Authorization", "Basic $encodedAuthString")
            .url(ACCESS_TOKEN_URL)
            .post(
                ("grant_type=authorization_code&code=" + code +
                        "&redirect_uri=" + REDIRECT_URI
                        ).toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
            )
            .build())
    }

    private fun startClient(request: Request) {
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(ContentValues.TAG, "ERROR: $e")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val json: String = response.body?.string() ?: "null"
                var data: JSONObject?
                try {
                    data = JSONObject(json)
                    val accessToken = data.optString("access_token")
                    val refreshToken = data.optString("refresh_token")

                    startChange(accessToken)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (intent != null && intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            if (uri!!.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e(ContentValues.TAG, "An error has occurred : $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == STATE) {
                    val code = uri.getQueryParameter("code")
                    getAccessToken(code.toString())
                }
            }
        }
    }


    fun startSignIn(view: View?) {
        val url = String.format(AUTH_URL, CLIENT_ID, STATE, REDIRECT_URI)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}