package com.app.olders_rrss.DB

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.app.olders_rrss.Clases.screenActiv
import java.util.Date

@Entity
data class ent_evaluacion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,       // PK
    @ColumnInfo("ID_Usuario") val ID_usu: Int,      // FK
    @ColumnInfo("Codigo_Eval") val strCodigo: String,
    @ColumnInfo("Denominacion") val strDenominacion: String,
    @ColumnInfo("Inicio") val datInicio: Date,
    @ColumnInfo("Modificacion") var datModif: Date,
    @ColumnInfo("Resultado") var douResultado: Double,
    @ColumnInfo("Avance") var douAvance: Double,
    @ColumnInfo("Finalizado") var booFinalizado: Boolean,
    @ColumnInfo("Diagnostico_List") var listDiagnostico: MutableList<screenActiv>
)

@Dao
interface dao_evaluacion {

    @Query("SELECT * FROM ent_evaluacion WHERE ID_Usuario = :ID_usu")
    suspend fun GetListEvalForID(ID_usu: Int): List<ent_evaluacion>

    @Query("SELECT MAX(ID) AS ID FROM ent_evaluacion")
    suspend fun GetLastID(): Int

    @Query("SELECT * FROM ent_evaluacion WHERE id = :ID")
    suspend fun FindById(ID: Int): ent_evaluacion

    @Insert
    suspend fun insertEvaluacion(vararg evaluacion: ent_evaluacion)

    @Update
    suspend fun updateEvaluacion(evaluacion: ent_evaluacion)

    @Delete
    suspend fun delete(evaluacion: ent_evaluacion)
}
