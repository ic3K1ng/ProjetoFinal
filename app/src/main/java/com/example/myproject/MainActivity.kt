package com.example.myproject

import android.annotation.SuppressLint
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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Date

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
            painter = painterResource(id = R.drawable.g_day),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F,
            modifier = Modifier.fillMaxSize()
        )
        HappyBirthdayText(
            birthdayName = birthdayName,
            year = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun HappyBirthdayText(birthdayName: String, year: String, modifier: Modifier = Modifier) {
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
            text = stringResource(R.string.year, year),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@SuppressLint("NewApi")
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var hasBirthday by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var years by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    if(!hasBirthday)
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                modifier = modifier.padding(35.dp)
            )
            Text(
                stringResource(id = R.string.digite_teu_nome),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = modifier.height(10.dp))
            TextField(
                value = name,
                label = { Text(stringResource(id = R.string.nome)) },
                onValueChange = { name = it},
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            TextField(
                value = data,
                label = { Text(stringResource(id = R.string.data)) },
                onValueChange = { data = it},
                placeholder = {Text("aaaa-mm-dd")},
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Text(message)
            Button(onClick = {
                if(data != "" && name != "") {
                    val today = LocalDate.now()
                    val birth = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    val period = today.minusYears(birth.year.toLong())
                    //val birthdayThisYear = LocalDate.of(today.year, birth.monthValue, birth.dayOfMonth)

                    if ((today.dayOfMonth == birth.dayOfMonth) && (today.monthValue == birth.monthValue)) {
                        hasBirthday = true
                        years = "${period.year} anos!"
                    } else {
                        val (monthsRemaining, daysRemaining) = monthsAndDaysUntilBirthday(birth.monthValue, birth.dayOfMonth)
                        message = "Faltam $monthsRemaining meses e $daysRemaining dias para o seu aniversário!"
                    }
                } else {
                    message = "Os 2 campos acima são obrigatórios!"
                }
            }) {
                Text(stringResource(id = R.string.avancar))
            }
        }
    else
        HappyBirthdayCard(name, years)
}

@RequiresApi(Build.VERSION_CODES.O)
fun monthsAndDaysUntilBirthday(birthdayMonth: Int, birthdayDay:Int): Pair<Int, Int> {
    val today = LocalDate.now()
    val nextYear = today.plusYears(1)
    val monthsUntilBirthday = if ((birthdayMonth < nextYear.monthValue) || (birthdayMonth == nextYear.monthValue )) {
        12 - (nextYear.monthValue - birthdayMonth)
    } else {
        if (birthdayDay >= nextYear.dayOfMonth){
            birthdayMonth - nextYear.monthValue
        }else{
            0
        }
    }
    val daysUntilBirthday = if (birthdayDay < nextYear.dayOfMonth) {
        LocalDate.of(nextYear.year, nextYear.month, nextYear.dayOfMonth).lengthOfMonth() - (nextYear.dayOfMonth - birthdayDay)
    } else {
        birthdayDay - nextYear.dayOfMonth
    }
    return Pair(monthsUntilBirthday, daysUntilBirthday)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProjectTheme {
        //MyApp()
        HappyBirthdayCard("Emân", "18")
    }
}