package com.example.myretrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myretrofit.Model.Post;
import com.example.myretrofit.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context ctx;
    private List<Post> posts;
    Post post;

    public MyAdapter(Context ctx, List<Post> posts) {
        this.ctx = ctx;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(ctx).inflate(R.layout.layout,parent,false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
      try {
         post= posts.get(position);
          holder.idTxt.setText("ID:  "+String.valueOf(post.getId()));
         holder.userIdTxt.setText("User Id:  "+String.valueOf(post.getUserId()));
        holder.titleTxt.setText("Title:  "+post.toString());
        holder.bodyTxt.setText("Text:  "+post.getBodyText());
      }catch (Exception e)
      {
          Log.i("TAG", "onBindViewHolder: "+e.toString());
      }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt, userIdTxt, titleTxt, bodyTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTxt =itemView.findViewById(R.id.idDisp);
            userIdTxt = itemView.findViewById(R.id.userIdDisp);
            titleTxt = itemView.findViewById(R.id.titleDisp);
            bodyTxt = itemView.findViewById(R.id.bodyTextDisp);
        }
    }
}
