package com.example.firstapplication

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
            val gColors = listOf(Color.Green, Color.Yellow, Color.Cyan)

            var showAlert by remember {
                mutableStateOf(true)
            }
            if (showAlert){
                AlertDialogExample{
                    showAlert = false
                }
            }

            Column (modifier = Modifier.padding(10.dp)) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Column (modifier = Modifier.width(200.dp)) {
                        Text(
                            text = "Hello Viewer.",
                            fontSize = 40.sp,
                            modifier = Modifier.basicMarquee(),
                            style = TextStyle(brush = Brush.linearGradient(gColors))
                        )
                    }
                    Column {
                        Row {
                            Text(text = "Jajnesh Here.", fontSize = 25.sp)
                        }
                        Box {
                            Text(text = "Box 1", fontSize = 20.sp)
                            Text(text = "Box 2", fontSize = 30.sp)
                        }
                    }
                }
                Column (modifier = Modifier.padding(top = 20.dp)) {
                    Text(text = "Blue colored Banzai!!", fontSize = 20.sp, color = Color.Blue)
                    Text(text = "Italic Banzai!!", fontSize = 20.sp, fontStyle = FontStyle.Italic)
                    Text(text = "Bold Banzai!!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Monospace Banzai!!", fontSize = 20.sp, fontFamily = FontFamily.Monospace)
                    Text(text = "Letter-spaced Banzai!!", fontSize = 20.sp, letterSpacing = 3.sp)
                    Text(text = "Underlined Banzai!!", fontSize = 20.sp, textDecoration = TextDecoration.Underline)
                    Text(text = "Width", modifier = Modifier.width(1.dp))
                    Text(text = "Back-colored Banzai!!", fontSize = 20.sp, modifier = Modifier.background(Color.Green))
                    Text(text = "Blurred Banzai", fontSize = 20.sp, modifier = Modifier.blur(1.5.dp))
                    Text(
                        text = "Bordered Banzai",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(gColors),
                                shape = ShapeDefaults.Medium
                            )
                            .padding(2.dp)
                    )
                }
                Column (modifier = Modifier.padding(top = 10.dp)) {
                    var textSam by remember {
                        mutableStateOf("Click Me")
                    }
                    Button(onClick = { textSam = "Banzai!!" }) {
                        Text(text = textSam)
                    }
                    Button(onClick = {  }, enabled = false) {
                        Text(text = "Disabled button")
                    }
                    OutlinedButton(onClick = {  }, shape = ShapeDefaults.Medium) {
                        Text(text = "Outlined Button")
                    }
                    FilledTonalButton(onClick = {  }) {
                        Text(text = "FilledTonal Button")
                    }
                    ElevatedButton(onClick = {  }, Modifier.shadow(elevation = 2.dp)) {
                        Text(text = "Elevated Button")
                    }
                    TextButton(onClick = {  }) {
                        Text("Text Button")
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlertDialogExample (
    onConfirmation: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "App isn't responding.") },
        text = { Text(text = "Just Kidding.") },
        onDismissRequest = {  },
        confirmButton = {
            Button(onClick = { onConfirmation() }) {
                Text(text = "Continue")
            }
        }
    )
}