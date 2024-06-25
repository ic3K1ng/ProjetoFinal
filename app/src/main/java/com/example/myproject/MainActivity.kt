package com.example.myproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myproject.ui.theme.MyProjectTheme
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import java.time.LocalDate

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun HappyBirthdayCard(birthdayName: String, from: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F,
            modifier = Modifier.fillMaxSize()
        )
        HappyBirthdayText(
            birthdayName = birthdayName,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun HappyBirthdayText(birthdayName: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.happy_birthday, birthdayName),
            fontSize = dimensionResource(id = R.dimen.happy_birthday_fontsize).value.sp,
            lineHeight = dimensionResource(id = R.dimen.happy_birthday_lineheight).value.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.from, from),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var hasBirthday: Boolean = false
    var name by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            stringResource(id = R.string.digite_teu_nome),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = modifier.height(25.dp))
        TextField(
            value = name,
            label = { Text(stringResource(id = R.string.nome)) },
            onValueChange = { name = it},
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        TextField(
            value = data,
            label = { Text(stringResource(id = R.string.data)) },
            onValueChange = { data = it},
            placeholder = {Text("aaaa-mm-dd")},
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        //HappyBirthdayCard("Edson", "Eva")
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProjectTheme {
        MyApp()
    }
}