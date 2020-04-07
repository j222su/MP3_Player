package com.example.mp3player;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    int layout;
    public ArrayList<MusicData>list;

    public MusicAdapter(int layout, ArrayList<MusicData> list) {
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        MusicViewHolder musicViewHolder=new MusicViewHolder(view);
        return musicViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (list!=null)? list.size() : 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAlbum;
        TextView tvSinger;
        TextView tvTitle;
        TextView tvTotal;
        ImageButton btnLike;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum=itemView.findViewById(R.id.imgAlbum);
            tvSinger=itemView.findViewById(R.id.tvSinger);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvTotal=itemView.findViewById(R.id.tvTotal);
            btnLike=itemView.findViewById(R.id.btnLike);
        }
    }
}
