# Restaurant Management App

## About the project

This repository contains an Android application for managing restaurant menu items.

The project was developed as an educational Android application using Kotlin. The application provides local storage of restaurant dishes and allows users to add new menu items and view the stored information in a grid-based interface.

The application uses Room Database for local data storage and RecyclerView for displaying restaurant items.

## Project objectives

The main objectives of the project were:

* to develop an Android application using Kotlin;
* to create a local database for storing restaurant menu items;
* to implement database operations using Room;
* to implement adding new restaurant items;
* to display stored data using RecyclerView;
* to use Kotlin Coroutines for database operations;
* to use Kotlin Flow for observing database changes;
* to implement a simple graphical user interface for restaurant management.

## Technologies

### Android

* Kotlin
* Android SDK
* Android Studio
* AppCompat
* ConstraintLayout
* RecyclerView
* View Binding

### Database

* Room Database
* SQLite
* DAO
* Entity
* Kotlin Flow

### Asynchronous operations

* Kotlin Coroutines
* CoroutineScope
* Dispatchers.IO
* lifecycleScope

## Application functionality

The application provides the following functionality:

* displaying all restaurant menu items;
* adding a new menu item;
* storing menu items in a local database;
* displaying the dish name;
* displaying the dish category;
* displaying the price;
* displaying the rating;
* displaying a dish image.

The main screen contains a two-column grid with all stored restaurant items.

A separate form is provided for adding new items to the database.

## Data model

The main database entity is `RestaurantItem`.

Each restaurant item contains the following fields:

| Parameter  | Type     | Description                          |
| ---------- | -------- | ------------------------------------ |
| `id`       | `Int`    | Unique identifier of the item        |
| `image`    | `Int`    | Reference to the dish image resource |
| `name`     | `String` | Name of the dish                     |
| `category` | `String` | Category of the dish                 |
| `price`    | `Double` | Price of the dish                    |
| `rating`   | `Double` | Rating of the dish                   |

The entity is stored in the `restaurant_items` database table.

## Database implementation

The application uses Room Database as a local database abstraction layer over SQLite.

The database is defined in the `RestaurantDB` class.

Database name:

```text
restaurant_db
```

The database contains the `restaurant_items` table represented by the `RestaurantItem` entity.

## DAO implementation

The `RestaurantDao` interface contains the main database operations.

Implemented operations include:

* inserting a new restaurant item;
* deleting an existing item;
* updating an item;
* retrieving all restaurant items;
* deleting an item by its ID.

The list of all items is returned as a Kotlin `Flow`:

```kotlin
@Query("SELECT * FROM restaurant_items")
fun getAllItems(): Flow<List<RestaurantItem>>
```

This allows the application to automatically receive updates when the database contents change.

## MainActivity

`MainActivity` is the main screen of the application.

The activity performs the following operations:

* initializes the Room database;
* creates the `RestaurantAdapter`;
* configures the RecyclerView;
* displays restaurant items in a two-column grid;
* observes database changes using Kotlin Flow;
* opens `FormActivity` for adding a new item.

The RecyclerView uses the following layout manager:

```kotlin
binding.rcView.layoutManager = GridLayoutManager(this, 2)
```

This configuration allows two restaurant items to be displayed in each row.

## FormActivity

`FormActivity` contains the form for adding a new restaurant item.

The user can enter:

* dish name;
* category;
* price;
* rating.

When the user presses the `Добавить` button, a new `RestaurantItem` object is created and inserted into the Room database.

Database insertion is performed on the IO dispatcher:

```kotlin
CoroutineScope(Dispatchers.IO).launch {
    db.getDao().insertItem(newItem)
    finish()
}
```

The default image resource `placeholder_dish` is used for newly created items.

## RestaurantAdapter

`RestaurantAdapter` connects the restaurant data from the database with the RecyclerView.

For each item, the adapter displays:

```text
Dish image
Dish name
Category
Price
Rating
```

The adapter receives a list of `RestaurantItem` objects and updates the RecyclerView when new data is received from the database.

## User interface

The main screen contains:

* RecyclerView for displaying restaurant items;
* button for adding a new item.

The restaurant items are displayed in a two-column grid.

The form screen contains:

* field for the dish name;
* field for the category;
* field for the price;
* field for the rating;
* button for adding the item.

## Application workflow

The application follows the following workflow:

```text
MainActivity
     |
     | Add new item
     v
FormActivity
     |
     | Create RestaurantItem
     v
RestaurantDao
     |
     | Insert
     v
RestaurantDB
     |
     | Flow
     v
MainActivity
     |
     v
RestaurantAdapter
     |
     v
RecyclerView
```

When a new item is added, it is stored in the local Room database. The database `Flow` detects the change and provides the updated list to `MainActivity`. The adapter then updates the RecyclerView.

## Project structure

```text
RestaurantApp/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/restaurantapp/
│           │   ├── RestaurantItem.kt
│           │   ├── RestaurantDao.kt
│           │   ├── RestaurantDB.kt
│           │   ├── RestaurantAdapter.kt
│           │   ├── MainActivity.kt
│           │   └── FormActivity.kt
│           │
│           └── res/
│               ├── drawable/
│               │   └── placeholder_dish
│               │
│               └── layout/
│                   ├── activity_main.xml
│                   ├── activity_form.xml
│                   └── restaurant_item.xml
│
└── README.md
```

The project structure may be adjusted depending on the Android Studio project configuration and additional resources.

## Database operations

The DAO provides the following operations:

| Operation    | Method          | Description                    |
| ------------ | --------------- | ------------------------------ |
| Insert       | `insertItem()`  | Adds a new restaurant item     |
| Delete       | `deleteItem()`  | Deletes an existing item       |
| Update       | `updateItem()`  | Updates an existing item       |
| Select       | `getAllItems()` | Retrieves all restaurant items |
| Delete by ID | `deleteById()`  | Deletes an item using its ID   |

## How to run

To run the project:

1. Clone the repository.
2. Open the project in Android Studio.
3. Synchronize the Gradle files.
4. Connect an Android device or start an Android Emulator.
5. Run the application.

Example:

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

The project requires Android Studio, Android SDK and a compatible JDK.

## Possible improvements

Possible directions for further development include:

* implementation of editing existing restaurant items;
* implementation of deleting items directly from the user interface;
* adding custom dish images;
* adding search functionality;
* filtering dishes by category;
* sorting dishes by price or rating;
* implementing input validation;
* adding restaurant table management;
* adding order management;
* adding employee management;
* adding statistics and reports;
* implementing ViewModel and Repository layers;
* improving the interface using Material Design components.

## Conclusion

The project demonstrates the development of a basic Android application for restaurant menu management using Kotlin.

The application implements local data storage using Room Database, database access through DAO, asynchronous operations using Kotlin Coroutines, and reactive data observation using Kotlin Flow.

The combination of Room Database and RecyclerView allows restaurant menu data to be stored locally and displayed dynamically in the application interface.

## Author

Татьяна Спиридонова
