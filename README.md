# Melting Spot Pizza - Android app

Offline-first Android WebView wrapper for the attached one-file website. The website is bundled in `app/src/main/assets/index.html`. JavaScript and DOM storage are enabled. Images or APIs referenced over the internet may still require internet access.

## Free APK build: GitHub Actions
1. Create a new GitHub repository.
2. Upload the **contents** of this project folder to the repository root, including the `.github` hidden folder (GitHub upload may omit hidden folders; check this).
3. Under Actions > Build Android APK > Run workflow (or push to `main`), start the build.
4. Open the completed workflow run and download artifact `MeltingSpotPizza-debug-apk`.
5. Unzip it, send `app-debug.apk` to an Android phone, and install it. Android may ask to allow installation from this source.

For local builds, install Android Studio, open the folder, and use Build > Build APK(s).

**Note:** Not a compiled APK. Requires Android SDK/Gradle to build, and has not been tested on an actual handset. Debug APKs are for testing, not Google Play. iPhone cannot install an APK (requires an iOS app or hosted site).
