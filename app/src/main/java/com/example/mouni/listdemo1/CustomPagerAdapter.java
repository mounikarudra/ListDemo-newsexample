package com.example.mouni.listdemo1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by MOUNI on 29-Jul-16.
 */
public class CustomPagerAdapter extends PagerAdapter {



    private List<ImageAndTitle> itemList;

    private Context context;

     int positiontemp=ActivityTwo.position-1;
    private LayoutInflater inflater;
    ImageLoader img_loader;

    public CustomPagerAdapter(Context context, List<ImageAndTitle> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override // Checks whether View is associated with Object
    // Or Object is associated with Page view i.e. view or not
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
           //position=ActivityTwo.position;


        // Get the View of the Single ViewPager Item
        View itemView = inflater.inflate(R.layout.activity_two, container, false);

        // Locate the ImageView and TextView
        NetworkImageView imageView = (NetworkImageView) itemView.findViewById(R.id.img);
        TextView textView = (TextView) itemView.findViewById(R.id.tv);

        // Get the Data Model for current Position
        ImageAndTitle imageAndTitle = itemList.get(++positiontemp);
        Log.i("mouni", "instantiateItem " + position);
//positiontemp++;
        // Set the Data for Image and Text

        //img_loader = CustomVolleyRequest.getInstance(this).getImageLoader();
        img_loader=ActivityTwo.img_loader;
        imageView.setImageUrl(imageAndTitle.getImageUrl(),img_loader);

        //imageView.setImageResource(dataModel.imageId);
        textView.setText(imageAndTitle.getContent());

        // Add viewpager_item.xml to View Pager
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //position=positiontemp-2;
        Log.i("mouni", "destroyItem " + position);
        // Remove viewpager_item.xml from ViewPager container
        container.removeView((LinearLayout) object);
    }



}
