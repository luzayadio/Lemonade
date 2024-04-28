package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp(
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var qtdToDo by remember { mutableStateOf(0) }
    var qtdDid by remember { mutableStateOf(0) }

    val imageResource = when(currentStep) {
        1-> R.drawable.lemon_tree
        2-> R.drawable.lemon_squeeze
        3-> R.drawable.lemon_drink
        4-> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val imageStringRes = when(currentStep) {
        1-> R.string.lemon_tree
        2-> R.string.lemon
        3-> R.string.glass_of_lemonade
        4-> R.string.empty_glass
        else -> R.string.lemon_tree
    }

    val textStringRes = when(currentStep) {
        1-> R.string.tap_the_lemon_tree_to_select_a_lemon
        2-> R.string.keep_tapping_the_lemon_to_squeeze_it
        3-> R.string.tap_the_lemonade_to_drink_it
        4-> R.string.tap_the_empty_glass_to_start_again
        else -> R.string.tap_the_lemon_tree_to_select_a_lemon
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            shape = RoundedCornerShape(20),
            border = BorderStroke(2.dp, Color(105, 205, 216)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2)),
            onClick = {
                if(qtdToDo != 0) {
                    if(qtdDid != qtdToDo) {
                        qtdDid += 1
                    } else {
                        qtdDid = 0
                        qtdToDo = 0
                        currentStep += 1
                    }
                } else {
                    if (currentStep < 4) {
                        if (currentStep == 2) {
                            qtdToDo = (2..4).random()
                        } else currentStep += + 1
                    } else {
                        currentStep = 1
                    }
                }
            },
            ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(imageStringRes)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(textStringRes),
            fontSize = 18.sp,
        )
        //Text(text = "$qtdToDo")
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}