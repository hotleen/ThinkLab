package com.app.thinkerlab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thinkerlab.ui.activity.DualRecordActivity
import com.app.thinkerlab.ui.activity.EBikeOverviewActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(SampleData.conversationSample)
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        val context = LocalContext.current
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.ebike),
                contentDescription = "actor profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            /**
             * val state = remember { mutableStateOf(false) }
             * var isExpanded
             *     get() = state.value
             *     set(value) { state.value = value }
             *
             */
            var isExpanded by remember { mutableStateOf(false) }
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    msg.author,
                    modifier = Modifier.clickable {
                        Log.i("testHzy", "click text!")
                        val intent = Intent(context, DualRecordActivity::class.java)
                        context.startActivity(intent)
                    },
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                    Text(
                        text = msg.body,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .clickable {
                                val intent = Intent(context, EBikeOverviewActivity::class.java)
                                context.startActivity(intent)
                            },
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        Conversation(SampleData.conversationSample)
    }

    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "2021 0521",
                "101"
            ),
            Message(
                "2021 0621",
                "151"
            ),
            Message(
                "2021 0721",
                "201"
            ),
            Message(
                "2021 0821",
                "251"
            ),

            )
    }
}