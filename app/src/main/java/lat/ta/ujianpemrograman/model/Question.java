package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbl_soal")
public class Question {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("soal")
    @Expose
    @ColumnInfo(name = "soal")
    private String question;

    @SerializedName("pil_a")
    @Expose
    @ColumnInfo(name = "pil_a")
    private String optionA;

    @SerializedName("pil_b")
    @Expose
    @ColumnInfo(name = "pil_b")
    private String optionB;

    @SerializedName("pil_c")
    @Expose
    @ColumnInfo(name = "pil_c")
    private String optionC;

    @SerializedName("pil_d")
    @Expose
    @ColumnInfo(name = "pil_d")
    private String optionD;

    @SerializedName("jwban")
    @Expose
    @ColumnInfo(name = "jwban")
    private int correctAnswer;

    @SerializedName("kategori")
    @Expose
    @ColumnInfo(name = "kategory")
    private int category;

    @SerializedName("paket")
    @Expose
    @ColumnInfo(name = "paket")
    private int idPacket;

    public Question() {
    }

    @Ignore
    public Question(int id, String question, String optionA, String optionB, String optionC,
                    String optionD, int correctAnswer, int category, int idPacket) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.idPacket = idPacket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getIdPacket() {
        return idPacket;
    }

    public void setIdPacket(int idPacket) {
        this.idPacket = idPacket;
    }

    public String getOptions(int id) {
        return getOptions()[id];
    }

    public String[] getOptions() {
        return new String[]{
                getOptionA(), getOptionB(),
                getOptionC(), getOptionD()
        };
    }
}
