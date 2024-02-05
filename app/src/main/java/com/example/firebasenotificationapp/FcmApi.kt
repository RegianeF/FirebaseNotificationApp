package com.example.firebasenotificationapp

import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApi {

    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )

    //transmissão
    @POST("/broadcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )

}