package lat.ta.ujianpemrograman.ui.packet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.ui.ActionActivity;
import lat.ta.ujianpemrograman.ui.Adapter;

import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;

public class PacketActivity extends AppCompatActivity implements Adapter.OnBinding<Packet> {

	private static String TAG = PacketActivity.class.getSimpleName();

    private Adapter<Packet> adapter;

	@BindView(R.id.listSoal) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setFullScreen(getWindow());
		setContentView(R.layout.activity_packet);
		ButterKnife.bind(this);

		adapter = new Adapter<>(this, R.layout.item_button, null);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

        PacketViewModel viewModel = new ViewModelProvider(this).get(PacketViewModel.class);
		viewModel.fetchAllPacket()
				 .observe(this, packets -> {
					 Log.i(TAG, "onCreate: Size Paket="+ packets.size());
					 adapter.refreshData(packets);
				 });
    }

	@Override
	public void onBindViewHolder(View itemView, Packet obj) {
        TextView tv = itemView.findViewById(R.id.btn);
		tv.setText(obj.getName());
		tv.setOnClickListener(view -> {
            Intent intent = new Intent(PacketActivity.this, ActionActivity.class);
            intent.putExtra(ActionActivity.EXTRA_ID_PACKET, obj.getId());
            startActivity(intent);
        });
	}
}