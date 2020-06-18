package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "nilai",
        primaryKeys = {"kategori", "paket"})
public class ScoreModel {

    @ColumnInfo(name = "paket")
    private int packet;

    @ColumnInfo(name = "nilai")
    private float score;

    @ColumnInfo(name = "kategori")
    private int category;

    @ColumnInfo(name = "jumlah_soal")
    private int questions;

    @ColumnInfo(name = "jawaban_benar")
    private int corrects;

    @ColumnInfo(name = "datetime")
    private String dateTime;

    @Ignore
    private float point;

    public int getPacket() {
        return packet;
    }

    public void setPacket(int packet) {
        this.packet = packet;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        point = 100f / questions;
        this.questions = questions;
    }

    public int getCorrects() {
        return corrects;
    }

    public void setCorrects(int corrects) {
        score = point * corrects;
        this.corrects = corrects;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
