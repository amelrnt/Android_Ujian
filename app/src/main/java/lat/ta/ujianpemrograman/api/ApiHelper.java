package lat.ta.ujianpemrograman.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public static String URL = "192.168.43.136";

    private static ApiService instance = null;

    private ApiHelper() {
    }

    /**
     * method getInstance untuk melakukan instance dari ApiService
     * menggunakan cara atau metode singleton
     * @return instance of ApiService
     */
    public static ApiService getInstance() {
        if (instance == null) {
            String URL = "http://"+ ApiHelper.URL +"/Webserver_Android_Ujian/api/";
            instance = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }

        return instance;
    }

}
