# Android Sample Apps
Sample apps and demos that use modern Android architecture components or that I found interesting. Please note that due to format restrictions, GIFs might not be perfect (and they don't fully reflect performances of app). It would seem that GitHub now embeds videos :D .   

## MVVM Todo
Simple Todo app which uses modern Android architecture components. Featuring:
* Room 
* Hilt (Dagger 2) 
* ViewModel
* LiveData/Flow
* Navigation with NavArgs (Single Activity app)
* View binding
* SearchView
* *exhaustive Kotlin compiler trick*, [see this link](https://proandroiddev.com/kotlin-when-statement-when-expression-oh-my-or-how-we-created-our-custom-detekt-rule-6f27e80bedaf)  
  
[Watch demo](https://user-images.githubusercontent.com/20267231/112981615-bf273000-915b-11eb-9861-9fcf7aafed9e.mp4)



## Image Search
App that provides image search functionality with help from Unsplash. If you download source code make sure to obtain and insert your own **Unsplash API access key** in **gradle.properties** file. Featuring:
* Retrofit (to use Unsplash API)
* Navigation with NavArgs (Single Activity app)
* View binding
* SearchView
* Hilt (Dagger 2) 
* Paging 3
* ViewModel

<br>
<img src="/readme/image-search.gif" alt="Image search demo" width="320" height="640"/>

## Circular Dial RecyclerView
Custom Layout Manager implementation for RecyclerView. Circular dial/arc layout.

<br>
<img src="/readme/CircularDialRecyclerView.gif" alt="Circular Dial RecyclerView demo" width="320" height="640"/>

## Spotify Clone
Simple music player that acts as Spotify Clone. Music is streamed from Firebase so you need to obtain and insert your own **google-services.json** 
* Firebase (Firestore & Storage) 
* Hilt (Dagger 2) 
* ViewModel
* LiveData
* Simple Navigation
* Kotlin synthetic (deprecated)
* Exoplayer (and related APIs)
  
[Watch demo](https://user-images.githubusercontent.com/20267231/112981615-bf273000-915b-11eb-9861-9fcf7aafed9e.mp4)


