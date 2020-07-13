package com.example.qlbhcdio.retrofit;

public class API {
    //
    //http://192.168.1.131:8080/
        public static String baseURL = "https://cdio3qlbh.herokuapp.com/";
        public static DataClient getData(){
            return RetrofitData.getClient(baseURL).create(DataClient.class);
        }
}
