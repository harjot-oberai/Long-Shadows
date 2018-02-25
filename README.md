<div align="center"><img src="/screens/cover.png"/></div>

# Long-Shadows
[![Platform](https://img.shields.io/badge/platform-Android-brightgreen.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License: MIT](https://img.shields.io/badge/License-MIT-red.svg)](https://opensource.org/licenses/MIT)

A library for rendering long shadows for android views beautifully and efficeiently. The library provides the following features : 

- <b>Any Type of views</b> : Long-Shadows can render shadow for any type of view, even custom views.
- <b>Heavy calculation in Native</b> : Long-Shadows shifts the load of heavy calculations to native, to reduce overhead in Java. Thus the rendering is very fast and efficient.
- <b>Multiple Shadows for a View</b> : The library support rendering multiple long shadows for a single view, simulating multiple light sources.
- <b>Async Calculations</b> : The library allows the operations to be asynchronous to avoid blocking the UI thread for long calculations.
- <b>Shadow animations</b> : The library supports fade out animation while rendering the shadow.

# Usage
Just add the following dependency in your app's `build.gradle`
```groovy
dependencies {
      compile 'com.sdsmdg.harjot:longshadows:0.2'
}
```

# License
<b>Long-Shadows</b> is licensed under `MIT license`. View [license](LICENSE.md).