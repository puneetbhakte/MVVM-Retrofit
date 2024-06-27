# User App

An Android application that fetches user data from a remote API and displays it. The application follows the MVVM architecture and includes Retrofit library and liveData.

## Features

- Fetches user data from a remote API.
- Displays a loading indicator while fetching data.
- Utilizes MVVM architecture for clean and maintainable code.

## Technologies Used

- Kotlin
- Android Architecture Components (LiveData, ViewModel)
- Coroutines for background tasks

## Demo 


https://github.com/puneetbhakte/MVVM-Retrofit/assets/96128250/7e5acf87-112f-4116-be86-18df9cb27f46

## My approach to make this app

- First I have created a model class using JSON plugin
- Next I have created service interface and retrofit object for api call
- Then I have created a repository class which takes service interface as a parameter and here I have created a suspend function to run the function in background
- After that I have created a view model class whish take repository as a parameter and here i have launch the function in seperate coroutines.
- In viewmodel class I have also handle the loading indicator as a boolean variable.
- I have used live data to store the values.
- If we are using any parameter in viewmodel then we need to create a view model factory to create an instance of view model
- Atlast I have initialized the viewmodel in MainActivity with the help of repository and service.
- I have prepared a recyclerview to show the data.
   

