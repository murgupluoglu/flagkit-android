package com.murgupluoglu.flagkit.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.murgupluoglu.flagkit.FlagKit
import com.murgupluoglu.flagkit.sample.databinding.ItemFlagBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val flags = arrayListOf<FlagModel>()
        FlagKit.getAllAvailableCodes().forEach {
            //flags.add(FlagModel(it, FlagKit.getResId(this, it)))
        }

        val drawable = FlagKit.getDrawable(this, "not_exist")
        val resId = FlagKit.getResId(this, "not_exist")

        for (locale in Locale.getISOCountries()) {
            flags.add(FlagModel(locale, FlagKit.getResId(this, locale)))
        }

        Log.i("flags", flags.toString())
        Log.i("flags", flags.size.toString())


        LastAdapter(flags, BR.item)
                .map<FlagModel>(
                        Type<ItemFlagBinding>(R.layout.item_flag)
                                .onBind {
                                    val item = it.binding.item!!
                                    it.binding.apply {
                                        flagImageView.setImageResource(item.resourceId)
                                        nameTextView.text = item.name
                                    }
                                }

                )
                .into(findViewById(R.id.recyclerView))
    }
}