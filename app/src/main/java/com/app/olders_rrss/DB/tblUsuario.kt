package com.app.olders_rrss.DB

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import java.util.Date

@Entity
data class ent_usuario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("Nombre") val strNombre: String? = "",
    @ColumnInfo("Apellido") val strApellido: String? = "",
    var booLocal: Boolean = false,
    var booNotif: Boolean = false,
    var Sexo: String? = "",
    @ColumnInfo("Nivel_Educacion") val strNivelEduc: String,
    @ColumnInfo("Temas_Interes") val strTemasInteres: String,
    @ColumnInfo("Experiencia_Tecnologica") val intExperTecn: Int,
    @ColumnInfo("Frecuencia_Uso") val intFrecUso: Int,
    @ColumnInfo("Correo") val strCorreo: String,
    @ColumnInfo("Password") val strPassword: String,
    @ColumnInfo("Nacimiento") var datNacimiento: Date = Date(),
    var datRegistro: Date = Date(),
    var booAlfabetizado: Boolean? = false,
    var booAudio: Boolean? = true,
    var intBotonPixel: Int? = 10,
    var booUsuarioApto: Boolean? = true
)

@Dao
interface dao_usuario {

    @Query("SELECT * FROM ent_usuario")
    suspend fun GetUsuarios(): List<ent_usuario>

    @Query("SELECT * FROM ent_usuario WHERE id IN (:usuarioIDs)")
    suspend fun GetListEspecifica(usuarioIDs: IntArray): List<ent_usuario>

    @Query("SELECT * FROM ent_usuario WHERE Nombre LIKE :first AND Apellido LIKE :last LIMIT 1")
    suspend fun FindByName(first: String, last: String): ent_usuario


    @Query("SELECT * FROM ent_usuario WHERE id = :ID")
    suspend fun FindById(ID: Int): ent_usuario

    @Query("SELECT MAX(ID) AS ID FROM ent_usuario")
    suspend fun GetLastID(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsu(vararg usuario: ent_usuario)

    @Delete
    suspend fun delete(usuario: ent_usuario)
}
