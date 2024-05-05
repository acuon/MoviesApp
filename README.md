# Movies App

This is a simple movies app that allows users to search for movies using the iTunes Search API. Users can view details about each movie and mark their favorite movies within the app.

### Features:
 - Search for movies using the iTunes Search API.
 - View detailed information about each movie, including title, description, and poster.
 - Watch trailers of movies directly within the app.
 - Mark movies as favorites for easy access later.

### Download the App

You can download the APK file of the app from the following sources:

- **Google Drive**: [Google Drive URL](https://drive.google.com/file/d/1GzH0EFqKuWTX1TowEfybtPhsaPLtPcJM/view?usp=drive_link)
- **GitHub**: [GitHub Repository](https://github.com/acuon/MoviesApp/blob/main/files/apk/app-debug.apk)
- **Bitrise**: [Bitrise Builds](https://app.bitrise.io/app/5eba9049-8448-4921-b4a0-564e984cc060/build/4d93cf67-a71d-4660-bb07-b2a3831d697f/artifact/ad27f13e96c93138/p/65eee079b21b3523ef5ff0676566306d)

### Screenshots
<p align="center">
  <a href="https://github.com/acuon/MoviesApp" title="Home Screen"><img height="300" width="150" src="https://github.com/acuon/MoviesApp/blob/main/files/screens/home_page_empty_view.jpg"></a>
  <a href="https://github.com/acuon/MoviesApp" title="Home Screen"><img height="300" width="150" src="https://github.com/acuon/MoviesApp/blob/main/files/screens/home_page_search_view.jpg"></a>
  <a href="https://github.com/acuon/MoviesApp" title="Movie Detail Screen"><img height="300" width="150" src="https://github.com/acuon/MoviesApp/blob/main/files/screens/movie_detail_page.jpg"></a>
  <a href="https://github.com/acuon/MoviesApp" title="Movie Detail Screen"><img height="300" width="150" src="https://github.com/acuon/MoviesApp/blob/main/files/screens/movie_detail_page_trailer.jpg"></a>
  <a href="https://github.com/acuon/MoviesApp" title="Favorite Screen"><img height="300" width="150" src="https://github.com/acuon/MoviesApp/blob/main/files/screens/favorite_movies_page.jpg"></a>
</p>

### Demo
You can watch the app demo here: 
[Watch Demo Video](https://drive.google.com/file/d/1D7FxxCFYGDzGlunfajKW_QiUBql0owZe/view?usp=drive_link)

### Installation

To install and run the Movies, follow these steps:
1. **Clone this repository to your local machine:**

   ```bash
   https://github.com/acuon/MoviesApp.git

2. **Build and Run**:
   - Build and run the Bhagwad Geeta app on your Android device or emulator.

# Tech Stack Used

Below are the technology stack and libraries used in the MoviesApp:

- **Navigation**:
  - **Description**: Navigation component simplifies navigation and helps in implementing predictable navigation patterns.
  - **Pros**: Simplifies navigation setup, supports deep linking, and provides type safety.
  - **Cons**: Can be complex to set up for advanced navigation scenarios.

- **Dependency Injection**:
  - **Description**: Dagger Hilt simplifies dependency injection by generating code and providing a standard way to perform field and constructor injection.
  - **Pros**: Easy to set up, reduces boilerplate code, and supports Android-specific scopes.
  - **Cons**: Learning curve for beginners, requires annotation processing.

- **Logging**:
  - **Description**: Timber is a lightweight logging library that provides easy-to-read logs with minimal setup.
  - **Pros**: Simple API, customizable log output, and efficient logging mechanism.
  - **Cons**: Limited features compared to more advanced logging libraries.

- **JSON Parsing**:
  - **Description**: Gson is a popular library for converting Java objects to JSON and vice versa.
  - **Pros**: Simple API, good performance, and wide adoption.
  - **Cons**: Lack of support for some advanced JSON parsing features.

- **Networking**:
  - **Description**: Retrofit is a type-safe HTTP client for Android and Java.
  - **Pros**: Easy to use, type-safe API, support for multiple protocols, and efficient network calls.
  - **Cons**: Requires separate converter libraries for JSON parsing, configuration can be complex for advanced scenarios.

- **Lifecycle Management**:
  - **Description**: Lifecycle components help in managing UI components lifecycle events and preventing memory leaks.
  - **Pros**: Simplifies lifecycle management, supports LiveData integration, and reduces boilerplate code.
  - **Cons**: Limited support for complex lifecycle scenarios.

- **Testing**:
  - **Description**: Various testing libraries help in writing and executing unit and UI tests to ensure app quality.
  - **Pros**: Enables automated testing, improves app stability, and identifies bugs early in the development cycle.
  - **Cons**: Setting up test environments and writing comprehensive test cases can be time-consuming.

- **Image Loading**:
  - **Description**: Glide is a powerful and flexible image loading library for Android that provides smooth and efficient image loading and caching capabilities.
  - **Pros**: Easy to integrate, supports caching and memory optimization, offers a rich set of features such as image transformations, GIF support, and placeholder images.
  - **Cons**: Requires additional setup compared to simpler image loading libraries like Coil, may have a slightly steeper learning curve for beginners.

- **Room Database**:
  - **Description**: Room is an abstraction layer over SQLite that provides a more robust database access while leveraging the full power of SQLite.
  - **Pros**: Simplifies database operations, provides compile-time checks, and supports LiveData integration.
  - **Cons**: Limited support for complex database operations compared to other ORM libraries.

# Architecture & Design Patterns Used

### MVVM Architecture:
- **Description:** MVVM is a software architecture pattern that separates the user interface (UI) logic from the business logic. It divides the application into three main components: Model, View, and ViewModel.
  
- **Components:**
  - **Model:** Represents the data and business logic of the application. It typically includes data sources (e.g., databases, network services) and data manipulation logic.
  
  - **View:** Represents the user interface (UI) components of the application. It includes activities, fragments, layouts, and other UI elements that interact with the user.
  
  - **ViewModel:** Acts as a mediator between the View and the Model. It exposes data from the Model to the View through observable data streams (e.g., LiveData, RxJava Observables). It also handles user interactions and updates the Model accordingly. ViewModel instances survive configuration changes (e.g., screen rotations) and are scoped to the lifecycle of the associated View.

- **Key Features:**
  - **Separation of Concerns:** MVVM separates the presentation logic (ViewModel) from the UI (View) and the data logic (Model), promoting a clean and maintainable codebase.
  - **Data Binding:** Utilizes data binding frameworks (e.g., Android Data Binding, Kotlin synthetic binding) to establish a connection between the View and the ViewModel. This allows for automatic updating of UI elements based on changes in the ViewModel.
  - **Testability:** MVVM improves testability by decoupling the UI logic from the business logic. Unit tests can be written for ViewModel classes without requiring a running Android environment.
  - **Lifecycle Awareness:** ViewModel instances are lifecycle-aware, meaning they can automatically handle lifecycle events (e.g., onCreate(), onDestroy()) and retain data during configuration changes.
  - **Reactive Programming:** MVVM often leverages reactive programming libraries (e.g., LiveData, RxJava) to propagate data changes from the ViewModel to the View in a reactive and efficient manner.
- **Benefits:**
  - **Modularity:** MVVM promotes modular design, making it easier to add, modify, or remove features without affecting other parts of the application.
  - **Maintainability:** Separation of concerns and clear component boundaries enhance code maintainability, readability, and scalability.
  - **Code Reusability:** By decoupling the UI from the business logic, MVVM facilitates code reuse across different parts of the application and even between different applications.


### Singleton Pattern:
- **Description:** Singleton pattern restricts the instantiation of a class to a single object, providing a global access point to that instance throughout the application.

- **Reasons to Use:**
  - **Global Access:** Provides a single, globally accessible instance of a class, allowing all parts of the application to access it without the need to create multiple instances.
  - **Resource Sharing:** Useful for managing resources such as database connections, file system access, or shared configuration settings, ensuring efficient resource utilization.
  - **State Management:** Ensures that the state of the singleton object remains consistent across the application, avoiding inconsistencies that can arise from multiple instances.
  - **Simplifies Access:** Offers a straightforward way to access shared resources or services without the need for complex initialization or passing references between components.

### Dependency Injection (DI) pattern
- **Description:** The module `AppModule` provides various dependencies for the application using Dagger Hilt annotations such as `@Module`, `@Provides`, and `@Singleton`. It includes providers for Retrofit, OkHttpClient, Room database, API services, and repository implementations.

- **Reasons to Use:**
  - **Modularity:** Dependency Injection promotes modularity by decoupling components and providing them with their dependencies. This makes it easier to maintain and extend the application.
  - **Testability:** DI facilitates unit testing by allowing dependencies to be easily substituted with mock implementations during testing, improving testability.
  - **Single Responsibility Principle (SRP):** By separating the responsibility of creating and managing dependencies to a dedicated module, the SRP is adhered to, promoting clean architecture.
  - **Encapsulation:** Dependencies are encapsulated within the module, reducing the complexity of object creation and configuration throughout the application.
  - **Scalability:** Dependency Injection makes it easier to scale the application by simplifying the addition of new features and components without impacting existing code.

### ViewHolder Pattern:
- **Description:** Each item view in the RecyclerView is associated with a ViewHolder object. The ViewHolder pattern involves creating a ViewHolder class that holds references to the views within each item layout. This pattern improves scrolling performance by reusing views and minimizing the number of calls to findViewById().

- **Reasons to Use:**
  - **View Reusability:** ViewHolder objects are reused as views are scrolled in and out of the screen, reducing memory consumption and improving performance.
  - **Efficient View Lookup:** ViewHolder pattern caches references to views within item layouts, reducing the overhead of calling findViewById() repeatedly.
  - **Separation of Concerns:** ViewHolder separates view-related logic from the Adapter, promoting a clear separation of concerns and making code easier to maintain.
  - **Encapsulation:** ViewHolder encapsulates the logic for binding data to views and handling user interactions within each item layout, improving code organization and readability.

### **Repository Pattern:**
  - **Description:** The Repository pattern abstracts data access logic, providing a clean interface for interacting with data sources such as databases or APIs.
  - **Reasons to Use:**
    - **Abstraction:** Separates business logic from data access, promoting clean architecture.
    - **Testability:** Facilitates unit testing by decoupling data access from application logic.
    - **Modularity:** Encourages modular design by isolating data access concerns, improving maintainability.
    - **Dependency Inversion:** Adheres to Dependency Inversion Principle by depending on abstractions rather than concrete implementations.

### **Adapter Pattern:**
  - **Description:** The Adapter pattern acts as a bridge between incompatible interfaces, allowing objects with different interfaces to work together.
  - **Reasons to Use:**
    - **Compatibility:** Enables integration of components with different interfaces or protocols.
    - **Flexibility:** Adapts existing classes without modifying their code, promoting code reuse.
    - **Encapsulation:** Provides a standardized interface to interact with external components, improving encapsulation.
    - **Interoperability:** Facilitates communication between components from different libraries or systems, enhancing interoperability.

### Template Method Pattern:
- **Description:** The combined base class acts as a foundational template for various components in your Android application, including Activities, Fragments, and ViewModels. It encapsulates common functionality and provides a consistent structure for these components.
- **Reasons to Use:**
  - **Code Reuse:** The base class promotes reuse of common functionality across different parts of your application, reducing duplication and improving maintainability.
  - **Standardization:** Establishes a uniform structure and behavior for Activities, Fragments, and ViewModels, enhancing code consistency and making the application easier to understand and maintain.
  - **Separation of Concerns:** Encapsulates shared functionality, such as data binding setup, view initialization, coroutine execution, and preference management, within a single base class. This separation isolates common concerns from specific implementation details, facilitating better organization and modularity.
  - **Extensibility:** Subclasses (i.e., concrete Activity, Fragment, and ViewModel implementations) can extend the base class to customize behavior according to their specific requirements. They can override methods or properties to add or modify functionality while still benefiting from the common features provided by the base class.
- **Key Features:**
  - **Lifecycle Management:** Handles lifecycle events (e.g., onCreate(), onViewCreated()) to perform initialization and cleanup tasks.
  - **Data Binding:** Integrates with data binding framework for UI updates and two-way data binding.
  - **Coroutines:** Executes asynchronous tasks using Kotlin coroutines, providing a simplified and efficient concurrency model.
  - **Preference Management:** Manages application preferences for storing and retrieving user settings or app state.
  - **UI Interaction:** Facilitates UI interaction by handling common tasks such as showing/hiding the keyboard and displaying toast messages.
  - **Template Methods:** Defines template methods or hooks (e.g., setupViews(), bindViewModel()) that subclasses can override to customize behavior.
  
### Result Pattern or Result Wrapper Pattern:
- **Description:** The `ResultOf` sealed class represents the result of an operation, such as an API call or database operation. It encapsulates different states that the operation can be in, including success, error, and loading states.
- **Reasons to Use:**
  - **Unified Representation:** Provides a standardized way to represent the outcome of operations, making it easier to handle and communicate the result throughout the application.
  - **Error Handling:** Allows for explicit handling of success and failure cases, improving error handling and providing clear feedback to users or developers.
  - **Asynchronous Operations:** Suitable for asynchronous operations, such as network requests or database queries, where the result may not be immediately available.
  - **State Management:** Facilitates managing the state of asynchronous operations, including loading indicators or error messages, to provide a better user experience.
- **Key Features:**
  - **Sealed Class:** Uses a sealed class to define a restricted hierarchy of result states, ensuring that all possible states are explicitly defined and accounted for.
  - **State Representation:** Defines three subclasses (`Success`, `Error`, `Loading`) to represent the different states of an operation, each containing relevant data (e.g., success data, error message).
  - **Type Safety:** Uses generics (`T`) to provide type safety, allowing the result data to be of any specified type.
  - **Immutable Data:** Result instances are immutable, ensuring that once created, their state cannot be modified, promoting predictability and consistency.

### UseCase or Interactor Pattern:
- **Description:** The UseCase pattern encapsulates specific actions or operations within separate classes, isolating business logic from external systems or data sources.
- **Reasons to Use:**
  - **Modularity:** UseCases promote modular design by separating concerns and isolating specific operations.
  - **Testability:** They facilitate unit testing by providing clear boundaries around business logic.
  - **Reusability:** UseCases can be shared and reused across different parts of the application, reducing duplication.
  - **Separation of Concerns:** This pattern helps in keeping business logic separate from presentation and data access layers, improving code maintainability.

## Support

If you encounter any issues or have questions, please feel free to contact me at [rohitshar8600@gmail.com](mailto:rohitshar800@gmail.com), or you can create an issue on the project's GitHub repository for assistance.
