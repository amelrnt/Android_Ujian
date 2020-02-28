package lat.ta.ujianpemrograman;


import android.app.Activity;
public class Soal1 extends Activity {
	
	
	private String soal_id;
    private String soal;
    private String pil_a;
    private String pil_b;
    private String pil_c;
    private String pil_d;
    private String url_img;
    private String url_audio;
    private int jwban;
    private int tipe;
    private int gambar;
    private String audio,version;
     
    public Soal1() {
        super();
    }
    
    public String getSoal() {
        return soal;
    }
 
    public void setSoal(String soal) {
        this.soal = soal;
    }
   
    public String getPil_a() {
        return pil_a;
    }
 
    public void setPil_a(String pil_a) {
        this.pil_a = pil_a;
    }
 
    public String getPil_b() {
        return pil_b;
    }
 
    public void setPil_b(String pil_b) {
        this.pil_b = pil_b;
    }
 
    public String getPil_c() {
        return pil_c;
    }
 
    public void setPil_c(String pil_c) {
        this.pil_c = pil_c;
    }
    public String getPil_d() {
        return pil_d;
    }
 
    public void setPil_d(String pil_d) {
        this.pil_d = pil_d;
    }
    public int getJwban() {
        return jwban;
    }
 
    public void setJwban(int jwban) {
        this.jwban = jwban;
    }
    public int getTipe() {
        return tipe;
    }
 
    public void setTipe(int tipe) {
        this.tipe = tipe;
    }
    public int getGambar() {
        return gambar;
    }
 
    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
   
	public String getUrl_img() {
		return url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}
	public String getAudio() {
	        return audio;
	}
	 
	    public void setAudio(String audio) {
	        this.audio = audio;
	    }   
	public String getUrl_audio() {
		return url_audio;
	}

	public void setUrl_audio(String url_audio) {
		this.url_audio = url_audio;
	}
	public String getSoal_id() {
		return soal_id;
	}

	public void setSoal_id(String soal_id) {
		this.soal_id = soal_id;
	}
	
	public void set_version(String version){
		this.version = version;
	}
	
	public String get_version(){
		return version;
	}
 
}