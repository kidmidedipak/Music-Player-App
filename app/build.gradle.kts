import java.util.regex.Pattern.compile

plugins {
    id("com.android.application")
}

android {
    namespace = "com.dipak.musicplayer"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.dipak.musicplayer"
        minSdk = 21
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-process:2.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //recyclerview animators
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")

    //exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.17.0")
 //    implementation("com.google.android.exoplayer:exoplayer:2.17.1")

    //circle image
//    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

//
//    //audio visualizer
//    implementation("io.qithub.qautamchibde:audiovisualizer:2.2.5")
    implementation("io.github.gautamchibde:audiovisualizer:2.2.7")

//
//
//    //for palettes for extracting colors
//    implementation("androidx.palette.palette:1.0.0")
    implementation("androidx.palette:palette:1.0.0")

//
//    //blurImageView
//    implementation("code.github.jgabrielfreitas:BlurImageView:1.0.1")
    implementation("com.github.jgabrielfreitas:BlurImageView:1.0.1")

//    implementation("io.openharmony.tpc.thirdlib:BlurImageView:1.0.1")


    //ads dep
    implementation("com.google.android.gms:play-services-ads:22.6.0")

    //auto update show to user
    // https://mvnrepository.com/artifact/com.google.android.play/core
    implementation("com.google.android.play:core:1.10.3")




}