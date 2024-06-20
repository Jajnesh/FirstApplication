package com.example.firstapplication

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
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
            ScaffoldExample()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample() {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(text = "My top bar.", textAlign = TextAlign.Center)
                    })
            },

            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    content = {
                        Text(text = "My bottom bar")
                    }
                )
            },

            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Add, contentDescription = "FloatingButton")
                }
            },
            content = {
                Column(modifier = Modifier.padding(it)) {
                    CardExample()

                    Divider(thickness = 2.dp)

                    ChipExample()

                    Divider(thickness = 2.dp)

                    BottomSheetExample()

                    Divider(thickness = 2.dp)

                    ProgressIndicatorExample()

                    Divider(thickness = 2.dp)

                    SliderExample()

                    Divider(thickness = 2.dp)

                    SwitchExample()

                    Divider(thickness = 2.dp)

                    CheckboxExample()
                }
            }
        )
    }

    @Composable
    fun CardExample() {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Red),
            modifier = Modifier
                .size(width = 500.dp, height = 150.dp)
                .padding(10.dp)
        ) {
            Text(
                text = "Simple Card Example.",
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
            Icon(
                Icons.Sharp.CheckCircle,
                contentDescription = "CardIcon",
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ChipExample() {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Assist Chip") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "AssistChip",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )

                var selected by remember {
                    mutableStateOf(false)
                }
                FilterChip(
                    selected = selected,
                    onClick = { selected = !selected },
                    label = { Text(text = "Filter Chip") },
                    leadingIcon = {
                        if (selected) {
                            Icon(
                                Icons.Filled.Check,
                                contentDescription = "AssistChip",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        } else {
                            null
                        }
                    }
                )
            }


            ElevatedSuggestionChip(
                onClick = { },
                label = { Text(text = "Elevated Suggestion Chip") }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomSheetExample() {
        var showBottomSheet by remember {
            mutableStateOf(false)
        }
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Button(onClick = { showBottomSheet = true }) {
                Text(text = "Show Bottom Sheet")
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }) {
                    Text(text = "This is a modal bottom sheet.", modifier = Modifier.padding(20.dp))
                }
            }
        }
    }

    @Composable
    fun ProgressIndicatorExample() {
        var loading by remember {
            mutableStateOf(false)
        }
        var mText by remember {
            mutableStateOf("Start loading")
        }

        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                if (mText == "Start loading") {
                    loading = true
                    mText = "Cancel"
                } else if (mText == "Cancel") {
                    loading = false
                    mText = "Start loading"
                }
            }) {
                Text(text = mText)
            }

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(50.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }

    @Composable
    fun SliderExample() {
        var sliderPosition by remember {
            mutableFloatStateOf(0f)
        }
        
        Column (modifier = Modifier.padding(30.dp,3.dp)) {
            Slider(value = sliderPosition, onValueChange = {sliderPosition = it})
            Text(text = sliderPosition.toString())
        }
    }

    @Composable
    fun SwitchExample() {
        var checked by remember {
            mutableStateOf(true)
        }

        Column (modifier = Modifier.padding(10.dp)) {
            Switch(
                checked = checked,
                onCheckedChange = {checked = it}
            )
        }
    }

    @Composable
    fun CheckboxExample() {
        var checked by remember {
            mutableStateOf(true)
        }

        Row (verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = {checked = it})
            Text(
                if (checked) "Checked" else "Unchecked"
            )
        }
    }
}