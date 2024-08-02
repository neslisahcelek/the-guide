
# The Guide - Discover Your City

A mobile application providing personalized recommendations for urban activities based on user interests, past activities, and ratings. This project is developed as part of the senior graduation project at Akdeniz University, Computer Engineering Department. And it is supported by the TUBITAK 2209-A Research Project Support Programme.

## Features
### Personalized Recommendations
The core feature of the application is to offer tailored suggestions for places and activities. This personalization is achieved through:

- **User Profile Analysis**: The application collects data on user preferences, past activities, and ratings to understand their interests.
- **Machine Learning Algorithms**: Specifically, the Probabilistic Matrix Factorization (PMF) model is used to analyze user data and generate recommendations. This model considers factors like user history and feedback to predict places the user might enjoy.

### Comprehensive City Information
Users can access detailed information about various places in their city, including:

- **Descriptions and Photos**: Each place has a detailed description, along with photos, to give users a better understanding of what to expect.
- **Reviews and Ratings**: Users can read reviews and see ratings provided by other users, helping them make informed decisions.

### User Interaction Features
- **Review and Rating System**: Users can rate and review places they visit, contributing to the community and improving the recommendation system.
- **Wishlist**: Users can save places they are interested in visiting later by adding them to their wishlist.
- **Filtering**: Users can filter recommended places by districts.

### Secure Authentication
The application uses Google Sign-In for secure and convenient authentication. This allows users to quickly create an account and start using the app without hassle.

### Real-Time Updates
Places in the city can change frequently. The app provides real-time updates about new places, ensuring users always have the latest information.

### Mobile Application

- **Kotlin**: The primary language used for developing the Android app. Kotlin is known for its modern syntax and safety features, making it a robust choice for mobile development.
- **Jetpack Compose**: A modern toolkit for building native Android UI. Jetpack Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.

### Tools and Libraries

- **Retrofit**: A type-safe HTTP client for Android and Java. Retrofit is used for making network requests to the backend API, handling JSON responses, and simplifying REST API calls.
- **Dagger Hilt**: A dependency injection library that simplifies the process of integrating Dagger into an Android application. Hilt manages the dependencies of the app's components, ensuring a clean architecture.
- **Material 3**: Implements Material Design components, providing a consistent and modern look and feel across the app.
- **Coil**: An image loading and caching library for Android. Coil is used for efficient image loading, caching, and displaying within the app.
- **Firebase**: Provides backend services such as authentication, real-time database, and analytics. Firebase Authentication is used for secure user login, and Firebase Realtime Database stores user and place data.

## Architecture

The application follows the **Model-View-Intent (MVI)** architecture pattern. MVI helps manage the app's state and side effects in a predictable and maintainable way. Key components include:

- **Model**: Represents the state of the application.
- **View**: Displays the UI based on the state provided by the model.
- **Intent**: Represents the user's intention, triggering changes in the state.

## How to Use

1. **Sign In**: Use your Google account for sign-in.
2. **Explore**: Browse through personalized recommendations and discover new places.
3. **Rate & Review**: Rate and review places to share your experiences.
4. **Personalize**: Set preferences and filter recommendations to tailor your experience.


https://github.com/user-attachments/assets/eefdde01-6abc-4f34-84ca-d4d0b6ad7521

