package com.example.crosoftentechnicalchallenge.presentation.ui.views


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.crosoftentechnicalchallenge.presentation.ui.components.IconBottomBar
import com.example.crosoftentechnicalchallenge.presentation.ui.components.items.BottomNavItem
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.BookRegisterScreenRoute
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.BottomBarNavigation
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.FeedScreenRoute
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.ProfileScreenRoute
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.SearchScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController(), onLogout: () -> Unit) {

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {

        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(90.dp)
                    .background(Color.White)
                ,
                containerColor = Color.White,
                content = {
                    BottomNavItem.itens.forEachIndexed { index, item ->
                        if (index == 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        IconBottomBar(
                            item = item,
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                when (index) {
                                    0 -> navHostController.navigate(FeedScreenRoute)
                                    1 -> navHostController.navigate(SearchScreenRoute)
                                    2 -> navHostController.navigate(BookRegisterScreenRoute)
                                    3 -> navHostController.navigate(ProfileScreenRoute)
                                }
                            }
                        )

                        if (index == BottomNavItem.itens.size - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
        }, content = { pd ->
            Box(
                modifier = Modifier
                    .padding(pd)
                    .background(Color.White)
            ){
                BottomBarNavigation(navHostController = navHostController, onLogout)
            }
        }
    )
}
