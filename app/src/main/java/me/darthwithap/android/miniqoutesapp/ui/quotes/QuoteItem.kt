package me.darthwithap.android.miniqoutesapp.ui.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.darthwithap.android.miniqoutesapp.R
import me.darthwithap.android.miniqoutesapp.domain.models.Quote

@Composable
fun QuoteItem(
  quote: Quote,
  onPreviousClick: () -> Unit,
  onNextClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(40.dp, 150.dp)
  ) {
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .fillMaxSize(0.8f)
        .align(Alignment.Center)
        .background(Color.Gray)
        .padding(8.dp),
      contentAlignment = Alignment.Center
    ) {
      Column {
        Icon(
          modifier = Modifier,
          painter = painterResource(id = R.drawable.ic_quotes),
          contentDescription = null
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = quote.content,
          fontSize = 16.sp,
          fontWeight = FontWeight.Medium,
          modifier = Modifier.padding(4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          modifier = Modifier.align(Alignment.Start),
          text = quote.author,
          fontSize = 14.sp,
          fontWeight = FontWeight.SemiBold,
          textAlign = TextAlign.Start
        )
        Icon(
          imageVector = Icons.Default.Share,
          contentDescription = null,
          modifier = Modifier
            .align(Alignment.End)
            .padding(8.dp)
        )
      }
    }
    Icon(
      imageVector = Icons.Default.ArrowBack,
      contentDescription = null,
      modifier = Modifier
        .padding(start = 16.dp, bottom = 16.dp)
        .clip(RoundedCornerShape(0.5f))
        .background(Color.White)
        .align(
          Alignment.BottomStart
        )
        .clickable {
          onPreviousClick()
        }
    )
    Icon(
      imageVector = Icons.Default.ArrowForward,
      contentDescription = null,
      modifier = Modifier
        .padding(bottom = 16.dp, end = 16.dp)
        .clip(RoundedCornerShape(0.5f))
        .background(Color.White)
        .align(
          Alignment.BottomEnd
        )
        .clickable {
          onNextClick()
        }
    )
  }
}