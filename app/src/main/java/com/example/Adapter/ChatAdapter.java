package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Bean.Comunity;
import com.example.Bean.Post;
import com.example.splash.Freceive;
import com.example.splash.Login;
import com.example.splash.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Comunity> data;


    private  final int N_TYPE = 0;
    private  final int F_TYPE = 1;

    private  int Max_num = 15;
    private Boolean isfootview =true;

    public ChatAdapter(Context context,List<Comunity> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View o_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item,parent,false);
        View f_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item,parent,false);
        if (viewType == F_TYPE){
            return new ChatAdapter.RecyclerViewHolder(f_view,F_TYPE);
        }else{
            return new ChatAdapter.RecyclerViewHolder(o_view,N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isfootview && (getItemViewType(position))==F_TYPE){
            final ChatAdapter.RecyclerViewHolder recyclerViewHolder = (ChatAdapter.RecyclerViewHolder) holder;
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
            final ChatAdapter.RecyclerViewHolder recyclerViewHolder = (ChatAdapter.RecyclerViewHolder) holder;
            final Comunity Comunity = data.get(position);

            recyclerViewHolder.c_name.setText(Comunity.getName());
            recyclerViewHolder.c_info.setText(Comunity.getInfo());
//            recyclerViewHolder.c_user.setText(Comunity.getUser().getUsername());
            recyclerViewHolder.c_time.setText(Comunity.getCreatedAt());
            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerViewHolder.getAdapterPosition();
                    Intent in = new Intent(context, Freceive.class);
                    in.putExtra("c_name",Comunity.getName());
                    in.putExtra("c_info",Comunity.getInfo());
//                    in.putExtra("c_user",Comunity.getUser().getUsername());
                    in.putExtra("id",data.get(position).getObjectId());
                    context.startActivity(in);

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
        public TextView c_name,c_info,c_time,c_user;
        public TextView Loading;

        public RecyclerViewHolder(View itemView,int view_type) {
            super(itemView);
            if(view_type ==N_TYPE){
                c_name = itemView.findViewById(R.id.c_name);
                c_info = itemView.findViewById(R.id.c_info);
//                c_user = itemView.findViewById(R.id.c_user);
                c_time = itemView.findViewById(R.id.c_time);
            }else if(view_type == F_TYPE){
                Loading = itemView.findViewById(R.id.footText);
            }
        }
    }
}
