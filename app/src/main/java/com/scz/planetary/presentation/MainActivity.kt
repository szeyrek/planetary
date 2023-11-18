package com.scz.planetary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.scz.planetary.presentation.apod.views.ApodScreen
import com.scz.planetary.presentation.apoddetail.views.ApodDetailScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val drawerHandlerScope = rememberCoroutineScope()
            var title by remember { mutableStateOf("") }
//            var navigationIcon by remember {
//                mutableStateOf(
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = null
//                    )
//                )
//            }
            var navigationIconClick by remember { mutableStateOf({}) }
            AppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text(text = "Test")
                        }
                    },
                    gesturesEnabled = true
                ) {
                    Scaffold(
                        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            CenterAlignedTopAppBar(
                                colors = TopAppBarDefaults.mediumTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                title = {
                                    Text(
                                        title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        navigationIconClick()
                                    }) {
//                                        navigationIcon()
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = null
                                        )
                                    }
                                },
                                scrollBehavior = scrollBehavior
                            )
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = Screen.ApodScreen.route
                            ) {
                                composable(Screen.ApodScreen.route) {
                                    ApodScreen(
                                        topBarTitle = { title = it },
                                        topBarNavigationIconClick = {
                                            navigationIconClick = {
                                                drawerHandlerScope.launch {
                                                    drawerState.apply {
                                                        if (isClosed) open() else close()
                                                    }
                                                }
                                            }
                                        },
                                        navController = navController
                                    )
                                }
                                composable(Screen.ApodDetailScreen.route) {
                                    ApodDetailScreen(
                                        topBarTitle = { title = it },
                                        topBarNavigationIconClick = {
                                            navigationIconClick = it
                                        },
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

