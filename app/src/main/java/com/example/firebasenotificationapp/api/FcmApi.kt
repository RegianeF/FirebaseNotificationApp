package com.example.firebasenotificationapp.api

import com.example.firebasenotificationapp.model.SendMessageDto
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