package com.app.olders_rrss.DB

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.util.Date

@Entity
data class ent_evaluacion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("Codigo") val strCodigo: String,
    @ColumnInfo("Denominacion") val strDenominacion: String,
    @ColumnInfo("Inicio") val datInicio: Date,
    @ColumnInfo("Modificacion") var datModif: Date,
    @ColumnInfo("Resultado") var douResultado: Double,
    @ColumnInfo("Avance") var douAvance: Double,
    @ColumnInfo("Finalizado") var booFinalizado: Boolean,
    @ColumnInfo("ID_usu") val ID_usu: Int
)

@Dao
interface dao_evaluacion {

    @Query("SELECT * FROM ent_evaluacion")
    suspend fun GetEvaluaciones(): List<ent_evaluacion>

    @Query("SELECT * FROM ent_evaluacion WHERE id IN (:evaluacionIDs)")
    suspend fun GetListEspecifica(evaluacionIDs: IntArray): List<ent_evaluacion>

    @Query("SELECT * FROM ent_evaluacion WHERE Codigo LIKE :codigo LIMIT 1")
    suspend fun FindByCodigo(codigo: String): ent_evaluacion

    @Query("SELECT * FROM ent_evaluacion WHERE id = :ID")
    suspend fun FindById(ID: Int): ent_evaluacion

    @Query("SELECT MAX(ID) AS ID FROM ent_evaluacion")
    suspend fun GetLastID(): Int

    @Insert
    suspend fun insertEvaluacion(vararg evaluacion: ent_evaluacion)

    @Delete
    suspend fun delete(evaluacion: ent_evaluacion)
}
