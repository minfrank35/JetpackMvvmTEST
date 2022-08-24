package com.example.vichaicompany.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vichaicompany.repository.LoginRepositoryImpl
import com.example.vichaicompany.ui.theme.VichaiCompanyTheme
import com.example.vichaicompany.viewmodel.LoginViewModel
import com.example.vichaicompany.viewmodel.SessionViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SessionViewModel.requestLogin()

        setContent {
            VichaiCompanyTheme {
                // A surface container using the 'background' color from the theme
                Column() {
                    Text(text = SessionViewModel.accessToken.value.toString())
                    Text(text = SessionViewModel.refreshToken.value.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VichaiCompanyTheme {
        Greeting("Android")
    }
}