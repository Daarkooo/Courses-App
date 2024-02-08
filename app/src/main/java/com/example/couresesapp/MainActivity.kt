package com.example.couresesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.couresesapp.data.DataSourcee
import com.example.couresesapp.model.Topic
import javax.sql.DataSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                CouresesUI()
            }
        }
    }
}


@Composable
fun CouresesUI(
    modifier: Modifier = Modifier.padding(8.dp),
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.mainBackground))
    ) {
        LazyVerticalGrid(

            columns = GridCells.Fixed(2), // the number of column is fixed and will face issues in case of tablette screen (big screen diplay large 2 columns) the line bellow is the solution for that
            //        columns = GridCells.Adaptive(128.dp), // this allows specifying width for items, and the n grid will fit as many columns as possible
            verticalArrangement =
            Arrangement.spacedBy(12.dp),
            horizontalArrangement =
            Arrangement.spacedBy(12.dp), // Spacing between the columns in our case 16 for only 2 columns
            modifier = modifier
        ) {
            items(DataSourcee.topics) { topic ->
                TopicItem(topic)
            }
        }
    }
}

@Composable
fun TopicItem(topic: Topic){
    Surface(
        color = colorResource(R.color.background),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(68.dp)
    ) {
        Row{
            Image(painter = painterResource(id = topic.imageResourceId),
                contentDescription = null,
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = stringResource(topic.stringResourceId),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                    )
                    Text(text = "${topic.availableCourses}")
                }
            }
        }
    }
}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun CuursesPreview() {
    CouresesUI()
}