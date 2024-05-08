package pt.fabiomatos.connectedcinema.ui.views

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import pt.fabiomatos.connectedcinema.ui.navigation.Welcome
import pt.fabiomatos.connectedcinema.ui.navigation.WelcomeItem
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.utils.Utils

@Composable
fun WelcomeScreen(navController: NavHostController)
{
    SliderScreen(navController)
}

@Composable
fun StepInicial(navController: NavHostController){
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
fun SliderScreen(navController: NavHostController = rememberNavController()) {
    val images = Utils.welcomeImages
    ConnectedCinemaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ImageSlider(images = images, navController = navController)
        }
    }
}

@Composable
fun ImageItem(imageRes: WelcomeItem){
    Column {
        Image(
            painter = painterResource(id = imageRes.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .size(350.dp)
        )
        Text(
            fontSize = 22.sp,
            text = imageRes.description,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<WelcomeItem>, navController: NavHostController) {

    val pageCount = images.size
    val pagerState = rememberPagerState(pageCount = { pageCount })

    var isBackVisible by remember { mutableStateOf(false) }
    var isForwardVisible by remember { mutableStateOf(true) }
    var isProceedVisible by remember { mutableStateOf(false) }

    // Callback to execute when page changes
    LaunchedEffect(pagerState.currentPage) {
       when (pagerState.currentPage) {
           0 -> {
               isBackVisible = false
               isForwardVisible = true
               isProceedVisible = false
           }
           (pageCount - 1) -> {
               isBackVisible = true
               isForwardVisible = false
               isProceedVisible = true
           }
           else -> {
               isBackVisible = true
               isForwardVisible = true
               isProceedVisible = false
           }
       }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        HorizontalPager(
            state = pagerState
        ) { page ->
            ImageItem(imageRes = images[page])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Red
                    else MaterialTheme.colorScheme.primary
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
                .fillMaxWidth()
                .padding(bottom = 70.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // scroll to page
            val coroutineScope = rememberCoroutineScope()

            if (isBackVisible) {

                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(MaterialTheme.colorScheme.primary)
                        .alpha(1f),
                    onClick = {
                        coroutineScope.launch {
                            // Call scroll to on pagerState
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                            isForwardVisible = true
                            if (pagerState.currentPage == 0)
                                isBackVisible = false
                            if (pagerState.currentPage != pageCount - 1) {
                                isProceedVisible = false
                            }
                        }
                    }) {
                    Icon(
                        Icons.Filled.ArrowBackIosNew,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            if (isProceedVisible) {
                Spacer(modifier = Modifier.size(20.dp))
                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth(),
                    onClick = {
                        coroutineScope.launch {
                            navController.navigate(Login.route)
                        }
                    }) {
                    Text(
                        fontSize = 20.sp,
                        text = stringResource(id = R.string.i_understand),
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (isForwardVisible) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = AbsoluteAlignment.Right,
                )
                {
                    IconButton(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary),
                        onClick = {
                            coroutineScope.launch {
                                // Call scroll to on pagerState
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                                isBackVisible = true
                                if (pagerState.currentPage == pageCount - 1) {
                                    isForwardVisible = false
                                    isProceedVisible = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SliderScreenPreview(){
    SliderScreen()
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SwipeActionExample() {
    val pageCount = 4
    val pagerState = rememberPagerState(pageCount = { pageCount })

    // Callback to execute when page changes
    LaunchedEffect(pagerState.currentPage) {
        // Do something when the page changes
        val currentPage = pagerState.currentPage
        // You can use currentPage to determine which page you are currently on
        // For example, if currentPage == 0, you are on the first page
        // If currentPage == 1, you are on the second page, and so on
        // You can perform actions based on the current page
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content of each page
            Text(
                text = "Page $page",
                fontSize = 24.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSwipeActionExample() {
    SwipeActionExample()
}