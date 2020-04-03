package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.database.PacketDao;
import lat.ta.ujianpemrograman.model.Packet;
import retrofit2.Response;

public class PacketRepository extends Repository<Packet> {

    private static String TAG = VersionRepository.class.getSimpleName();

    private PacketDao dao;
    private MutableLiveData<List<Packet>> liveData;

    private ApiService service = ApiHelper.getInstance();

    public PacketRepository(Context context) {
        super(context);
        dao = database.getPacketDao();
    }

    public LiveData<List<Packet>> getAllAsync(boolean fromWebService) {
        if (fromWebService) {
            executor.submit(() -> {
                try {
                    Future<List<Packet>> future = getAllSync();
                    List<Packet> result = future.get();
                    liveData.postValue(result);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } else {
            getAll();
        }

        return liveData;
    }

    public void getAll() {
        executor.submit(() -> liveData.postValue(dao.getAll()));
    }

    public Future<List<Packet>> getAllSync() {
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
