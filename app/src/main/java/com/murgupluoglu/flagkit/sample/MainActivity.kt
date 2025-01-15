package com.murgupluoglu.flagkit.sample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.murgupluoglu.flagkit.FlagKit
import com.murgupluoglu.flagkit.sample.ui.theme.FlagKitTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        printInfo()

        val flags = arrayListOf<FlagModel>()
        FlagKit.getAllAvailableCodes().forEach {
            flags.add(FlagModel(it, FlagKit.getResId(this, it)))
        }

        setContent {
            FlagKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(Modifier.padding(innerPadding)) {
                        item {
                            Text(
                                modifier = Modifier.padding(start = 24.dp),
                                text = "Total flags: ${flags.size}"
                            )
                        }
                        items(flags.size) { index ->
                            val item = flags[index]
                            ItemView(item)
                        }
                    }
                }
            }
        }
    }
}

private fun Context.printInfo() {
    val infos = arrayListOf<FlagModel>()
    for (locale in Locale.getISOCountries()) {
        infos.add(FlagModel(locale, FlagKit.getResId(this, locale)))
    }
    FlagKit.getAllAvailableCodes().forEach {
        if (Locale.getISOCountries().contains(it.uppercase()).not()) {
            Log.i("INFO", "Not exist $it")
        }
    }
    val notFound = infos.filter { it.resourceId == 0 }
    Log.i(
        "INFO",
        "Total Flags: ${infos.size}\n Not Found: ${notFound.size} - ${notFound.map { it.name }}"
    )
}

@Composable
private fun ItemView(
    item: FlagModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp
            )
    ) {
        Row(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(50.dp),
                painter = painterResource(id = item.resourceId),
                contentDescription = item.name
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = item.name)
        }
    }
}

@Preview
@Composable
private fun ItemViewPreview() {
    ItemView(
        FlagModel("tr", com.murgupluoglu.flagkit.R.drawable.tr)
    )
}