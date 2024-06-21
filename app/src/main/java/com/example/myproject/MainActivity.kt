package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myproject.ui.theme.MyProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            fontSize = 36.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProjectTheme {
        HappyBirthdayCard("Edson", "Eva")
    }
}