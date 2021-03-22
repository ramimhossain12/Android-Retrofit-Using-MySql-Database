package com.example.datacrudmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsData extends AppCompatActivity {

    private ImageView pic;
    private TextView name, email,gender;
    private  JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_data);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);


        //....for API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104/demo/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts();


    }

    private  void  getPosts(){

        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");


        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){

                    name.setText("Code:"+ response.code());
                    email.setText("Code:"+ response.code());
                    gender.setText("Code:"+ response.code());
                    return;
                }
                List<Post > posts = response.body();

                for (Post post : posts){

                    String content ="";
                    content +=  "ID: " + post.getName() +"\n";
                    content += "Name:"+ post.getName() +"\n";
                    content += "Email:"+ post.getEmail() + "\n";
                    content += "Gender:" +post.getGender() + "\n\n";



                    name.append(content);
                    email.append(content);
                    gender.append(content);

                }



            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {


                name.setText(t.getMessage());
                email.setText(t.getMessage());
                gender.setText(t.getMessage());

            }
        });

    }
}