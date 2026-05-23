# Koin
-keep class org.koin.** { *; }

# Vosk Offline Recognition
-keep class org.vosk.** { *; }
-keep class com.sun.jna.** { *; }
-keepnames class com.sun.jna.** { *; }

# DataStore & Serialization
-keep class com.ibrahim.voicemute.data.local.UserPreferences { *; }

# Lifecycle & Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}
