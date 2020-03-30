package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.database.AppDb;
import lat.ta.ujianpemrograman.database.AppDbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static lat.ta.ujianpemrograman.Utils.isConnected;
import static lat.ta.ujianpemrograman.Utils.showMessage;

public class Repository<T> implements Callback<T> {

    protected ApiService service;
    protected AppDb database;
    protected Context context;

    public Repository(Context ctx) {
        context = ctx;
        service = ApiHelper.getInstance();
        database = AppDbHelper.getInstance(context);
    }

    protected boolean isOnline() {
        if (!isConnected(context)) {
            showMessage(context, context.getString(R.string.info_network_not_availavle));
            return false;
        }

        return true;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        showMessage(context, context.getString(R.string.info_network_error));
    }

}
