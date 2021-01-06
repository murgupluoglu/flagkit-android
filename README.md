<div align="center">
<a href="https://jitpack.io/#murgupluoglu/flagkit-android" target="_blank">
<img src="https://jitpack.io/v/murgupluoglu/flagkit-android.svg" />
</a>
</div>

# FlagKit

Beautiful flag icons for usage in Android apps. All flags are provided as VectorDrawable. FlagKit copy from this [iOS library](https://github.com/madebybowtie/FlagKit).

# Installation

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.murgupluoglu:flagkit-android:lastVersion'
}
```

# Usage

```kotlin
val resourceId = FlagKit.getResId(context, "tr")

ImageView.setImageResource(resourceId)

val drawable = FlagKit.getDrawable(this, "tr")

ImageView.setImageDrawable(drawable)

val all = FlagKit.getAllAvailableCodes()
```

## Reference

FlagKit provides over 249 flags. A list of all flags can be [found here](Flags.md).
