package lat.ta.ujianpemrograman;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.repository.PacketRepository;
import lat.ta.ujianpemrograman.ui.Adapter;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class ListPacketActivity extends AppCompatActivity implements
	Adapter.OnBinding<Packet> {

	private static String TAG = ListPacketActivity.class.getSimpleName();

    private Adapter<Packet> adapter;

	@BindView(R.id.listSoal)
	RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setFullScreen(getWindow());
		setContentView(R.layout.activity_listpaket);
		ButterKnife.bind(this);

		adapter = new Adapter<>(this, R.layout.item_button, null);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
		viewModel.fetchAllPacket()
				 .observe(this, packets -> {
					 Log.i(TAG, "onCreate: Size Paket="+ packets.size());
					 adapter.refreshData(packets);
				 });
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onBindViewHolder(View itemView, Packet obj) {
        TextView tv = itemView.findViewById(R.id.btn);
		tv.setText(obj.getName());
		tv.setOnClickListener(view -> {
            Intent intent = new Intent(ListPacketActivity.this,
                    PilihActionActivity.class);
            intent.putExtra(PilihActionActivity.EXTRA_ID_PACKET, obj.getId());
            startActivity(intent);
        });
	}

	static class ViewModel extends AndroidViewModel {
    	private PacketRepository packetRepository;

		public ViewModel(@NonNull Application application) {
			super(application);
			packetRepository = new PacketRepository(application.getApplicationContext());
		}

		LiveData<List<Packet>> fetchAllPacket() {
			return packetRepository.getAllAsync(false);
		}
	}
}

   

