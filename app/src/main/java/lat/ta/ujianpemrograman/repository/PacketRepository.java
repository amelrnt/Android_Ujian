package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.model.Packet;

public class PacketRepository extends Repository<Packet> {

    private String TAG = VersionRepository.class.getSimpleName();
    private ApiService service = ApiHelper.getInstance();

    public PacketRepository(Context context) {
        super(context);
    }
}
