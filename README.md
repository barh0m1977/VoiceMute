# VoiceMute — Smart Prayer Silence Mode

Android experimental application that detects prayer initiation using offline speech recognition and automatically silences the device to reduce interruptions during prayer.

## ✨ Overview

VoiceMute is an experimental Android app inspired by a simple, real-life question:

"Can a smartphone recognize when someone begins praying and automatically mute the sound and interruptions?"

The project explores offline speech recognition, background audio processing on Android, and system-wide audio management.

When the app detects prayer-related phrases, such as:
- "الله أكبر"

it automatically:

- Enables Silent Mode
- Activates Do Not Disturb (DND)

## 🧠 Motivation
The goal of this project was not to build a production-ready application, but rather to explore:

- Offline AI on Android
- Real-time audio processing
- Foreground services
- Android system APIs
- Human-centered software ideas

This project serves primarily as a technical experiment and learning experience.

## ⚠️ Important Note

This project is primarily a technical experiment and proof of concept.

While the idea is interesting from an engineering perspective, continuous background audio listening is not the most practical solution for reducing interruptions during prayer because it may consume noticeable system resources, battery, and processing power.

There are simpler and more efficient alternatives, such as:
- Automatically enabling Do Not Disturb during prayer times
- Scheduled silent modes
- Context-aware automation

The purpose of this project was mainly to explore:
- Offline speech recognition
- Real-time audio processing
- Android background execution


## 🏗 Architecture

The project follows a Clean Architecture-inspired structure:
```text
com.ibrahim.voicemute
│
├── core/          # Shared utilities & helpers
├── data/          # Data sources & implementations
├── di/            # Dependency Injection
├── domain/        # Business logic & use cases
├── presentation/
│   ├── components/
│   ├── home/
│   ├── navigation/
│   └── settings/
│
├── ui/            # Theme & UI setup
├── MainActivity
└── VoiceMuteApp
```
## ⚙️ Tech Stack
### Android
- Kotlin
- Jetpack Compose
- Android Foreground Services
- Broadcast Receivers
- Notifications API
- AudioManager APIs
- Do Not Disturb APIs
### Audio / AI
- Offline Speech Recognition
- Local audio processing
- Microphone streaming
### Architecture
- Clean Architecture principles
- Layer separation
- Modular structure

🚀 Features
- Offline prayer phrase detection
- Real-time microphone listening
- Automatic Silent Mode activation
- Do Not Disturb integration
- Local audio processing
- Privacy-focused implementation
  
## 📂 Speech Recognition Model

Due to repository size limitations, the speech recognition model is not included in this repository.

You must manually download and place the model inside:
```text
app/src/main/assets/
```
Example expected structure:
```text
app/src/main/assets/vosk_model_ar_mgb2_0.4/
```
