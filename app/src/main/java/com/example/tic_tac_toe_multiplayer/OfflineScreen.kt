package com.example.tic_tac_toe_multiplayer

import android.widget.GridLayout
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tic_tac_toe_multiplayer.models.OfflineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineScreen(viewModel:OfflineViewModel, parcel:MyParcel, navToHome:()->Unit){
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Offline Mode",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navToHome) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(
            modifier= Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Chance of ${viewModel.gameState.value}",
                modifier = Modifier.padding(8.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ) //status of game
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {
                for (i in 0..2) {
                    Row(Modifier.wrapContentSize()) {
                        for (j in 0..2) {
                            val index = i * 3 + j
                            ElevatedButton(
                                onClick = {
                                    if(viewModel.buttonStates.value[i][j]){
                                        viewModel.matrixUpdate(i,j)
                                        viewModel.gameStateUpdate()
                                    }
                                    viewModel.buttonStateUpdate(i,j)
                                    if(viewModel.has0Won()){
                                        Toast.makeText(context,"0 has won",Toast.LENGTH_LONG).show()
                                        viewModel.wins0()
                                        viewModel.resetButtons()
                                    }
                                    if(viewModel.hasXWon()){
                                        Toast.makeText(context,"X has won",Toast.LENGTH_LONG).show()
                                        viewModel.winsX()
                                        viewModel.resetButtons()
                                    }
                                },
                            ) {
                                Text(text = "${viewModel.matrix.value[i][j]}", fontSize = 60.sp)
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            Button(onClick = { viewModel.resetButtons() },modifier = Modifier.padding(8.dp)) {
                Text(text = "Reset")
            }
            Text(text = "Last Winner: ${viewModel.lastWinner.value}",modifier = Modifier.padding(8.dp)) //last winner
            Text(text = "0 Score: ${viewModel.score0.value}",modifier = Modifier.padding(8.dp)) //score x
            Text(text = "X Score: ${viewModel.scoreX.value}",modifier = Modifier.padding(8.dp)) //score 0
        }
    }
}
