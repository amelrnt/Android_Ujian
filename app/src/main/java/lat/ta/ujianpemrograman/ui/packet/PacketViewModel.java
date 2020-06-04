package lat.ta.ujianpemrograman.ui.packet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.repository.PacketRepository;

public class PacketViewModel extends AndroidViewModel {

    private PacketRepository packetRepository;

    public PacketViewModel(@NonNull Application application) {
        super(application);
        packetRepository = new PacketRepository(application.getApplicationContext());
    }

    LiveData<List<Packet>> fetchAllPacket() {
        return packetRepository.getAllAsync(false);
    }
}
