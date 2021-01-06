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
```

## Usage (iOS, macOS, tvOS)
FlagKit provides both rectangular unstyled flags and styled flags in a variety of shapes. Our [sample project](Sources/Swift/FlagKitDemo-iOS) demonstrates how to display flags and customize them into different shapes (rounded corners, square, circle).

> **Note:** Styling is currently not supported by FlagKit on macOS

This brief example loads the flag for the users current locale, and retrieves the unstyled flag and a styled flag:

```swift
let countryCode = Locale.current.regionCode!
let flag = Flag(countryCode: countryCode)!

// Retrieve the unstyled image for customized use
let originalImage = flag.originalImage

// Or retrieve a styled flag
let styledImage = flag.image(style: .circle)
```

You can always access the underlying assets directly, through the bundled Asset Catalog:

```swift
let countryCode = Locale.current.regionCode!
let bundle = FlagKit.assetBundle
let originalImage = UIImage(named: countryCode, in: bundle, compatibleWith: nil)
```

## Reference

FlagKit provides over 249 flags. A list of all flags can be [found here](Flags.md).
