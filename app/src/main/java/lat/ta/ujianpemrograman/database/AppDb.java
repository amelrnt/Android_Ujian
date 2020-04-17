package lat.ta.ujianpemrograman.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lat.ta.ujianpemrograman.model.Nilai;
import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.Version;

@Database(entities = { Version.class, Packet.class, Question.class, Nilai.class },
        version = 2, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract VersionDao getVersionDao();

    public abstract PacketDao getPacketDao();

    public abstract QuestionDao getQuestionDao();

}
