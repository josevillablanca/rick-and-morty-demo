## Rick & Morti Demo Android

![example workflow](https://github.com/josevillablanca/rick-and-morty-demo/actions/workflows/android.yml/badge.svg)

# Compile
You can download and compile the debug project, but for usage of firebase authentication you will need to build the release apk.

Use this in your terminal:
`./gradlew clean assembleRelease`

# Description

The following project is a List-Detail app with characters from the Rick and Morty's API, that is an Open Source API.

It uses MVVM pattern for the development.

This project uses the following Android tool stack:

- Kotlin
- Coroutines
- ViewModel
- LiveData
- Hilt
- Navigation Component
- Firebase Authentication + Google Sign-In
- Material Design Components
- Dark Theme Support
- KTX core
- Truth for assertions
- Retrofit
- Moshi
- Glide