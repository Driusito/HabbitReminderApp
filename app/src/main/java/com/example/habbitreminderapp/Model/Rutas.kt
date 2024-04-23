package com.example.habbitreminderapp.Model

sealed class Rutas(val ruta:String)
object Inicio:Rutas("Inicio")
object MiPerfil:Rutas("MiPerfil")
object NuevaMeta:Rutas("NuevaMeta")
object MisMetas:Rutas("MisMetas")
object Configuracion:Rutas("Configuracion")