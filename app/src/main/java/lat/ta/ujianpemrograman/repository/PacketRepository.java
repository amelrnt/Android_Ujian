package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.database.PacketDao;
import lat.ta.ujianpemrograman.model.Packet;
import retrofit2.Response;

public class PacketRepository extends Repository<Packet> {

    private static String TAG = VersionRepository.class.getSimpleName();

    private PacketDao dao;
    private ApiService service = ApiHelper.getInstance();

    public PacketRepository(Context context) {
        super(context);
        dao = database.getPacketDao();
    }

    public Future<List<Packet>> getPacket() {
        if (! isOnline()) return null;

        return executor.submit(() -> {
            Response<List<Packet>> response = service.getUpdatePacket().execute();
            return response.body();
        });
    }

    public void save(List<Packet> packets) {
        dao.clear();
        for (Packet packet: packets) save(packet);
    }

    public void save(Packet packet) {
        executor.submit(() -> dao.add(packet));
    }
}
