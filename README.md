# Finite Coverflow
The Viewpager2 PagerTransformer library which is helpful to create amazing carousel, image slider and cover flows. 
[Examples](app/src/main/java/com/saeed/infiniteflow/example)

<img src="https://i.imgur.com/8qiVZ6q.gif" data-canonical-src="https://i.imgur.com/RXWT6ZX.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/iRqQQ1O.gif" data-canonical-src="https://i.imgur.com/MsPmifA.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/c2IeHl2.gif" data-canonical-src="https://i.imgur.com/MBWG3az.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/jyf8jKM.gif" data-canonical-src="https://i.imgur.com/RXWT6ZX.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/ui8beYU.gif" data-canonical-src="https://i.imgur.com/MsPmifA.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/a5qa5yB.gif" data-canonical-src="https://i.imgur.com/MBWG3az.gif" width="160" height="320" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://i.imgur.com/gOWlsQH.gif" data-canonical-src="https://i.imgur.com/MBWG3az.gif" width="160" height="320" />

---

# Gradle Dependency

Firstly Add Jitpack to your repositories.

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add finite flow and viewpager2 library to your app module's `build.gradle` file:

```gradle
dependencies {
    //Cover flow
    implementation 'com.github.saeed-younus.overlay-service:overlayservice:1.13'
    //ViewPager2
    implementation 'androidx.viewpager2:viewpager2:1.0.0-beta03'
}
```
# Layout

You add FinitePagerContainer view in your layout file and inside it add ViewPager2 which holds your view or fragment adapter

```xml
    <com.saeed.infiniteflow.lib.FinitePagerContainer
        android:id="@+id/pager_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/view_pager"
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:layout_gravity="center"
            android:orientation="horizontal" />

    </com.saeed.infiniteflow.lib.FinitePagerContainer>
```
**NOTE:** viewpager2 size specify your single item layout size. If you want to fullscreen slider then check out fullscreen example.

**ORIENTATION:** You can change orientation easily in viewpager2 you just need set oreintation in xml.
### IMPLEMENTATION

In your Activity you add adapter for viewpager2 and set three types of animation for your FinitePagerContainer.
### Set up PagerContainer
```kotlin
val pagerContainer = findViewById<FinitePagerContainer>(R.id.pager_container)
// Set Your Adapter with viewPager adapter
pagerContainer.getViewPager().adapter = RecyclerPagerAdapter()
```

### Simple Slider
```kotlin
// For Apply Simple Slider Animation no overlapping no stack
pagerContainer.setSimpleSlider(
    unSelectedItemRotation = 0f,    // Rotation of Unselected Items
    unSelectedItemAlpha = 0.2f,     // Alpha Value of unselected Items
    minScale = 0.5f                 // Min Scale on unselected Items
)
```
<p align="center">
    <img src="https://i.imgur.com/8qiVZ6q.gif" data-canonical-src="https://i.imgur.com/RXWT6ZX.gif" width="160" height="320" />
</p>

---
### Overlap Slider
```kotlin
// For Apply Simple Slider Animation no overlapping no stack
pagerContainer.setOverlapSlider(
    unSelectedItemRotation = 0f,    // Rotation of Unselected Items
    unSelectedItemAlpha = 0.2f,     // Alpha Value of unselected Items
    minScale = 0.5f,                // Min Scale on unselected Items
    itemGap = 0f                    // Gap between Items
)
```
<p align="center">
<img align="center" src="https://i.imgur.com/iRqQQ1O.gif" data-canonical-src="https://i.imgur.com/RXWT6ZX.gif" width="160" height="320" />
</p>

---
### Stack Slider
```kotlin
// For Apply Stack Animation to your ViewPager
pagerContainer.setStackSlider(
    behindScale = 0.7f, // Scale Value for Behind Items
    behindAlpha = 0f    // Opacity Value for Behind Items
)
```
<p align="center">
<img src="https://i.imgur.com/c2IeHl2.gif" data-canonical-src="https://i.imgur.com/RXWT6ZX.gif" width="160" height="320" />
</p>

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/saeed-younus/finite-cover-flow/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/saeed-younus/?tab=followers) for my next creations 👍
 
License
-------

Copyright 2017 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.