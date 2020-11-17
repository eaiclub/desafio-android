package br.com.versa.data.remote

import br.com.versa.BuildConfig
import br.com.versa.data.local.sharedPref.SessionDataSource
import br.com.versa.mechanism.*
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServiceClient(private val sessionDataSource: SessionDataSource) {

    var webService: WebService
    var loginWebService: LoginWebService
    var uploadWebService: UploadWebService

    init {
        webService = createDefaultWebService(BuildConfig.WS_BASE_URL + BuildConfig.API_PATH + BuildConfig.API_VERSION)
        loginWebService = createLoginWebService(BuildConfig.WS_BASE_LOGIN_URL + BuildConfig.API_PATH)
        uploadWebService = createUploadWebService()
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val TIMEOUT = 30000L

    }

    private fun createDefaultWebService(
        uri: String, gson: Gson = getGson(),
        timeout: Long = TIMEOUT
    ) = createRetrofitAccess(uri, timeout, Interceptor { chain ->

        var request = chain.request()
        val originalHttpUrl = request.url()
        val url = originalHttpUrl.newBuilder().build()

        val builder = request.newBuilder()
        sessionDataSource.getToken()?.let { token ->
            if (token.isNotEmpty()) {
                builder.addHeader(AUTHORIZATION, BEARER.plus(token))
            }
        }
        builder.addHeader(CONTENT_TYPE, APPLICATION_JSON)
        builder.url(url)

        request = builder.build()
        chain.proceed(request)
    }, gson).create(WebService::class.java)

    private fun createLoginWebService(
        uri: String, gson: Gson = getGson(),
        timeout: Long = TIMEOUT
    ) = createRetrofitAccess(uri, timeout, Interceptor { chain ->

        var request = chain.request()
        val originalHttpUrl = request.url()
        val url = originalHttpUrl.newBuilder().build()

        val builder = request.newBuilder()

        builder.addHeader(CONTENT_TYPE, APPLICATION_JSON)
        builder.addHeader(API_KEY, BuildConfig.API_KEY_ID)
        builder.url(url)

        request = builder.build()
        chain.proceed(request)
    }, gson).create(LoginWebService::class.java)

    private fun createUploadWebService(
        gson: Gson = getGson(),
        timeout: Long = TIMEOUT
    ) = createRetrofitAccess(BuildConfig.WS_BASE_URL, timeout, Interceptor { chain ->

        var request = chain.request()
        val originalHttpUrl = request.url()
        val url = originalHttpUrl.newBuilder().build()

        val builder = request.newBuilder()

        builder.url(url)

        request = builder.build()
        chain.proceed(request)
    }, gson).create(UploadWebService::class.java)

    private fun createRetrofitAccess(
        uri: String, timeout: Long, requestInterceptor: Interceptor,
        gson: Gson = getGson()
    ) = Retrofit.Builder().baseUrl(uri).client(
        setupInterceptors(requestInterceptor, timeout).build()
    ).addConverterFactory(
        GsonConverterFactory.create(gson)
    ).build()

    private fun setupInterceptors(
        requestInterceptor: Interceptor,
        timeout: Long
    ) = OkHttpClient.Builder().apply {

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS
            else HttpLoggingInterceptor.Level.NONE

        addInterceptor(loggingInterceptor)
        addInterceptor(requestInterceptor)
        authenticator(WebAuthenticator())
        addNetworkInterceptor(StethoInterceptor())
        connectTimeout(timeout, TimeUnit.SECONDS)
        readTimeout(timeout, TimeUnit.SECONDS)
    }


    private fun getGson() = GsonBuilder().setDateFormat(DATE_FORMAT).create()

}