# Tracks-by-iTunes
A sample project which retrieves Tracks from the iTunes Search API

## Architecture
This projects uses the Model-View-ViewModel (MVVM) architecture. For Model, we have the repository layer for handling Track data. For View, we have fragments for displaying 
the Track data. And finally, the ViewModel handles the interaction between the Model and the View. We are using this architecture to have a separation of concerns, manage the 
Android lifecycle better, and handle asynchronous operations and events easier.

## Persistence
For local persistence, we are using the Room library. For the database, we have ItunesSearchDatabase for our data storage. We have the TrackEntity class to define the Track table
and columns. Then, our data can be accessed using the TrackDao (data-access object). 

## Download
APK: https://drive.google.com/file/d/10-i4vLhFvPyDn9SY6AJWoy5dJvc3bzjJ/view?usp=sharing
