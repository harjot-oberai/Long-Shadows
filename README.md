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
  * [Examples](#examples)
    * [Example 1 (Basic)](#example-1-basic)
  	* [Example 2 (Shadow Angle)](#example-2-shadow-angle)
  	* [Example 3 (Shadow Length)](#example-3-shadow-length)
  	* [Example 4 (Shadow Blur) ](#example-4-shadow-blur)
  	* [Example 5 (Shadow Color)](#example-5-shadow-color)
  	* [Example 6 (Single View, Multiple Shadows)](#example-6-single-view-multiple-shadows)
  	* [Example 7 (Non-Transparent background)](#example-7-non-transparent-background)
  * [Custom Views and ViewGroups](#using-long-shadows-with-custom-views-and-custom-viewgroups)
  * [How does this work ?](#how-does-this-work-)
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

Shadows are filled by a linear gradient, so shadow color is built of two compontents `shadow_start_color`, which defines the start color of the gradient and `shadow_end_color`, which defines the end color of the gradient.

> **Tip** : To get a solid color shadow, just put the same value in `shadow_start_color` and `shadow_end_color` attributes.

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#90B494">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/ic_chrome"
        app:shadow_endColor="#00000000"
        app:shadow_startColor="#88000000" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_5.png">

## Example 6 (Single View, Multiple Shadows)

The library offers multiple long shadows for a single view. To use multiple shadows, just pass a comma separated string of shadow angles of you choice in the `shadow_angle` attribute.

> **e.g.** `app:shadow_angle="45, 90, 135"`. This will create 3 shadows at angle *45*, *90* and *135* degrees.

> You can also specify different lengths for each shadow in a similar way, by passing a comma separated string of shadow lengths in the `shadow_length` attribute.<br><br> **e.g.** <br> `app:shadow_angle="45, 90, 135"` <br>`app:shadow_length="200, 300, 400"`<br><br> This will create 3 shadows at angles *45*, *90* and *135*, with shadows lengths, *200*, *300* and *400* respectively.

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#D8D4F2">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/ic_circle"
        app:shadow_angle="45, 90, 135"
        app:shadow_length="200, 300, 400" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_6.png">

## Example 7 (Non-Transparent background)

So far we have seen that the library generates long shadows for all non-transparent parts in an image, but what if we want the image to have a non-transparent background, but only create shadow for what is inside the image and not the background. This is where `background_transparent` and `background_color` attributes come into play.

If your image has say a white background, with some character in the middle and we only want the shadow for the middle character and completely avoid shadow for the white background, you can set `background_transparent` as `false`, indicating the background contains some color other that 0 (transparent), and `background_color` as `#FFFFFF`, indicating that we need to treat all white pixels as background and not generate shadow for them.

> **Note** : Transparent pixels are always counted as background and never generate any shadow of their own. This is useful in cases where the background of your image's background is a circle or a rounded-rectangle with white color, where we have to count both transparent pixels and white pixel as background.

<img src="/screens/example_7_google_icon.png" align="right" width="100">

**e.g.** Lets say we want to display the google icon which has a white rectangle that acts as the background of the letter `G`.<br> Lets see how can we generate long shadow only for the letter and avoid building the shadow for the rectangular white background.

> For this example we will create a circular shape drawable `rectangular_white_background.xml` which will act as the background to icon vector `ic_google_icon_colored.xml`. You can find these resource files [here](/app/src/main/res/drawable/)

#### XML
```xml
<com.sdsmdg.harjot.longshadows.LongShadowsWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#DD6E42">

    <com.sdsmdg.harjot.longshadows.LongShadowsImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="@drawable/rectangular_white_background"
        android:padding="20dp"
        android:src="@drawable/ic_google_icon_colored"
        app:background_color="#FFFFFF"
        app:background_transparent="false" />

</com.sdsmdg.harjot.longshadows.LongShadowsWrapper>
```

#### Result

<img src="/screens/example_7.png">

> **Note** : As much as I want people to use this feature, I should warn that this won't work in several cases as android internally applies anti alias on images/bitmaps while scaling and this leads to some random colors around the boundaries. Since the algorithm checks for only one particular color, these new colors will be treated as contours and shadow will be generated, leading to unexpected results.<br><br>
So if you want to have a background, I would suggest to draw the background in a different View and overlay this View with `LongShadowsView`/`LongShadowsImageView`/`LongShadowsTextView`, containing only the part you want to generate the shadow for.<br><br>
If you are creating a custom view and want to draw a bitmap and use this feature, you may set `Paint#setFilterBitmap()` to `false`. This will disable anti aliasing for the bitmap.

# Custom Views and ViewGroups

### Custom View
If you want your own custom view to be able to generate long shadows, just extend `LongShadowsView` instead of `View` while creating the custom view.<br>That's it :)
```java
public class CustomLongShadowsView extends LongShadowsView {

    public CustomLongShadowsView(Context context) {
        super(context);
    }

    public CustomLongShadowsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLongShadowsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        * Draw whatever you want.
        */

    }
}
```

> **Note** : The custom view must be inside LongShadowsWrapper for the shadow to be generated.

### Custom View Group

`LongShadowsWrapper` and `LongShadowsFrameLayoutWrapper` are two custom view groups provided by library that extend `RelativeLayout` and `FrameLayout` respectively. These custom view groups recursively search for eligible views for long shadow generation, even inside nested view groups.<br>
So if you have a custom view group, inside which you want to place your `LongShadowsImageView` or `LongShadowsTextView`, then you can just make this view group a direct child of `LongShadowsWrapper` or `LongShadowsFrameLayoutWrapper` and the library will take care of everything else.

See the example below : 

```xml
<com.sdsmdg.harjot.longshadows.LongShadowsFrameLayoutWrapper
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.abc.YourCustomViewGroup
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!-- Place your views here -->

    </com.example.abc.YourCustomViewGroup>

</com.sdsmdg.harjot.longshadows.LongShadowsFrameLayoutWrapper>
```

# How does this work ?
Magic !

# Documentation

# Limitations

# Credits

# License
<b>Long-Shadows</b> is licensed under `MIT license`. View [license](LICENSE.md).