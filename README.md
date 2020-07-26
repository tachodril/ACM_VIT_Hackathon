# ACM-VIT-Hackathon
### This project solves one of the problem statements of ACM-VIT Hackathon 2020.
**Problem Statements:**
* Implement a Bottom Navigation Bar 
* Make use of dependency injection 
* Implement local caching using database 

## Theme:
We have built a Mobile Application which will help big retail stores to enable their customers to easily find products in their store and optimize their overall shopping experience. User have to create a shopping list from the app and the app will provide the shortest paths.

## App Features:
### Stated in problem statement
* Implemented a Bottom Navigation Bar
* Made use of dependency injection
* Implemented local caching using database

### Other prominent features
* Search a product using its name or category
* Add search results to custom shopping list
* Scan a handwritten list and extract items to list
* Voice navigation for shopping
* Indoor Map system of the store
* Smartly sorted list according to their location, weight and size


## Installation Instructions:

### 1. Using APK (Preferred)
  *  Download the apk file from [here](https://github.com/prince11sysop/Lowes-Product-Finder/releases/tag/v1.0).
  * Copy the apk named “ ACM_VIT_Hackathon ” in your Android device
  * Install the apk (Please allow if asked for any permission)
  * Open the app

### 2. Using GitHub Repository:
  * Clone the repository in Android Studio.
  * Build the Gradle
  * Click on the “Run” button to run the app on your device or on the emulator.

  
## Tech Stack:

### Technologies:
* Java
* Android
* Android Studio
* Device Interal storage
* Shared Preferences
* Picasso
* GSON
* Firebase ML Kit
* Firebase Database
* Git & GitHub

## Challenges faced:
* We decided to cache the scanned image using thumbnails, but it was giving bad image quality for ML Vision processing. So, we stored the image in the local storage instead of using the image's thumbnails.
* Customizing the app according to any specific retail store was not possible, so we created a generalised solution which can be customized for any store.
* Finding the api to indoor mapping system was not easy until we find Google's Indoor Mapping.

## Accomplishments:
We have successfully integrated all 3 requirements of the problem statements -
* Bottom Navigation View to navigate through 5 featured tabs in the home page.
* We have heavily used Dependency Injection in various parts of the project (eg - passing contexts, adapters, activity instances, etc. into constructors of static methods. The best implementation of the same is in modal classes).
* We have used local caching in database in many features. Users shopping list is saved in cache using shared preferences, scanned images are stored in local storage of the device and once viewed web pages (in featured websites tab) are saved in local cache.

## How to use - Video Link: 
* Click here to view the demonstration video of this app.

## Screenshots:
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/FeaturedWebsitesTab.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/SearchItem.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/SearchSuggestions.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/ShoppingListTab.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/StoreMapShopping.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/StartShoppingTab.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/ScanIListTab.jpg" width="200" height="400">
<img src="https://github.com/tachodril/ACM_VIT_Hackathon/blob/master/Screenshots/AboutPage.jpg" width="200" height="400">



## Data on which app is supposed to be tested:
* LG Refrigerator, LG Ceiling fans
* Smart Microwave, ,LG Dishwasher
* Home Repair tools, Appliances
* Corniva special bathtub



