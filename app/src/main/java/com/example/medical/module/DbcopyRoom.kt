//package com.example.medical.module
//
//import android.content.Context
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
////package com.example.medical.module
//
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponet :: class)
//object DBCopyRoom {
//    @Singleton
//    @Provides
//    fun provideDatanase( @ApplicationContext context: Context) =  Room.databaseBuilder(
//        context.applicationContext,
//        DatabaseModule::class.java,
//        "medical"
//    ).build()
//
//    @Singleton
//    @Provides
//    fun provideChuyenKhoaDao(db: DatabaseModule) = db.chuyenkhoaDAO()
//
//    @Singleton
//    @Provides
//    fun provideBacSiDao(db: DatabaseModule) = db.bacsiDAO()
//}