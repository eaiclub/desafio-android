package br.com.versa.data.remote

import br.com.versa.data.remote.model.*
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    /**
     * User
     */

    @GET("user/me")
    fun getUser(): Call<UserResponse>

    @POST("user")
    fun registerUser(@Body userRequest: UserRequest): Call<UserResponse>

    @PUT("user/push-token")
    fun sendPushToken(@Body pushTokenRequest: PushTokenRequest): Call<Unit>

    @PUT("user/customer")
    fun editUser(@Body editUserRequest: EditUserRequest): Call<UserResponse>

    @PUT("user/customer")
    fun sendFeedback(@Body sendFeedback: UserFeedback): Call<Unit>

    @GET("utils/secure-upload-url")
    fun getSecureUrl(@Query("imageName") imageName: String): Call<SecureUrlResponse>

    /**
     * Vehicle
     */

    @GET("brand/selectives")
    fun getBrands(): Call<List<VehicleItem>>

    @GET("model/selectives")
    fun getModels(@Query("brandId") brandId: String): Call<List<VehicleItem>>

    @POST("vehicle")
    fun registerVehicle(@Body vehicle: Vehicle): Call<VehicleResponse>

    @GET("vehicle")
    fun getVehicles(): Call<VehicleListResponse>

    @GET("vehicle")
    fun getVehiclesList(@Query("limit") limit: Int,
                        @Query("offset") offset: Int): Call<BasePaginatedModel<VehicleResponse>>

    @DELETE("vehicle/{id}")
    fun deleteVehicle(@Path("id") id: String): Call<Unit>

    @PUT("vehicle/{id}")
    fun editVehicle(@Path("id") id: String,
                    @Body vehicle: Vehicle): Call<VehicleResponse>

    /**
     * Fuel
     */

    @GET("fuel/selectives")
    fun getFuels(): Call<List<FuelResponse>>

    /**
     * Address
     */

    @POST("user-address")
    fun createAddress(@Body addressRequest: AddressRequest): Call<AddressResponse>

    @GET("user-address")
    fun getAddresses(): Call<List<AddressResponse>>

    @DELETE("user-address/{id}")
    fun deleteAddress(@Path("id") id: String): Call<Unit>

    @PUT("user-address/{id}")
    fun editAddress(@Path("id") id: String,
                    @Body addressRequest: AddressRequest): Call<AddressResponse>

    @GET("utils/address")
    fun getAddressByZipCode(@Query("cep") zipCode: String): Call<ZipCodeResponse>

    /**
     * Order
     */

    @GET("utils/valid-schedule-date")
    fun isValidScheduleDate(@Query("date") date: String): Call<Boolean>

    @GET("order/my-active")
    fun getOrders(): Call<BasePaginatedModel<OrderRequest>>

    @GET("order/not-evaluated")
    fun getOrdersNotEvaluated(): Call<List<OrderNotEvaluated>>

    @PUT("order/{orderId}/evaluate")
    fun sendEvaluateOrder(@Path("orderId") id: String,
                          @Body evaluateRequest: EvaluateRequest): Call<Unit>

    @GET("order/{orderId}")
    fun getOrderById(@Path("orderId") id: String): Call<OrderDetail>

    @POST("order")
    fun performOrder(@Body orderRequest: OrderRequest): Call<OrderRequest>

    @POST("order-problem")
    fun sendProblemOrder(@Body orderProblem: OrderProblem): Call<Unit>

    @GET("order")
    fun getHistoric(@Query("limit") limit: Int,
                    @Query("offset") offset: Int): Call<BasePaginatedModel<OrderHistoricItem>>

    @GET("order/active")
    fun getHistoricActive(@Query("limit") limit: Int,
                    @Query("offset") offset: Int): Call<BasePaginatedModel<OrderHistoricItem>>

    /**
     * Credit Card
     */

    @POST("credit-card")
    fun registerCreditCard(@Body creditCard: CreditCard): Call<CreditCard>

    @GET("credit-card")
    fun getCreditCards(): Call<List<CreditCard>>

    @DELETE("credit-card/{id}")
    fun deleteCreditCard(@Path("id") id: String): Call<Unit>

}