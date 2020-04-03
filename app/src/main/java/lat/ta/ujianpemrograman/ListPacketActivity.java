package lat.ta.ujianpemrograman;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.repository.PacketRepository;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class ListPacketActivity extends AppCompatActivity {

	private ViewModel viewModel;

	// TODO : tambahkan atribut dari adapter

	// TODO : Binding View Recycler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setFullScreen(getWindow());
		setContentView(R.layout.activity_listpaket);

		// TODO : 1. Instance Object dari adapter ke atribut
		// TODO : 2. Set Layout dan Adapter

		viewModel = new ViewModelProvider(this).get(ViewModel.class);
		viewModel.fetchAllPacket().observe(this, packets -> {
			// TODO : Update UI Disini
		});
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	private static class ViewModel extends AndroidViewModel {
    	private PacketRepository packetRepository;

		public ViewModel(@NonNull Application application) {
			super(application);

			packetRepository = new PacketRepository(application.getApplicationContext());
		}

		public LiveData<List<Packet>> fetchAllPacket() {
			return packetRepository.getAllAsync(false);
		}
	}
}

   

