package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myproject.ui.theme.MyProjectTheme

class MainActivity : ComponentActivity() {

    lateinit var questionTextView: TextView
    lateinit var answer1Button: Button
    lateinit var answer2Button: Button
    lateinit var answer3Button: Button
    lateinit var answer4Button: Button
    lateinit var scoreTextView: TextView
    lateinit var questions: List<Question>
    var currentQuestionIndex = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProjectTheme {
        Greeting("Edson Muandula")
    }
}