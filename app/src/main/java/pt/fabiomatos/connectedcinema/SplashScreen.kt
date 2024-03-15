package pt.fabiomatos.connectedcinema

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme

class SplashScreen : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenCompose()
        }
        startMainActivity()
    }
}

private fun startMainActivity() {
    Handler().postDelayed({
        //doSomethingHere()
    }, 1000)

}

@Composable
fun SplashScreenCompose(){
    ConnectedCinemaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color =  MaterialTheme.colorScheme.background
        ) {
            Column {
                Image(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 40.dp, end = 40.dp),
                    painter = painterResource(id = R.drawable.logo_transparent),
                    contentDescription = "Logo"
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenComposePreview(){
    SplashScreenCompose()
}