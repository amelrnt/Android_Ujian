package com.wenchao.jsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Soal1;

public class Database extends SQLiteOpenHelper {
    final static String DB_NAME = "db_kuis.db";
 
    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT,pil_d TEXT,jwban INTEGER, img BLOB,tipe INTEGER,grup INTEGER)";
        db.execSQL(sql);
    }
   
    
    public List<Soal1> getSoal(){
        List<Soal1> section2 = new ArrayList<Soal1>();
        		
        String query = "SELECT * FROM tbl_soal WHERE tipe=2 order by grup, random()";
             
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
         
        
        if(cursor.moveToFirst()){
            do{
                Soal1 s = new Soal1();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setPil_d(cursor.getString(5));
                s.setJwban(cursor.getInt(6));
                s.setGambar(cursor.getInt(7));
                section2.add(s);
            }while(cursor.moveToNext());
              
        }   	
    	 return section2;
    }

    public List<Soal1> getSoal4(){
        List<Soal1> section4 = new ArrayList<Soal1>();

        String query = "SELECT * FROM tbl_soal WHERE tipe=4 order by grup, random()";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            do{
                Soal1 s = new Soal1();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setPil_d(cursor.getString(5));
                s.setJwban(cursor.getInt(6));
                s.setGambar(cursor.getInt(7));
                section4.add(s);
            }while(cursor.moveToNext());

        }
        return section4;
    }

    public List<Soal1> getSoal5(){
        List<Soal1> section5 = new ArrayList<Soal1>();

        String query = "SELECT * FROM tbl_soal WHERE tipe=5 order by grup, random()";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            do{
                Soal1 s = new Soal1();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setPil_d(cursor.getString(5));
                s.setJwban(cursor.getInt(6));
                s.setGambar(cursor.getInt(7));
                section5.add(s);
            }while(cursor.moveToNext());

        }
        return section5;
    }

    public List<Soal1> getSoal6(){
        List<Soal1> section6 = new ArrayList<Soal1>();

        String query = "SELECT * FROM tbl_soal WHERE tipe=6 order by grup, random()";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            do{
                Soal1 s = new Soal1();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setPil_d(cursor.getString(5));
                s.setJwban(cursor.getInt(6));
                s.setGambar(cursor.getInt(7));
                section6.add(s);
            }while(cursor.moveToNext());

        }
        return section6;
    }
    public List<Soal1> getSoal7(){
        List<Soal1> section7 = new ArrayList<Soal1>();

        String query = "SELECT * FROM tbl_soal WHERE tipe=7 order by grup, random()";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            do{
                Soal1 s = new Soal1();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setPil_d(cursor.getString(5));
                s.setJwban(cursor.getInt(6));
                s.setGambar(cursor.getInt(7));
                section7.add(s);
            }while(cursor.moveToNext());

        }
        return section7;
    }
    public List<Soal1> getSoal3(){
    	List<Soal1> section3 = new ArrayList<Soal1>();
    	 String query = "select * from tbl_soal where tipe = 3 order by grup, random()";
         
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(query, null);
          
         
         if(cursor.moveToFirst()){
             do{
                 Soal1 s = new Soal1();
                 s.setSoal(cursor.getString(1));
                 s.setPil_a(cursor.getString(2));
                 s.setPil_b(cursor.getString(3));
                 s.setPil_c(cursor.getString(4));
                 s.setPil_d(cursor.getString(5));
                 s.setJwban(cursor.getInt(6));
                 s.setGambar(cursor.getInt(7));
                 section3.add(s);
             }while(cursor.moveToNext());
               
         }   	
    	return section3;
    	}
    
    public List<Soal1> getSoal2(){
    	List<Soal1> section1 = new ArrayList<Soal1>();
    	 String query = "select * from tbl_soal where tipe = 1";
         
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(query, null);
          
         
         if(cursor.moveToFirst()){
             do{
                 Soal1 s = new Soal1();
                 s.setSoal(cursor.getString(1));
                 s.setPil_a(cursor.getString(2));
                 s.setPil_b(cursor.getString(3));
                 s.setPil_c(cursor.getString(4));
                 s.setPil_d(cursor.getString(5));
                 s.setJwban(cursor.getInt(6));
                 s.setAudio(cursor.getString(7));
                 section1.add(s);
             }while(cursor.moveToNext());
               
         }   	
    	return section1;
    	}
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_soal");
        db.execSQL("DROP TABLE IF EXISTS tbl_gambar");
        onCreate(db); 
    }
 
}