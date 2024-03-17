package com.app.olders_rrss.DB

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.app.olders_rrss.Clases.enumCategoria
import com.app.olders_rrss.Clases.enumModulo
import com.app.olders_rrss.Clases.enumPlataforma
import java.util.Date

@Entity
data class ent_actividad(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,           // PK
    @ColumnInfo("Codigo_Eval") val strCodigo: String,      // FK
    @ColumnInfo("ID_Usuario") val ID_usu: Int,              //FK
    @ColumnInfo("Denominacion") val strDenominacion: String,
    @ColumnInfo("Texto_Usuario") val strTextoUsu: String,
    @ColumnInfo("Logro_Nombre") var strLogro: String,

    @ColumnInfo("Plataforma") val ePlataforma: enumPlataforma,
    @ColumnInfo("Categoria") val eCategoria: enumCategoria,
    @ColumnInfo("Modulo") val eModulo: enumModulo,

    @ColumnInfo("Inicio") val datInicio: Date,
    @ColumnInfo("Modificacion") var datModif: Date,
    @ColumnInfo("Resultado") var douResultado: Double,
    @ColumnInfo("Avance") var douAvance: Double,
    @ColumnInfo("Finalizado") var booFinalizado: Boolean,

    @ColumnInfo("Select") var booSelect: Boolean,
    @ColumnInfo("Suggest") var booSuggest: Boolean,

    var Eval_1: Double,
    var Eval_2: Double,
    var Eval_3: Double,
    var Eval_4: Double,
    var Eval_5: Double,
    var Opc_1: Double,
    var Opc_2: Double,
    var Opc_3: Double

    //@ColumnInfo("Tareas_List") var listTareas: MutableList<screenActiv>
)

@Dao
interface dao_actividad {

    @Query("SELECT * FROM ent_actividad WHERE ID_Usuario = :ID_usu")
    suspend fun GetListActForID(ID_usu: Int): List<ent_actividad>

    @Query("SELECT MAX(ID) AS ID FROM ent_actividad")
    suspend fun GetLastID(): Int

    @Query("SELECT * FROM ent_actividad WHERE id = :ID")
    suspend fun FindById(ID: Int): ent_actividad

    @Insert
    suspend fun insertActividad(vararg actividad: ent_actividad)

    @Update
    suspend fun updateActividad(actividad: ent_actividad)

    @Delete
    suspend fun delete(actividad: ent_actividad)
}
