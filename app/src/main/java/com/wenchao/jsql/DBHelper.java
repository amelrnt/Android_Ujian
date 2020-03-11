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
	
	  return db.query("tbl_paket", new String[] { KEY_PAKET, NAMA_PAKET }, null, null,null, null, null);  
	 }

	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub
		String sql = "create table tb_nilai(no integer primary key,username text null, nama_matkul text null, tgl text null, jml_benar integer null, nilai_akhir integer null);";
		Log.d("Data", "onCreate: " + sql);
		db.execSQL(sql);
		sql = "INSERT INTO tb_nilai (no,username, nama_matkul, tgl, jml_benar, nilai_akhir) VALUES " +
				"('1','null','Pemrograman Berbasis Website', '0000-00-00', '0','0')," +
				"('2','null','Pemrograman Berbasis Mobile', '0000-00-00', '0','0')," +
				"('3','null','Pemrograman Jaringan Komputer', '0000-00-00', '0','0')," +
				"('4','null','Pemrograman Algoritma', '0000-00-00', '0','0')," +
				"('5','null','Pemrograman Basis Data', '0000-00-00', '0','0')," +
				"('6','null','Pemrograman Berorientasi Object', '0000-00-00', '0','0')," +
				"('7','null','Pemrograman Berbasis GUI', '0000-00-00', '0','0');";
        String sql1 = "create table tb_materi(no integer primary key, materi text null, nma_matkul text null);";
        Log.d("Data", "onCreate: " + sql1);
        db.execSQL(sql1);
        sql1 = "INSERT INTO tb_materi (no, materi, nma_matkul) VALUES "+
                "('1','PENGERTIAN WEB \n" +
				" Web merupakan media informasi berbasis jaringan komuter yang dapat diakses di mana saja " +
                "kapan saja dengan biaya relative murah. Web merupakan bentuk implementasi dari bahasa pemrograman web (Web Programming)." +
                "Sejarah perkembangan bahasa pemrograman web diawali dengan munculnya HTML (HyperText Markup Language)," +
                "yang kemudian dikembangkan dengan munculnya CSS (Cascading Style Sheet) yang betujuan untuk memperindah tampilan website" +
                "dengan perintah-perintah atau kode bahasa pemrograman css." +
                "Pemrograman web bisa diartikan sebagai proses pembuatan program dalam bentuk web" +
                "dengan perintah-perintah atau kode yang terstruktur dan hasilnya akan ditampilkan dalam bentuk web melalui web browser. " +
                "Beberapa bahasa pemrograman web berbasis server (Server-Side) mulai muncul dan dikembangkan oleh beberapa perusahaan" +
                "perangkat lunak, seperti: ASP (Active Server Pages) oleh perusahaan Microsoft Corporation, JSP (Java Server Pages)" +
                "oleh perusahaan Sun Microsystem, dan CGI (Common Gateway Interface) oleh perusahaan UNIX yang sekarang diadopsi " +
                "oleh NCSA (National Central for Supercomputing Application) web server. " +
                "Berikut macam-macam bahasa pemrograman web yang lebih lengkap " +
                ": Bahasa Pemrograman Keterangan HyperText Markup Language (HTML) adalah sebuah bahasa markup yang digunakan " +
                "untuk membuat sebuah halaman web dan menampilkan berbagai informasi di dalam sebuah browser Internet. " +
                "HTML HTML saat ini merupakan standar Internet yang didefinisikan dan dikendalikan penggunaannya " +
                "oleh World Wide Web Consortium (W3C). HTML berupa kode-kode tag yang menginstruksikan browser untuk menghasilkan tampilan " +
                "sesuai dengan yang diinginkan. Sebuah file yang merupakan file HTML dapat dibuka dengan menggunakan browser web " +
                "seperti Mozilla Firefox, Microsoft Internet Explorer dll. " +
                "Hypertext Preprocessor (PHP) adalah bahasa pemrograman script yang paling banyak dipakai saat ini. " +
                "PHP PHP pertama kali dibuat oleh Rasmus Lerdorf pada tahun 1995. Pada waktu itu PHP masih bernama FI (Form Interpreted), " +
                "yang wujudnya berupa sekumpulan script yang digunakan untuk mengolah data form dari web. " +
                "PHP banyak dipakai untuk membuat situs web yang dinamis, walaupun tidak tertutup kemungkinan digunakan untuk pemakaian lain. " +
                "PHP biasanya berjalan pada sistem operasi linux (PHP juga bisa dijalankan dengan hosting windows). " +
                "ASP adalah singkatan dari Active Server Pages yang merupakan salah satu bahasa pemograman web untuk menciptakan " +
                "halaman web yang dinamis. ASP ASP merupakan salah satu produk teknologi yang disediakan oleh Microsoft. " +
                "ASP bekerja pada web server dan merupakan server side scripting. \n" +
				"\n"+
				"Cara kerja pemrograman web\n" +
				"Sebelum menjelaskan cara kerja pemrograman web, sebaiknya anda mengetahui tentang HTML bahwa HTML bukan bahasa pemrograman tapi adalah markup language (bahasa penandaan yang terdiri dari TAG). HTML hanya mendeskripsikan bahwa bagian tertentu dalam sebuah halaman web adalah isi yang harus ditampilkan oleh browser dengan cara tertentu.\n" +
				"Bagaimana cara kerja pemrograman web? Secara sederhana pemrograman web hanya dilakukan dengan menyisipkan perintah diantara TAG HTML. Misal :\n" +
				"\n"+
				"<html>\n" +
				"      <head>\n" +
				"          <title>Contoh</title>\n" +
				"      </head>\n" +
				"  <body>\n" +
				"       <?php\n" +
				"             echo date(\"d/m/Y\");\n" +
				"       ?>\n" +
				"  </body>\n" +
				"</html> \n" +
				"\n"+
				"Pada saat kita mengakses halaman tersebut, web server langsung merespon apa yang kita minta dengan melakukan proses parsing (membaca isi halaman baris perbaris) dan jika menemukan baris perintah bahasa pemrograman maka web server akan mengeksekusi/menjalankan perintah tersebut dan setelah semua selesai baru akan mengirimkannya ke browser. Contoh diatas web server akan mengirimkan hasil eksekusi baris perintah echo date(\"d/m/Y\") dalam bentuk tanggal dinamis yang diangap sebagai bagian dari HTML yang bersifat statis.'," +
                "'Pemrograman Berbasis Website'),"+
                "('2','Pemrograman Mobile adalah pemrograman yang ditujukan untuk pembuatan aplikasi diperangkat mobile yang dapat kita buat dengan menggunakan Java. Keterbatasan yang sangat umum dalam bahasa pemrograman untuk peralatan mobile adalah kendala dalan segala dalam hal sumber daya, seperti ukuran layar, memori, CPU, penyimpanan dan cara menginput data. Perbedaan tampilan juga disebabkan adanya \n" +
				"perbedaan hardware dan API yang di gunakan. Berikut macam-macam Mobile Programming untuk ponsel : \n" +
				"-          J2ME\n" +
				"-          C++\n" +
				"-          Objective C\n" +
				"-          C#.\n" +
				"-          Javafx\n" +
				"-          PHP','Pemrograman Berbasis Mobile'),"+
                "('3','DEFINISI JARINGAN\n" +
				"Jairngan komputer adalah seperangkat komputer otonom yang saling\n" +
				"terhubung yang secara eksplisit terlihat, sehingga dapat saling bertukar\n" +
				"informasi/data, dan berbagai (share) satu dengan yang lainnya. [Tanenbaum\n" +
				"1996]\n" +
				"IP ADDRESS, DNS DAN PORT\n" +
				"Setiap mesin dalam jaringana disebut “node”. Node dapat berupa\n" +
				"komputer, printer, router, bridge, gateway dan lain-lain. Node-node yang\n" +
				"berupa koputer yang sangat fungsional “host”. Setiap node memiliki alamat\n" +
				"tertentu yang terdiri dari kumpulan byte yang unik. Alamat yang dipakai harus\n" +
				"berbeda-beda. Setiap host selalu memeriksa setiap alamat yang ada agar\n" +
				"jangan sampai sama Jika suatu alamat sudah dipakai maka akan secara\n" +
				"random membuat alamat yang baru sampai alamatnya tidak sama dengan yang\n" +
				"lain dalam satu jaringan yang sama. Hal ini diatur oleh DHCP (Dynamic Host\n" +
				"Control Protocol)\n" +
				"Kini, alamat jaringan dapat juga menggunakan “nama” sehingga\n" +
				"manusia dapat lebih mudah mengingatnya. Namun pada kenyataannya\n" +
				"“nama” alamat juga akan diubah dahulu menjadi alamat byte pada saat\n" +
				"pemrosesan. Hal ini diatur oleh DNS (Domain Name System) Satu atau lebih\n" +
				"nama harus mewakili satu alamat byte fisik. Hal ini akan diatur oleh Server\n" +
				"Alias pada Server. Misalnya : localhost dan antonie.com itu sama-sama\n" +
				"menunjuk pada satu alamat yang sama yaitu 127.0.0.1\n" +
				"Semua servis-servis yang ada pada jaringan komputer biasanya juga\n" +
				"dipisahkan masing-masing dengan menggunakan port. Misalnya antara servis\n" +
				"FTP dan HTTP menggunakan port yang berbeda-beda.','Pemrograman Jaringan Komputer'),"+
                "('4','Pengertian Basis Data\n" +
				"Basis data adalah kumpulan file-file yang mempunyai kaitan antara satu file dengan file lain sehingga membentuk suatu bangunan data untuk menginformasikan suatu perusahaan atau instansi dalam batasan tertentu\n" +
				"Istilah-istilah Basis data\n" +
				"Beberapa hal yang termaksud unsur-unsur dari basis data adalah sebagai berikut:\n" +
				"Entititas\n" +
				"Entititas adalah orang, tempat, kejadian atau konsep yang informasinya direkam. Pada bidang kesehatan Entity adalah Pasien, Dokter, Kamar.\n" +
				"Field\n" +
				"Setiap entity mempunyai atribut atau sebutan untuk mewakili suatu entity. Seorang siswa dapat dilihat dari atributnya misalnya, NIM, Nama_siswa, Alamat.\n" +
				"Record\n" +
				"Record adalah kumpulan isi elemen data (atribut) yang saling berhubungan menginformasikan tentang suatu entity secara lengkap.\n" +
				"Contoh Kumpulan atribut NIP, Nama, dan alamat berisikan “01001245566”, Sanusi, Jl. Hati suci No 2 Kupang.\n" +
				"Data Value\n\b" +
				"Merupakan data aktual atau infomasi yang disimpan ditiap data elemen. Isi atribut disebut nilai data.\n" +
				"Kunci Elemen Data ( Key Data Element )\n" +
				"Tanda pengenal yang secara unik mengidentifikasikan entitas dari suatu kumpulan entitas.\n" +
				"Contoh Entitas Mahasiswa yang mempunyai atribut-atribut npm, nama, alamat, tanggal lahir menggunakan Kunci Elemen Data npm.','Pemrograman Basisdata'),"+
                "('5','Apakah Algoritma itu?\n" +
				"\uF06C Algorism \uF0E0 algorithm \uF06C nama penulis buku Arab yaitu Abu Ja’far Muhammad ibnu Musa Al-Khuwarizmi \uF06C Algoritma adalah: \uF06C penyusunaan aspek proses logika dari suatu pemecahan masalah tanpa melihat karakteristik bahasa pemrograman yang akan digunakan \uF06C urutan notasi logika yang merupakan hasil analisis dan rancangan sistematik dari strategi pemecahan masalah, untuk menggambarkan urutan langkah kerja yang jika dikerjakan akan membawa ke tujuannya. \uF06C urutan logika langkah kerja untuk menyelesaikan suatu masalah. ','Pemrograman Algoritma'),"+
                "('6','PEMOGRAMAN BERORIENTASI OBJEK\n" +
				"Konsep utama dalam pemrograman berorientasi objek. Segala sesuatu adalah OBJEK. Objek adalah reseprentasi sebuah item, unit atau entitas individu, dapat diidentifikasi.\n" +
				"*) Contoh : real atau abstrak\n" +
				"• Tangible : mobil, printer\n" +
				"• Peranan : pekerja, boss\n" +
				"• Kejadian : penerbangan\n" +
				"• Interaksi : kontrak, penjualan\n" +
				"• Spesifikasi : bentuk\n" +
				"OBJEK = Atribut + Operasi\n" +
				"Objek merupakan data dalam sistem. Dimanipulasi oleh operasi. Dalam implementasi, atribut berupa objek lain, atau rujukan ke objek lain. Sering disebut methods. Dapat memanipulasi atribut dalam sebuah kelas. Dapat memanggil operasi dalam kelas yang lain. Mengirim dan menerima informasi atau pesan sebagai parameter.\n" +
				"*) Contoh : \n" +
				"\uF0A7 Objek memiliki nama , misal : kuda\n" +
				"\uF0A7 Objek memiliki atribut yang mengidentifikasikan keadaan (states) , misal : warna, berat, jenis kelamin\n" +
				"\uF0A7 Objek memiliki operasi yang menyatakan apa yang dilakukan oleh objek , misal : menggonggong, berlari, berbaring\n" +
				"\n" +
				"Class adalah sekumpulan objek. Objek yang sama.\n" +
				"Semua objek dalam suatu kelas memiliki atribut dan operasi yang sama, tetapi nilai atribut dapat berbeda. Suatu objek tertentu merupakan INSTANCE suatu kelas. Dunia terbuat atau tersusun atas objek. Ita tahu tentang objek (attributes). Kita dapat melakukan suatu pada objek (operations).\n" +
				"• Kadang melakukan suatu pada objek mengubah atributnya. Objek terbuat dari objek yang lebih kecil\n" +
				"• Kita dapat mengerti objek besar dengan mengerti bagian-bagian pembentuknya.\n" +
				"Objek bekerjasama dengan objek lain untuk mencapai suatu maksud / tujuan.\n" +
				"Abstraksi adalah cara kita melihat suatu sistem dalam bentuk yang lebih sederhana, yaitu sebagai suatu kumpulan subsistem (object) yang saling berinteraksi.\n" +
				"Modularity adalah sifat objek = modular. Objek dapat ditulis dan dimantain terpisah (independen) dari objek lain. Contoh : Mobil adalah sekumpulan sistem pengapian, sistem kemudi, sistem pengereman.\n" +
				"Enkap – sulasi adalah suatu teknik dimana data dibuat dalam suatu paket beserta dengan fungsi yang bersesuaian\n" +
				"Antarmuka ke objek didefinisikan sedemikain rupa sehingga memperlihatkan sekecil mungkin sebagaimana objek bekerja. Mekanisme menyembunyikan suatu proses dalam sistem untuk menghindari interfensi dan menyederhanakan pengguna sistem itu sendiri. Contoh : tombol on / of / pengaturan suhu atau AC.\n" +
				"Inhertance / pewarisan adalah mekanisme *) mewarisi / menurunkan karateristik ke objek lain.\n" +
				"*) Contoh : \n" +
				"Bicycle :\n" +
				"\uF0DE Mountain bike\n" +
				"\uF0DE Racing bike\n" +
				"\uF0DE Tandem bike\n" +
				"\n" +
				"Polimorfisme adalah POLI + MORPHOS\n" +
				"• Memiliki banyak bentuk (rupa)\n" +
				"Kemampuan objek-objek yang berbeda untuk memberi respons terhadap permintaan yang sa ma.\n" +
				"• Sesuai dengan cara masing-masing objek\n" +
				"*) Contoh :\n" +
				"\uF0BE Segitiga luas\n" +
				"\uF0BE Lingkaran luas\n" +
				"\uF0BE Bujursangkar luas','Pemrograman Berorientasi Objek'),"+
                "('7','A.      Pengertian GUI Pada JAVA \n" +
				"Graphic User Interface (GUI) adalah pemrograman dengan bahasa Java yang dibuat menggunakan aplikasi yang berbasiskan GUI. Tujuannya adalah menambahkan beberapa komponen yang tidak bisa dibuat dalam basis text.\n" +
				"Komponen-konponen tersebut bisa berupa tombol, gambar, dll. Tujuannya adalah untuk memudahkan user menggunakan program yang dibuat tersebut.\n" +
				"Kalau dilihat pengertian tentang GUI secara umum adalah Interaksi yang dapat dilaksanakan oleh user melalui menu dan icon yang diperlihatkan dalam modus grafik. Contoh implementasi GUI-based shell ini adalah pada sistem operasi Microsoft Windows.\n" +
				"\n" +
				"B.       Jenis-Jenis Gui\n" +
				"Diantara jenis-jenis gui di java adalah:\n" +
				"   a)   AWT (Abstract Window Toolkit) \n" +
				"AWT (Abstract Window Toolkit) adalah GUI Toolkit pertama pada bahasa pemrograman Java, sayang-nya AWT ini sangat-sangat kekurangan komponen yang biasa digunakan untuk membangun sebuah aplikasi desktop secara lengkap (komponen tabel saja tidak ada ) Terlepas dari kurang-nya komponen GUI yang terdapat pada AWT (Abstract Window Toolkit), aplikasi yang dibangun menggunakan AWT (Abstract Window Toolkit) akan tampak seperti aplikasi native. Maksudnya yaitu, jika aplikasi yang dibangun menggunakan AWT (Abstract Window Toolkit) ini dijalankan pada Sistem Operasi Windows. Maka aplikasi ini akan terlihat seperti aplikasi Windows pada umum-nya, dan begitu juga jika dijalankan pada Sistem Operasi Mac ataupun GNU/Linux. Kenapa ini bisa terjadi, karena AWT (Abstract Window Toolkit) ini benar-benar memanggil native subrutin untuk menggambar setiap komponen-nya ke layar.\n" +
				"\n" +
				"    b)  SWT (Standart Widget Tookit) \n" +
				"SWT (Standart Widget Toolkit) adalah sebuah GUI Toolkit yang dikeluaran oleh IBM sebagai alternatif dari AWT/Java Swing milik SUN Microsystem. SWT terdapat pada package java.swt. package java.swt berisis komponen-komponen GUI yang bersifat platform sistem operasi.\n" +
				"Tampilan dari java.swt menyesuaikan dengan sistem operasi sehingga mengakibatkan dependensi sistem. Namun performa dari SWT ini sama dengan swing. Yang membedakan antara SWT (Standart Widget Toolkit) dan AWT/Java Swing adalah SWT ini benar-benar mengakses native GUI library yang terdapat pada Sistem Operasi melalui JNI (Java Native Interface). \n" +
				"    c)  SWING \n" +
				"Java Swing adalah librari java yang digunkan untuk menciptakan Grafik User Interface (GUI). Dengan Java Swing kita dapat membuat user interface yang cross platform atau OS independent. Artinya user interface yang kita buat dapat dijalankan pada system operasi apa saja (OS yang suport Java) dengan tampilan yang relative sama. Bahkan kita dapat membuat user interface yang menyerupai Windows XP, Mac OS atau Linux tanpa tergantung dari OS yang kita gunakan. SWING, adalah salah satu bagian dari Java Foundation Classes (JFC). Pada JFC ini juga terdapat fasilitas untuk menambahkan Rich Graphic Functionality.\n','Pemrograman GUI');";

		db.execSQL(sql);
        db.execSQL(sql1);

	}

//	public void onCreate(SQLiteDatabase db) {
//	}

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
	     
 }
}