package com.example.movienew.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//El punto de partida es el aplication que es el que se mantiene vivo hasta que se destruya la aplicacion
// y por ende se podran acceder al grafico y obtener las dependenciasma(Es el contenedor del grafico)
@HiltAndroidApp
class BaseApplication: Application() {
}