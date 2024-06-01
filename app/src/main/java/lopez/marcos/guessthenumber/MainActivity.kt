package lopez.marcos.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import lopez.marcos.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    var maxNumber = 100;
    var minNumber = 0;
    var num: Int = 0;
    var won = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);

        val guessings: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.upB)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener{
            num = Random.nextInt(minNumber, maxNumber)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE;
            guessed.visibility = View.VISIBLE;
        }

        up.setOnClickListener{
            minNumber = num;
            if(chekingLimit()){
                num = Random.nextInt(minNumber, maxNumber);
                guessings.setText(num.toString());
            } else{
                guessings.setText("Perdi");
            }
        }

        down.setOnClickListener{
            maxNumber = num;
            if(chekingLimit()){
                num = Random.nextInt(minNumber, maxNumber);
                guessings.setText(num.toString());
            } else{
                guessings.setText("Perdi");
            }
        }

        guessed.setOnClickListener{
            if(!won){
                guessings.setText("Adivine, tu numero es $num");
                guessed.setText("Play Again")
                won = true;
            } else {
                generate.visibility = View.VISIBLE;
                guessings.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE;
                resetValues();
            }
        }
    }

    fun resetValues(){
        minNumber = 0;
        maxNumber = 100;
        num = 0;
        won = false;
    }

    fun chekingLimit(): Boolean{
        return minNumber != maxNumber;
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
    GuessTheNumberTheme {
        Greeting("Android")
    }
}