package pt.fabiomatos.connectedcinema.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.data.DataSource
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme

@Composable
fun LoginScreen() {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    ConnectedCinemaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .size(105.dp),
                    alignment = Alignment.TopCenter,
                    painter = painterResource(id = R.drawable.logo_slogan_transparent),
                    contentDescription = "Logo"
                )

                Column (
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = email,
                        onValueChange = { email = it },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Email,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Email",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.email_description_label)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.email_label)
                            )
                        },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Lock,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = stringResource(id = R.string.email_label),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.password_description_label)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.password_label)
                            )
                        },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                        visualTransformation =
                        if (passwordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        keyboardOptions =
                        KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            // Please provide localized description for
                            // accessibility services
                            val description =
                                if (passwordVisible)
                                    stringResource(id = R.string.hide_password)
                                else
                                    stringResource(id = R.string.show_password)

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, description)
                            }
                        }
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = stringResource(id = R.string.forget_password),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.size(40.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(25),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                    {
                        Text(
                            text = "Login",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(0.25f)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            thickness = 2.dp
                        )
                        Text(
                            text = "Or"
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(0.35f)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            thickness = 2.dp
                        )
                    }

                    Spacer(modifier = Modifier.size(20.dp))

                    Row (
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    {
                        DataSource.loginOptions.forEach { item ->
                            IconOptionsLogin(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.size(50.dp),
                                resourceId = item.second,
                                contentDescription = item.first
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IconOptionsLogin(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    resourceId: Int,
    contentDescription : String
) {
    IconButton(
        onClick = { onClick() },
    )
    {
        Icon(
            painter = painterResource(id = resourceId),
            modifier = modifier,
            contentDescription = contentDescription,
            tint = Color.Unspecified
        )
    }


}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}