package edu.temple.mycountdownapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.temple.mycountdownapp.ui.theme.MyCountdownAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCountdownAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Countdown(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Countdown(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val counterStart = 100
    var counter by rememberSaveable { mutableIntStateOf(counterStart) }
    var isRunning by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = counter.toString(),
            fontSize = 98.sp
        )
        Button(
            enabled = !isRunning,
            onClick = {
                isRunning = true
                counter = counterStart
                scope.launch {
                    try {
                        while (counter > 0) {
                            delay(1_000L)
                            counter--
                        }
                    } finally {
                        isRunning = false
                    }
                }
            }
        ) {
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountdownPreview() {
    MyCountdownAppTheme {
        Countdown()
    }
}
