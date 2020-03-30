package lat.ta.ujianpemrograman.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lat.ta.ujianpemrograman.model.Packet;

@Dao
public interface PacketDao {

    @Insert
    void add(Packet question);

    @Query("SELECT * FROM tbl_paket")
    List<Packet> getAll();

    @Query("DELETE FROM tbl_paket")
    void clear();

}
