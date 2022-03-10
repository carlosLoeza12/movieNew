package com.example.movienew.di

import com.example.movienew.repository.MovieRepository
import com.example.movienew.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// le tenemos que decir a hilt donde estan las implementaciones que se van a utilizar en el viemodel
//Se crea el modulo del viewmodel para unificar las interfaces con su implementacion
//en este caso con el anotador Binds

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelModule {

    //Le decimos que haga el match de la interfaz con la implementacion
    //el viewmodel va a utilizar este metodo por que es el que devuelve la interfaz con su implementacion
    @Binds
    abstract fun bindRepoImpl(movieRepositoryImp: MovieRepositoryImp): MovieRepository
}