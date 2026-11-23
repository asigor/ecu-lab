# ECU Lab on Android

The Android app is a native Kotlin host for the existing ECU Lab web interface.
The simulator and its physics remain in the shared `src/sim` modules; the Android
app packages the Vite production build locally in the APK, so the core experience
does not require a network connection.

## Requirements

- Android Studio with Android SDK 35
- JDK 17
- Node.js 20 or 22 and npm

## Build a debug APK

From the repository root:

```bash
npm ci
./gradlew :app:assembleDebug
```

Gradle runs `npm run build` and copies `dist/` into the Android app assets before
packaging. The APK is written to
`app/build/outputs/apk/debug/app-debug.apk`.

Open the repository in Android Studio and run the `app` configuration to install
it on a device or emulator.

## Notes

- Minimum Android version: Android 8.0 (API 26).
- SANDBOX, TUTORIAL and CAREER continue to use the same simulator as the web app.
- This is a simulator/teaching tool, not a source of calibration values for a real car.
