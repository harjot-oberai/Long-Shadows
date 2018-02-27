<div align="center"><img src="/screens/cover.png"/></div>

# <div align="center">Long-Shadows</div>
<div align="center">A library for efficiently generating and rendering beautiful long shadows in Android.</div><br>

<div align="center">
	<a href="https://www.android.com">
    <img src="https://img.shields.io/badge/platform-Android-brightgreen.svg?style=flat-square"
      alt="Platform" />
  </a>
	<a href="https://android-arsenal.com/api?level=15">
    <img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat-square"
      alt="API" />
  </a>
	<a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-red.svg?style=flat-square"
      alt="License: MIT" />
  </a>
</div><br>

# Table of contents

  * [Features](#features)
  * [Usage](#usage)
  * [How does this work ?](#how-does-this-work-)
  * [Examples](#examples)
    * [Example 1 (Basic)](#example-1-basic)
  	* [Example 2 (Shadow Angle)](#example-2-shadow-angle)
  	* [Example 3 (Shadow Length)](#example-3-shadow-length)
  	* [Example 4 (Shadow Blur) ](#example-4-shadow-blur)
  	* [Example 5 (Shadow Color)](#example-5-shadow-color)
  	* [Example 6 (Single View, Multiple Shadows)](#example-6-single-view-multiple-shadows)
  	* [Example 7 (Non-Transparent background)](#example-7-non-transparent-background)
  * [Custom Views and ViewGroups](#using-long-shadows-with-custom-views-and-custom-viewgroups)
  * [Documentation](#documentation)
  * [Limitations](#limitations)
  * [Credits](#credits)
  * [License](#license)

# Features

- <b>Any Type of views</b> : Long-Shadows can render shadow for any type of view, even custom views.
- <b>Heavy calculation in Native</b> : Long-Shadows shifts the load of heavy calculations to native, to reduce overhead in Java. Thus the rendering is very fast and efficient.
- <b>Precise control over shadow of every View</b> : The library supports fine control over shadows of a view, so two views in same ViewGroup can have different shadow parameters.
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

# How does this work ?
Magic !

# Examples
Here are some examples to provide you a head start with using this library. The examples are selected to demonstrate almost all the basic features provided by the library. So lets start !

## Example 1 (Basic)

<b>LongShadowsImageView</b>

<img src="/screens/example_1_1.png" align="right" width="350">

```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#F5362C">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true"
        android:src="@drawable/ic_slack_1" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

<b>LongShadowsTextView</b>

<img src="/screens/example_1_2.png" align="right" width="350">

```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#40476D">

    <com.sdsmdg.harjot.longshadows.LongShadowsTextView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:text="Hello"
        android:textColor="#FFFFFF" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

## Example 2 (Shadow Angle)

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#AD5D4E">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/ic_circle"
        app:shadow_angle="90" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_2.png">

## Example 3 (Shadow Length)

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#AEC5EB">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="100dp"
        android:layout_height="150dp"
        android:src="@drawable/ic_rocket"
        app:shadow_angle="90"
        app:shadow_length="100" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_3.png">

## Example 4 (Shadow Blur) 

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#CFB1B7">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/ic_circle"
        app:shadow_blur_enabled="true"
        app:shadow_blur_radius="15" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_4.png">

## Example 5 (Shadow Color)

## Example 6 (Single View, Multiple Shadows)

## Example 7 (Non-Transparent background)

# Custom Views and ViewGroups

# Documentation

# Limitations

# Credits

# License
<b>Long-Shadows</b> is licensed under `MIT license`. View [license](LICENSE.md).