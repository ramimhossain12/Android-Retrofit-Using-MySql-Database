package com.example.datacrudmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.Reference;
import java.util.HashMap;

import java.util.List;
import java.util.Map;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView pic;
    private  TextView name, email,gender;

    private  JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);

        ///,,,,,Gson
        Gson gson = new GsonBuilder().serializeNulls().create();




        //....for API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://192.168.0.104/demo/add/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        createPost();





    }




    private  void createPost(){


        Map<String,String> fields = new HashMap<>();
        fields.put("userId","25");
        fields.put("title","Massage");

        Call<Post> call = jsonPlaceHolderApi.createPost((Post) fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()){

                    name.setText("Code:"+ response.code());
                    email.setText("Code:"+ response.code());
                    gender.setText("Code:"+ response.code());
                    return;
                }

                Post postResponse = response.body();
                String  content ="";
                content += "Code:" +response.code() +"\n";
                content += "ID: " +postResponse.getPic() +"\n";
                content += "Name: " +postResponse.getName() +"\n";
                content += "Email: " +postResponse.getEmail() +"\n";
                content += "Gender: " +postResponse.getGender() +"\n\n";

                name.setText(content);
                email.setText(content);
                gender.setText(content);



            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                name.setText(t.getMessage());
                email.setText(t.getMessage());
                gender.setText(t.getMessage());

            }
        });
    }

    //....for   update



}