package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.firstapplication.ui.theme.FirstApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val gColors = listOf(Color.Cyan, Color.LightGray, Color.Blue)
            Column (modifier = Modifier.width(200.dp)) {
                Text(
                    text = "Hello",
                    color = Color.Red,
                    fontSize = 50.sp
                )
                Text(
                    text = "Hello",
                    style = TextStyle(brush = Brush.linearGradient(gColors)),
                    fontSize = 50.sp
                )
                Text(
                    text = "Hello World",
                    modifier = Modifier.basicMarquee(),
                    fontSize = 50.sp
                )
            }
        }
    }
}