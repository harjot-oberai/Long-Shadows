<div align="center"><img src="/screens/cover.png"/></div>

# <div align="center">Long-Shadows</div>
<div align="center">A library for efficiently generating and rendering beautifult long shadows in Android.</div><br>

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

## Table of contents

  * [Features](#features)
  * [Usage](#usage)
  * [How does this work ?](#how-does-this-work-)
  * [Examples](#examples)
  	* [Example 1 (Basic)](#example-1-basic)
  	* [Example 2 (Shadow Length)](#example-2-shadow-length)
  	* [Example 3 (Shadow Blur) ](#example-3-shadow-blur)
  	* [Example 4 (Shadow Color)](#example-4-shadow-color)
  	* [Example 5 (Multiple Views, Multiple Angles)](#example-5-multiple-views-multiple-angles)
  	* [Example 6 (Single View, Multiple Shadows)](#example-6-single-view-multiple-shadows)
  	* [Example 7 (Non-Transparent background)](#example-7-non-transparent-background)
  * [Custom Views and ViewGroups](#using-long-shadows-with-custom-views-and-custom-viewgroups)
  * [Documentation](#documentation)
  * [Limitations](#limitations)
  * [Credits](#credits)
  * [License](#license)

## Features

- <b>Any Type of views</b> : Long-Shadows can render shadow for any type of view, even custom views.
- <b>Heavy calculation in Native</b> : Long-Shadows shifts the load of heavy calculations to native, to reduce overhead in Java. Thus the rendering is very fast and efficient.
- <b>Multiple Shadows for a View</b> : The library support rendering multiple long shadows for a single view, simulating multiple light sources.
- <b>Async Calculations</b> : The library allows the operations to be asynchronous to avoid blocking the UI thread for long calculations.
- <b>Shadow animations</b> : The library supports fade out animation while rendering the shadow.

## Usage
Just add the following dependency in your app's `build.gradle`
```groovy
dependencies {
      compile 'com.sdsmdg.harjot:longshadows:0.2'
}
```

## How does this work ?
Magic !

## Examples

#### Example 1 (Basic)

#### Example 2 (Shadow Length)

#### Example 3 (Shadow Blur) 

#### Example 4 (Shadow Color)

#### Example 5 (Multiple Views, Multiple Angles)

#### Example 6 (Single View, Multiple Shadows)

#### Example 7 (Non-Transparent background)

## Using Long-Shadows with custom Views and custom ViewGroups

## Documentation

## Limitations

## Credits

## License
<b>Long-Shadows</b> is licensed under `MIT license`. View [license](LICENSE.md).