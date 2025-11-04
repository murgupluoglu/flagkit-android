package com.murgupluoglu.flagkit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.murgupluoglu.flagkit.FlagModel
import com.murgupluoglu.flagkit.sample.ui.theme.FlagKitTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val flags = arrayListOf<FlagUIModel>()
        FlagKit.getAllAvailableCodes().forEach {
            flags.add(
                FlagUIModel(
                    alpha2Code = it.alpha2Code,
                    resourceId = it.resourceId,
                    countryName = Locale.forLanguageTag("${it.alpha2Code}-${it.alpha2Code}").displayCountry
                )
            )
        }
        val notFound = arrayListOf<FlagModel>()
        for (locale in Locale.getISOCountries()) {
            val resourceId = FlagKit.getResId(locale)
            if (resourceId == com.murgupluoglu.flagkit.R.drawable.unknown_flag) {
                notFound.add(FlagModel(locale, resourceId))
            }
        }

        setContent {
            FlagKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(Modifier.padding(innerPadding)) {
                        item {
                            Column {
                                Text(
                                    modifier = Modifier.padding(start = 24.dp),
                                    text = "Total flags: ${flags.size}"
                                )
                                Text(
                                    modifier = Modifier.padding(start = 24.dp),
                                    text = "Not found flags: ${notFound.map { it.alpha2Code }}"
                                )
                            }
                        }
                        item {
                            ItemView(
                                FlagUIModel(
                                    "xx",
                                    com.murgupluoglu.flagkit.R.drawable.unknown_flag,
                                    "Unknown"
                                )
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

@Composable
private fun ItemView(
    item: FlagUIModel
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
                contentDescription = item.alpha2Code
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = item.alpha2Code)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = item.countryName)
        }
    }
}

@Preview
@Composable
private fun ItemViewPreview() {
    ItemView(
        FlagUIModel(
            alpha2Code = "tr",
            resourceId = com.murgupluoglu.flagkit.R.drawable.tr_flag,
            countryName = "Türkiye"

        )
    )
}