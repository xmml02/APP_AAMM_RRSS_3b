package com.app.olders_rrss.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ent_usuario::class, ent_evaluacion::class, ent_actividad::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class clsDB_local : RoomDatabase() {
    abstract fun dao_usuario(): dao_usuario
    abstract fun dao_evaluacion(): dao_evaluacion
    abstract fun dao_actividad(): dao_actividad
}

