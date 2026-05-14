# HabbitReminder 📱🚀

**HabbitReminder** es una aplicación nativa para Android diseñada para la gestión inteligente de rutinas y el seguimiento de hábitos a lo largo del tiempo. 

Este proyecto ha sido desarrollado como **Trabajo de Fin de Grado (TFG)** para el Grado Superior de Desarrollo de Aplicaciones Multiplataforma (DAM) en el **IES Torre del Rey**. La aplicación busca ofrecer una solución intuitiva, visual y eficiente para que los usuarios mantengan el control sobre sus objetivos diarios.

## ✨ Características Principales

- **Gestión Dinámica de Tareas**: Crea, edita y organiza tus tareas diarias con una interfaz fluida.
- **Categorización Visual**: Personaliza cada hábito utilizando un selector de **emojis** y una amplia gama de **colores**, facilitando la identificación rápida de tareas.
- **Seguimiento Temporal (Heatmap)**: Incluye una vista de calendario tipo "mapa de calor" que registra tu productividad:
  - 🟢 **Verde**: Tareas completadas.
  - 🔴 **Rojo**: Tareas pendientes o atrasadas.
  - 🟡 **Amarillo**: Cumplimiento parcial.
- **Organización Inteligente**: La pantalla principal divide automáticamente las tareas en secciones: *Atrasadas*, *Hoy*, *Mañana* y *Próximas*.
- **Sistema de Notificaciones**: Recordatorios programados mediante procesos en segundo plano para asegurar que nunca olvides una rutina.
- **Interacción por Gestos**: Implementación de *swipes* (deslizar) para marcar tareas como completadas o eliminarlas de forma rápida y moderna.
- **Privacidad y Persistencia**: Almacenamiento local mediante base de datos SQLite, garantizando que tus datos nunca salgan de tu dispositivo.

## 🛠️ Stack Tecnológico

El proyecto utiliza las herramientas más modernas recomendadas por Google para el desarrollo de Android nativo:

- **Lenguaje**: [Kotlin](https://kotlinlang.org/)
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Arquitectura declarativa).
- **Persistencia de Datos**: [Room Database](https://developer.android.com/training/data-storage/room) (SQLite).
- **Inyección de Dependencias**: [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
- **Tareas en Segundo Plano**: [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) para la gestión de notificaciones.
- **Arquitectura**: MVVM (Model-View-ViewModel).

## 🚀 Instalación y Desarrollo

### Requisitos previos
* Android Studio (versión Iguana o superior recomendada).
* Dispositivo Android o Emulador con API 24 (Android 7.0) o superior.

### Pasos para ejecutar
1. Clona el repositorio:
   ```bash
   git clone [https://github.com/Driusito/HabbitReminderApp.git](https://github.com/Driusito/HabbitReminderApp.git)
2.Abre el proyecto en Android Studio.

3.Sincroniza el proyecto con los archivos de Gradle.

4.Ejecuta la aplicación en tu dispositivo o emulador.

## 📂 Estructura del Proyecto
El proyecto sigue una estructura limpia organizada por capas:

ui: Contiene los Composables, temas y ViewModels.

data: Gestión de la base de datos Room, DAOs y entidades.

di: Configuración de los módulos de Inyección de Dependencias con Hilt.

worker: Lógica para las notificaciones programadas con WorkManager.

##👤 Autor
Andrés Jesús Jurado Suárez - GitHub

Proyecto desarrollado para el módulo de Proyecto Integrado - IES Torre del Rey (Promoción 2023/2024)
