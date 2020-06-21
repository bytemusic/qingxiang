package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Bean.Comunity;
import com.example.Bean.Post;
import com.example.splash.Login;
import com.example.splash.R;
import com.example.splash.Receive;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyCollectPushAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Post> data;


    private  final int N_TYPE = 0;
    private  final int F_TYPE = 1;

    private  int Max_num = 15;
    private Boolean isfootview =true;

    public MyCollectPushAdapter(Context context, List<Post> data){
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View o_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycollect_push,parent,false);
        View f_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item,parent,false);
        if (viewType == F_TYPE){
            return new MyCollectPushAdapter.RecyclerViewHolder(f_view,F_TYPE);
        }else{
            return new MyCollectPushAdapter.RecyclerViewHolder(o_view,N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isfootview && (getItemViewType(position))==F_TYPE){
            final MyCollectPushAdapter.RecyclerViewHolder recyclerViewHolder = (MyCollectPushAdapter.RecyclerViewHolder) holder;
            recyclerViewHolder.Loading.setText("加载中...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Max_num += 8;
                    notifyDataSetChanged();
                }
            } ,2000);{

            }


        }else {
            // this is ord_item content
            final MyCollectPushAdapter.RecyclerViewHolder recyclerViewHolder = (MyCollectPushAdapter.RecyclerViewHolder) holder;
            Post post = data.get(position);
            recyclerViewHolder.mypush_username.setText(post.getName());
            recyclerViewHolder.mypush_nickname.setText(post.getNickname());
            recyclerViewHolder.mypush_content.setText(post.getContent());
            recyclerViewHolder.mypush_time.setText(post.getCreatedAt());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ps = recyclerViewHolder.getAdapterPosition();
                    if(BmobUser.getCurrentUser(BmobUser.class) != null) {
                        Intent in = new Intent(context, Receive.class);
                        in.putExtra("id",data.get(position).getObjectId());
                        context.startActivity(in);
                    }else {
                        Toast.makeText(context,"请登录",Toast.LENGTH_LONG).show();
                        context.startActivity(new Intent(context, Login.class));
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Max_num - 1){
            return F_TYPE;
        }else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (data.size()<Max_num) {
            return data.size();
        } else {
            return Max_num;
        }
    }
    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView mypush_nickname,mypush_username,mypush_content,mypush_time;
        public TextView Loading;

        public RecyclerViewHolder(View itemView,int view_type) {
            super(itemView);
            if(view_type ==N_TYPE){
                mypush_nickname= itemView.findViewById(R.id.mypush_nickname);
                mypush_username = itemView.findViewById(R.id.mypush_username);
                mypush_content= itemView.findViewById(R.id.mypush_content);
                mypush_time = itemView.findViewById(R.id.mypush_time);

            }else if(view_type == F_TYPE){
                Loading = itemView.findViewById(R.id.footText);
            }
        }
    }
}
