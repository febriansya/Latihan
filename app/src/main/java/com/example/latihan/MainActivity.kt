package com.example.latihan

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.latihan.ui.theme.LatihanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatihanTheme {
                // A surface container using the 'background' color from the theme

                MyApp()
            }
        }
    }


    @Composable
    fun MyApp(angka: List<String> = List(100) { "$it" }) {

        var buttonContinued by rememberSaveable { mutableStateOf(true) }

        val item = mutableListOf<Person>(Person(1, "yan febriansyah"), Person(2, "Pahmi Jaelani"))

        if (buttonContinued) {
            OnBoardingScreen {
                buttonContinued = false
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 2.dp)
            ) {
                items(angka.size) { tep ->
                    ItemHello(
                        tep
                    )
                }
            }
        }
    }
}


@Composable
fun ItemHello(
    nomor: Int
) {
    var expand by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expand) 48.dp else 0.dp, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .fillMaxWidth(1f)

    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "hello")
                Text(
                    text = nomor.toString(), style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expand) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }

            }
            IconButton(
                onClick = { expand = !expand }) {
                Icon(
                    imageVector = if (expand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expand) stringResource(id = R.string.show_less) else stringResource(
                        id = R.string.show_more
                    )
                )
            }
        }
    }
}


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to the basic codelab!")
        ElevatedButton(onClick = onButtonClicked) {
            Text(text = "Continue")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "dark"
)
@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    LatihanTheme {
        OnBoardingScreen {

        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "dark"
)
@Preview(showBackground = true)
@Composable
fun ListPreview() {
    LatihanTheme {
        ItemHello(nomor = 1)
    }
}