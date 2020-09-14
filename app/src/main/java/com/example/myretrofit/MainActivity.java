package com.example.myretrofit;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myretrofit.Adapter.MyAdapter;
import com.example.myretrofit.Interface.JsonPlaceHolderApi;
import com.example.myretrofit.Model.Post;
import com.example.myretrofit.ViewModel.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapterRecycler;
    ActivityViewModel activityViewModel;
    List<Post> posts=new ArrayList<>();
    String TAG="Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");


        recyclerView=findViewById(R.id.recyclerView);


        activityViewModel= ViewModelProviders.of(this).get(ActivityViewModel.class);
        activityViewModel.init();
        adapterRecycler=new MyAdapter(MainActivity.this,posts);
        activityViewModel.getLiveData().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts)
            {
                MainActivity.this.posts.clear();
                MainActivity.this.posts.addAll(posts);
                adapterRecycler.notifyDataSetChanged();

            }
        });
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setHasFixedSize(true);
        int col=2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,col));
        setData();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume:");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop:");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy:");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart:");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState:");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState:");
    }




    public void setData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonApiHolder = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonApiHolder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TAG", "onResponse: ");
                    return;
                }

                posts.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Log.i("TAG", "onFailure: ");
            }
        });

    }








     /*
     int id;
     int uid;
      String title;
     String txt;
     DataPost dataPost;
     ListView list;
     ArrayList<String> arrayList;
     MyAdapter myAdapter;
     String context;
    TextView idTxt, userIdTxt, titleTxt, bodyTxt;

      list = findViewById(R.id.listView);
     myAdapter = new MyAdapter(this);
     list.setAdapter(myAdapter);


          final DataPost dataPost=new DataPost();
          myAdapter.notifyDataSetChanged(); //any changes in the adapter gets update and render.





               for (Post post : posts) {



                    context = "";
                    context += "ID: " + post.getId() + "\n";
                    context += "User Id: " + post.getUserId() + "\n";
                    context += "Title: " + post.getTitle() + "\n";
                    context += "Text: " + post.getBodyText() + "\n \n";}
                    if (!context.equals(null)) {
                        arrayList.add(context);
                    } else {
                        Toast.makeText(MainActivity.this, "" + context, Toast.LENGTH_SHORT).show();
                    }


                    //arrayList.add(posts);
                   Toast.makeText(MainActivity.this, "" + context, Toast.LENGTH_SHORT).show();
                    /*idTxt.append("ID: "+ post.getId());
                   userIdTxt.append("User Id: "+post.getUserId());
                   titleTxt.append("Text: "+post.getBodyText());
                   bodyTxt.append("Text: "+post.getBodyText());
                   String context="";
                    context += "ID: "+ post.getId() +"\n";
                    context += "User Id: "+post.getUserId() +"\n";
                    context += "Title: "+post.getTitle()+"\n";
                    context += "Text: "+post.getBodyText()+"\n \n";
                   bodyTxt.append(context);*/
    // Toast.makeText(MainActivity.this, ""+context, Toast.LENGTH_SHORT).show();
    //Toast.makeText(MainActivity.this, "sssfull", Toast.LENGTH_SHORT).show();*/

  /*   class MyAdapter extends BaseAdapter {
        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            if ((posts == null)) {
                Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
            }
            return posts.size();
        }

        @Override
        public Object getItem(int i) {
            return getItemId(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(context).inflate(R.layout.layout, viewGroup, false);
            idTxt = v.findViewById(R.id.idDisp);
            userIdTxt = v.findViewById(R.id.userIdDisp);
            titleTxt = v.findViewById(R.id.titleDisp);
            bodyTxt = v.findViewById(R.id.bodyTextDisp);

            Post post = posts.get(i);
            idTxt.append("ID: " + post.getId());
            userIdTxt.append("User Id: " + post.getUserId());
            titleTxt.append("Title: " + post.getTitle());
            bodyTxt.append("Text: " + post.getBodyText());
            return v;
        }
    } */

}