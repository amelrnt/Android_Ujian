package lat.ta.ujianpemrograman.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private static String TAG = ApiHelper.class.getSimpleName();

    private static ApiService instance = null;

    private ApiHelper() {
    }

    /**
     * interface ResponseListener untuk melakukan
     * pengolahan data jika telah terdapat response dari web service.
     */
    public interface ResponseListener {
        void onResponse(Response response);
    }

    /**
     * Class EnQueue merupakan helper untuk menambahkan enqueue pada service
     * yang ada di class [Repo].
     * @param <T> Generic Class yang menerima Response.
     */
    public static class EnQueue<T> implements Callback<Response<T>> {

        private ResponseListener responseListener;

        public EnQueue(ResponseListener responseListener) {
            this.responseListener = responseListener;
        }

        @Override
        public void onResponse(Call<Response<T>> call, retrofit2.Response<Response<T>> response) {
            Log.i(TAG, "onResponse: *OK");
            Log.i(TAG, "onResponse: "+ response.message());

            if (response.body() != null) {
                responseListener.onResponse(response.body());
            }
        }

        @Override
        public void onFailure(Call<Response<T>> call, Throwable t) {
            Log.e(TAG, "onFailure: Failure Get Data");
            Log.e(TAG, "onFailure: " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * method getInstance untuk melakukan instance dari ApiService
     * menggunakan cara atau metode singleton
     * @return instance of ApiService
     */
    public static ApiService getInstance() {
        if (instance == null) {
            String URL = "http://192.168.56.1/Webserver_Android_Ujian/index.php/wservice/";

            instance = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }

        return instance;
    }

}
