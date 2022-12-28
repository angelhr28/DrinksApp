# DrinksApp

Implementacion de librerias de uso convencional para la recepcion de informacion de servicios, AndroidX, Compose, Renderizado de imagenes, Integracion con Apis GoogleMaps.
Integracion con injeccion de dependencias por DaggerHilt, patron de diseño MVVM utilizacion de corrutinas con ayuda de liveData y Viewmodel.
Maquetacion con ViewBinding (proximamente Jetpack Compose en la siguiente rama)

  // Fragment
    implementation "androidx.fragment:fragment-ktx:$ktx_fragment_version"
    // Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"
    // Activity
    implementation "androidx.activity:activity-ktx:$ktx_activity_version"
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    // Retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    // Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines_version"
    // Dagger Hilt
    implementation "com.google.dagger:hilt-android:$hilt_version"
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    kapt "com.google.dagger:hilt-compiler:$hilt_version"
    //Room
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    // SharedPreferences
    implementation 'androidx.preference:preference:1.2.0'
    // Gson
    implementation 'com.google.code.gson:gson:2.9.0'
    //Picasso
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0'
    //  GoogleService
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
    implementation 'com.google.android.gms:play-services-location:21.0.1'
    implementation 'com.google.android.gms:play-services-vision:20.1.3'
