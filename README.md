# PokedexApp
<h1 align="center">Pokedex</h1>
<p align="center">  
A simple Pokedex App getting API with Retrofit, maintaining data using LiveData, and Material Design based on MVVM architecture.
</p>

<p align="center">
<img src="/Main.png" width="32%"/>
<img src="/Detail.png" width="32%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData)
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding - Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Glide](https://github.com/bumptech/glide)

## MAD Score
![summary](https://user-images.githubusercontent.com/92455262/165884403-0821bb8f-b25a-4e9c-98ac-8255a5d3e0a7.png)

## Architecture
Pokedex is based on the MVVM architecture

## Open API
<img src="https://pokeapi.co/static/pokeapi_256.3fa72200.png" align="right" width="21%"/>
Pokedex using the [PokeAPI](https://pokeapi.co/) for constructing RESTful API.<br>
PokeAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Pokémon.
