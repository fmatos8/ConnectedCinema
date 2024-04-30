package pt.fabiomatos.connectedcinema.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.ui.navigation.Login
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme

@Composable
fun WelcomeScreen(navController: NavHostController = rememberNavController())
{
    StepInicial()
}

@Composable
fun StepInicial(){
    ConnectedCinemaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    Icons.Filled.VisibilityOff,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(id = R.string.email_label),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp))
                Text(
                    fontSize = 20.sp,
                    text = stringResource(id = R.string.welcome_text),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(60.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    onClick = {

                    },
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary
                    )
                )
                {
                    Text(
                        text = stringResource(id = R.string.i_understand),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun SliderScreen() {
    val images = listOf(
        R.drawable.welcome1,
        R.drawable.welcome2,
        R.drawable.welcome3
    )
    ConnectedCinemaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ImageSlider(images = images)
        }
    }
}

@Composable
fun ImageItem(imageRes: Int){
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .size(350.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<Int>){

    val pageCount = images.size
    val pagerState = rememberPagerState(pageCount = {pageCount})

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        HorizontalPager(
            state = pagerState
        ) { page ->
            ImageItem(imageRes = images[page])
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Red else MaterialTheme.colorScheme.primary
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                ) {

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // scroll to page
            val coroutineScope = rememberCoroutineScope()
            IconButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary),
                onClick = {
                    coroutineScope.launch {
                        // Call scroll to on pagerState
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                Icon(
                    Icons.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}




@Preview
@Composable
fun SliderScreenPreview(){
    SliderScreen()
}

