package com.example.mp3player;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    int layout;
    private ArrayList<MusicData> list;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
    String sSinger;
    String sTitle;
    String sAlbumImage;
    static String sDataPath;
    private static final String TAG="MP3입니다.";

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
    public void onBindViewHolder(@NonNull final MusicViewHolder holder, final int position) {
//        list=new ArrayList<MusicData>();
//        Bitmap albumImage = getAlbumImage(this, Integer.parseInt(list.get(position).getAlbumId()), 170);
//        holder.imgAlbum.setImageBitmap(albumImage);

        final String albumId=list.get(position).getAlbumId();
        holder.imgAlbum.setImageURI(Uri.parse(albumId));
        holder.tvSinger.setText(list.get(position).getArtist());
        holder.tvTitle.setText(list.get(position).getTitle());
//        Log.d(TAG, "")


        final String time=simpleDateFormat.format(list.get(position).getTotal());
        holder.tvTotal.setText(time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionNum=holder.getAdapterPosition();
                Toast.makeText(v.getContext(), positionNum+list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                sAlbumImage=list.get(position).getAlbumId();
                sSinger=list.get(position).getArtist();
                sTitle=list.get(position).getTitle();
                sDataPath=list.get(position).getDataPath();

                MainActivity.mImgAlbum.setImageURI(Uri.parse(albumId));
                MainActivity.mTvSinger.setText(sSinger);
                MainActivity.mTvTitle.setText(sTitle);
                MainActivity.mTvTotalProgress.setText(time);

                Log.d(TAG, "MusicAdapter 아이템 경로 : "+sDataPath);
                Log.d(TAG, "MusicAdapter-앨범 아이디 :"+list.get(position).getAlbumId());
            }
        });
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


//    private static final BitmapFactory.Options options = new BitmapFactory.Options();
//    private static Bitmap getAlbumImage(Context context, int album_id, int MAX_IMAGE_SIZE) {
//        // NOTE: There is in fact a 1 pixel frame in the ImageView used to
//        // display this drawable. Take it into account now, so we don't have to0
//            try {
//                fd = res.openFileDescriptor(uri, "r");
//
//
//                // Compute the closest power-of-two scale factor
//                // and pass that to sBitmapOptionsCache.inSampleSize, which will
//                // result in faster decoding and better quality
//
//                //크기를 얻어오기 위한옵션 ,
//                //inJustDecodeBounds값이 true로 설정되면 decoder가 bitmap object에 대해 메모리를 할당하지 않고, 따라서 bitmap을 반환하지도 않는다.
//                // 다만 options fields는 값이 채워지기 때문에 Load 하려는 이미지의 크기를 포함한 정보들을 얻어올 수 있다.
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeFileDescriptor(
//                        fd.getFileDescriptor(), null, options);
//                int scale = 0;
//                if (options.outHeight > MAX_IMAGE_SIZE || options.outWidth > MAX_IMAGE_SIZE) {
//                    scale = (int) Math.pow(2, (int) Math.round(Math.log(MAX_IMAGE_SIZE / (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
//                }
//                options.inJustDecodeBounds = false;
//                options.inSampleSize = scale;
//
//
//
//                Bitmap b = BitmapFactory.decodeFileDescriptor(
//                        fd.getFileDescriptor(), null, options);
//
//                if (b != null) {
//                    // finally rescale to exactly the size we need
//                    if (options.outWidth != MAX_IMAGE_SIZE || options.outHeight != MAX_IMAGE_SIZE) {
//                        Bitmap tmp = Bitmap.createScaledBitmap(b, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE, true);
//                        b.recycle();
//                        b = tmp;
//                    }
//                }
//
//                return b;
//            } catch (FileNotFoundException e) {
//            } finally {
//                try {
//                    if (fd != null)
//                        fd.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//        return null;
//    }


}
