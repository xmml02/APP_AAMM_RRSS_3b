package com.app.olders_rrss

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

// val db = Room.databaseBuilder(this, App_DB::class.java, "DB_App").build()

/*
lifecycleScope.launch {
                db.usuarioDAO().insertUsu(                     usuario(
                        firstName = binding.editTextText.text.toString(),
                        lastName = binding.editTextTextEmailAddress.text.toString()                     )
                )             }
 */


@Database(entities = [TEST_usuario::class], version = 1)
abstract class TEST_App_DB : RoomDatabase() {
    abstract fun TEST_usuarioDAO(): TEST_usuarioDao
}

@Dao
interface TEST_usuarioDao {
    @Query("SELECT * FROM TEST_usuario")
    suspend fun GetUsuarios(): List<TEST_usuario>

    @Query("SELECT * FROM TEST_usuario WHERE id IN (:usuarioIDs)")
    suspend fun GetListEspecifica(usuarioIDs: IntArray): List<TEST_usuario>

    @Query("SELECT * FROM TEST_usuario WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    suspend fun FindByName(first: String, last: String): TEST_usuario

    @Query("SELECT * FROM TEST_usuario WHERE id = :ID")
    suspend fun FindById(ID: Int): TEST_usuario


    @Insert
    suspend fun insertUsu(vararg usuario: TEST_usuario)

    @Delete
    suspend fun delete(usuario: TEST_usuario)
}

@Entity
data class TEST_usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") var firstName: String? = null,
    @ColumnInfo(name = "last_name") var lastName: String? = null
)
