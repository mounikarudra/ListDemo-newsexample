package com.example.mouni.listdemo1;

/**
 * Created by MOUNI on 28-Jul-16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>  {

    public ImageLoader imageLoader;
    private Context context;


    //List of images and title
     public static ArrayList<ImageAndTitle> img_list;

    public CardAdapter(ArrayList<ImageAndTitle> img_list, Context context){
        super();
        //Getting all the superheroes
        this.img_list = img_list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageAndTitle superHero =  img_list.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);
        holder.textViewTitle.setText(superHero.getTitle());
        holder.textViewContent.setText(superHero.getContent());

    }

    @Override
    public int getItemCount() {
        return img_list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public NetworkImageView imageView;
        public TextView textViewTitle;
       public View view;
        public TextView textViewContent;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            textViewTitle = (TextView) itemView.findViewById(R.id.txv_row);
            textViewContent=(TextView)itemView.findViewById(R.id.tv_content);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {

                    int i=getAdapterPosition();
                    ImageAndTitle content=img_list.get(i);
                     String Content=content.getContent();
                    String imgurl=content.getImageUrl();
                    Intent intent=new Intent(context.getApplicationContext(),ActivityTwo.class);
                    intent.putExtra("content",Content);
                    intent.putExtra("imgurl",imgurl);
                    intent.putExtra("position",i);
                    context.startActivity(intent);

                }
            });
        }
    }
}