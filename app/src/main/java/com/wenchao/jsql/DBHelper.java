package com.wenchao.jsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import Model.Soal1;

public class DBHelper extends SQLiteOpenHelper {

 private static String DB_NAME = "dbkuis.sqlite";
 private SQLiteDatabase db;
 private final Context context;
 private String DB_PATH;
 public static String NAMA_PAKET = "nama_paket";
 public static String KEY_PAKET = "paket as _id";
 private static final int db_version=1;


 public DBHelper(Context context) {
  super(context, DB_NAME, null, db_version);
  this.context = context;
  DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
 }

 public void createDataBase() throws IOException {

  boolean dbExist = checkDataBase();
  if (dbExist) {
	  
  } else {
   this.getReadableDatabase();
   try {
    copyDataBase();
   } catch (IOException e) {
    throw new Error("Error copying database");
   }
  }
 }

 private boolean checkDataBase() {
  File dbFile = new File(DB_PATH + DB_NAME);
  return dbFile.exists();
 }

 private void copyDataBase() throws IOException {

  InputStream myInput = context.getAssets().open(DB_NAME);
  String outFileName = DB_PATH + DB_NAME;
  OutputStream myOutput = new FileOutputStream(outFileName);
  byte[] buffer = new byte[1024];
  int length;
  while ((length = myInput.read(buffer)) > 0) {
   myOutput.write(buffer, 0, length);
  }

  // Close the streams
  myOutput.flush();
  myOutput.close();
  myInput.close();

 } 
 
 
 public String getdbversion(){
	 String dbver="";
	 try {
	 	   String myPath = DB_PATH + DB_NAME;
	 	   db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 	  }catch(SQLiteException e){
	 	   
	 	 }
	 String query = "select * from tbl_versi";
	 Cursor cursor = db.rawQuery(query, null);
	 
	  if(cursor.getCount()>0){
		  cursor.moveToFirst();
		  dbver = cursor.getString(cursor.getColumnIndex("version"));
	  }
	  return dbver;
 }
 
 public void updatedbver(String ver){
	 db = this.getReadableDatabase();
	 String query ="update tbl_versi set version="+ver+" where id_ver =1";
	 db.execSQL(query);
 }
 
 public List<Soal1> getSoalDinamis(String paketsoal, String tipe){
	 List<Soal1> section = new ArrayList<Soal1>();
	 db = this.getWritableDatabase();
	 if(tipe=="1"){
		 String query = "SELECT * FROM tbl_soal WHERE paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";
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
	             section.add(s);
	         }while(cursor.moveToNext());
	           
	     }
	 	}
	 else if(tipe=="2"){
		 String query = "SELECT * FROM tbl_soal WHERE paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";
         
	     
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
	             section.add(s);
	         }while(cursor.moveToNext());
	           
	     }   
	 	}	
	 else if(tipe=="3"){
		 String query = "select * from tbl_soal where paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";
	      
	      
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
	              section.add(s);
	          }while(cursor.moveToNext());
	            
	      }   	
	 	}
	 else if(tipe=="4"){
		 String query = "select * from tbl_soal where paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";


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
				 section.add(s);
			 }while(cursor.moveToNext());

		 }
	 }
	 else if(tipe=="5"){
		 String query = "select * from tbl_soal where paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";


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
				 section.add(s);
			 }while(cursor.moveToNext());

		 }
	 }
	 else if(tipe=="6"){
		 String query = "select * from tbl_soal where paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";


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
				 section.add(s);
			 }while(cursor.moveToNext());

		 }
	 }
	 else if(tipe=="7"){
		 String query = "select * from tbl_soal where paket="+paketsoal+" and tipe="+tipe+" order by grup, random() LIMIT 20";


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
				 section.add(s);
			 }while(cursor.moveToNext());

		 }
	 }
	 return section;
 }

  
 public Cursor fetchAllPaket(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
	 try {
	 	   String myPath = DB_PATH + DB_NAME;
	 	   db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 	  }catch(SQLiteException e){

	 	 }

	  return db.query("tbl_paket", new String[] { KEY_PAKET, NAMA_PAKET },
			  null, null,null, null, null);
	 }

	@Override
	public void onCreate(SQLiteDatabase db) {
//		String sql = "create table tb_nilai (" +
//				"`no` integer primary key," +
//				"username text, " +
//				"nama_matkul text, " +
//				"tgl text null, " +
//				"jml_benar integer null, " +
//				"nilai_akhir integer null" +
//				");";

//        String sql1 = "create table tb_materi(" +
//				"no integer primary key, " +
//				"materi text null, " +
//				"nma_matkul text null" +
//				");";
	}

//	public void onCreate(SQLiteDatabase db) {
//	}

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
 }
}