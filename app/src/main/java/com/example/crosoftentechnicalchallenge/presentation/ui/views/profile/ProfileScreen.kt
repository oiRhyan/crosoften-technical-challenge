package com.example.crosoftentechnicalchallenge.presentation.ui.views.profile

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.data.utils.helpers.UserPersistence
import com.example.crosoftentechnicalchallenge.presentation.MainActivity

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(onLogout: () -> Unit) {

    val context = LocalContext.current
    val user = UserPersistence.getUser()
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall,
            text = "Perfil")
        Spacer(modifier = Modifier.height(50.dp))
        Text("Bem vindo!")
        Text(text = "Usuário", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = user.value.email, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            onLogout()
        }) {
            Text(text = "Sair")
        }
        
    }
}