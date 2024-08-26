# NoteChad: A Comprehensive Note-Taking App

## Overview

NoteChad is a feature-rich note-taking app designed to provide a seamless experience for creating, editing, and managing notes. The application leverages various modern Android development technologies to ensure a scalable, maintainable, and efficient codebase.

## DEMO

[Watch Demo](https://drive.google.com/file/d/1tPjRwEiR0lg7IMUhsFxkk5llIuviPWRZ/view?usp=sharing)

## Technologies Used

### 1. **Kotlin**
   - ![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?logo=kotlin&logoColor=white&style=flat-square)
   - **Purpose:** The main programming language used for developing the entire Android application.

### 2. **Room**
   - ![Room](https://img.shields.io/badge/Room-FF4081?logo=google&logoColor=white&style=flat-square)
   - **Purpose:** Provides an abstraction layer over SQLite to handle database operations efficiently.

### 3. **Hilt**
   - ![Hilt](https://img.shields.io/badge/Hilt-D14836?logo=android&logoColor=white&style=flat-square)
   - **Purpose:** Dependency injection framework to manage dependencies and improve code modularity.

### 4. **Firebase Authentication**
   - ![Firebase Authentication](https://img.shields.io/badge/Firebase%20Authentication-FFCA28?logo=firebase&logoColor=white&style=flat-square)
   - **Purpose:** Handles secure user authentication.

### 5. **RecyclerView**
   - ![RecyclerView](https://img.shields.io/badge/RecyclerView-8BC34A?logo=android&logoColor=white&style=flat-square)
   - **Purpose:** Displays a list of notes in a staggered grid layout using an adapter.

### 6. **Coroutines**
   - ![Coroutines](https://img.shields.io/badge/Coroutines-FF8700?logo=kotlin&logoColor=white&style=flat-square)
   - **Purpose:** Manages asynchronous tasks without blocking the main thread.

## Features

- **User Authentication:** Implemented Firebase Authentication to allow users to sign in securely.
- **Note Management:** Users can create, edit, and delete notes effortlessly.
- **Comprehensive UI:** Utilized fragments to design a user-friendly interface with seamless navigation between different screens.
- **Persistent Data Storage:** Transitioned from SQLite to Room for efficient storage and retrieval of notes.
- **Advanced Functionality:** Integrated features like Staggered Grid Layout Manager and tracking time of creation.

## Project Structure

- **`MainActivity.kt`:** Hosts the main UI components and manages navigation.
- **`SignInFragment.kt`:** Handles user authentication.
- **`NotesRVFragment.kt`:** Displays the list of notes using a RecyclerView in a staggered grid layout.
- **`NotesFragment.kt`:** Manages the creation and editing of notes.
- **`UpdateFragment.kt`:** Allows users to update existing notes.

## Technical Details

- **Architecture:** Followed the Model-View-ViewModel (MVVM) architecture pattern for clean separation of concerns and easier unit testing.
- **Database Management:** Migrated from SQLite to Room Persistence Library for efficient data management.
- **UI Design:** Designed the user interface using Material Design guidelines, ensuring a modern and intuitive user experience.
- **Asynchronous Programming:** Implemented asynchronous tasks using Kotlin coroutines to perform database and network operations without blocking the main thread.
- **Error Handling:** Implemented robust error handling mechanisms to provide a seamless user experience even in case of unexpected errors.

## Setup and Installation

1. **Clone the repository:**
   git clone https://github.com/arsh-kum04/NOTE-CHAD.git
   
2. **Open the project in Android Studio.**
3. **Build the project:**
4. **Run the app on an emulator or connected device.**

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
