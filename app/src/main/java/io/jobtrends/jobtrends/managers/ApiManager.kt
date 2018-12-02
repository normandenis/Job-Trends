package io.jobtrends.jobtrends.managers

import com.android.volley.NetworkResponse
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.*
import com.android.volley.toolbox.HttpHeaderParser.parseCacheHeaders
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley.newRequestQueue
import io.jobtrends.jobtrends.BuildConfig
import io.jobtrends.jobtrends.dagger.App

class ApiManager {

    companion object {
        private const val ERROR_STATUS_CODE = 404
        private const val ERROR_MESSAGE: String =
            "Nous rencontrons des soucis avec nos serveurs, veuillez nous excuser de la gêne occasionnée."
        private val URL_BASE: String = "https://api.dev.jobtrends.io/"
    }

    private var statusCode: Int = 0
    private val queue: RequestQueue = newRequestQueue(App.app.applicationContext)

    fun request(method: Int, url: String, callback: (Int, String) -> Unit,json: String = "") {
        val request: StringRequest = object : StringRequest(
            method,
            URL_BASE + url,
            Listener<String> { s ->
                callback(statusCode, s)
            },
            ErrorListener { s ->
                callback(s?.networkResponse?.statusCode ?: ERROR_STATUS_CODE, ERROR_MESSAGE)
            }) {

            override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
                statusCode = response.statusCode
                val data: String = try {
                    String(response.data)
                } catch (e: Exception) {
                    ""
                }
                return success(data, parseCacheHeaders(response))
            }

            override fun getBody(): ByteArray = json.toByteArray()

            override fun getHeaders(): MutableMap<String, String> {
                val tmp = mutableMapOf<String, String>()
                tmp["Content-Type"] = "application/json"
                return tmp
            }
        }
        queue.add(request)
    }
}