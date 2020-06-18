package lat.ta.ujianpemrograman.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.ScoreModel;

@Database(entities = {Packet.class, Question.class, ScoreModel.class},
        version = 4, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract PacketDao getPacketDao();

    public abstract QuestionDao getQuestionDao();

    public abstract NilaiDao getNilaiDao();

}
