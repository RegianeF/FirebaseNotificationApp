package com.example.firebasenotificationapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasenotificationapp.ChatState
import com.example.firebasenotificationapp.FcmApi
import com.example.firebasenotificationapp.NotificationBody
import com.example.firebasenotificationapp.SendMessageDto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException
import java.lang.Exception

class ChatViewModel(

) : ViewModel() {

    //passar por injeção - IP ADDRESS WI-FI
    private val api: FcmApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()


    var state by mutableStateOf(ChatState())
        private set

    fun onRemoteTokenChange(newToken: String) {
        state = state.copy(
            remoteToken = newToken
        )
    }

    fun onSubmitRemoteToken() {
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(message: String) {
        state = state.copy(
            messageText = message
        )
    }

    fun sendMessage(isBroadcast: Boolean) {
        viewModelScope.launch {
            val body = SendMessageDto(
                to = if (isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "New message!",
                    body = state.messageText
                )
            )

            try {
                if (isBroadcast) {
                    api.broadcast(body)
                } else {
                    api.sendMessage(body)
                }

                state = state.copy(
                    messageText = ""
                )
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }

}