# Shaadi.com-Assignment

A clean architecture Android application demonstrating modern Android development practices using MVVM, Jetpack components, and background sync with WorkManager.

---

## 🧠 Introduction

This project is a sample matchmaking app built using **Clean Architecture** and the **MVVM** pattern. It fetches user profiles from [randomuser.me](https://randomuser.me/) and allows users to "like" or "dislike" them. User match actions are reliably synced with the server, even when the device is offline.

---

## 🧩 Features

- 🧑‍🤝‍🧑 **Displaying Matches**  
  Paginated list of users fetched from the API with user details, including name, profile image, and location.

- 💖 **Like / Dislike Functionality**  
  Users can perform match actions ("like" or "dislike") which are stored locally and synced based on connectivity.

- 🌐 **Offline-first Architecture**  
  - If the internet is **not available**, match actions are queued using **WorkManager** and synced automatically later.
  - If internet **is available**, actions are synced **instantly** to the server.

- 🔄 **Background Syncing**  
  Sync operations are handled with **WorkManager**

- 🎞 **Lottie Loader**  
  Displays a Lottie-based animation while the user data is loading.

- ❌ **Edge Case Handling**  
  Graceful handling of no internet connection, API errors, and empty states with user-friendly UI feedback.

---

## 🔧 Tech Stack

- **Kotlin** – Primary programming language
- **MVVM** – Modern Android architecture pattern
- **Clean Architecture** – Layered separation of concerns
- **Retrofit** – Networking library
- **Room** – Local offline database
- **WorkManager** – Reliable background job scheduling
- **Hilt** – Dependency injection
- **Paging 3** – Efficient, lazy list loading
- **Glide** – Image loading and caching
- **Lottie** – For smooth animated loaders
- **Kotlin Flow & Coroutines** – For reactive async programming
- **ViewBinding** – Type-safe and efficient view binding

---

## 📁 Project Structure

```text
Shaadi.com-Assignment/
│
├── core/                            # Common utilities, DI setup, wrappers
│   ├── di/                          # Hilt modules
│   ├── utils/                       # Extensions, safeApiCall, etc.
│   └── wrapper/                     # Resource<T> sealed wrapper
│
├── data/                            # Data Layer
│   ├── local/                       # Room DB, DAOs, entities
│   ├── remote/                      # Retrofit API, DTOs
│   ├── pagging/                     # PagingSource for user data
│   └── repository/                  # Concrete repository implementations
│
├── domain/                          # Domain Layer
│   ├── model/                       # Business models
│   ├── repository/                  # Repository interfaces
│   └── usecase/                     # UseCases to handle business logic
│
├── presentation/                    # UI Layer
│   ├── usermainscreen/             # Adapter, view holder, and UI logic
│   └── utils/                       # UI extensions (gone/show), drawables
│
├── worker/                          # WorkManager worker for syncing
│
├── App.kt                           # Application class with @HiltAndroidApp
└── AndroidManifest.xml
