package com.example.mouni.listdemo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by MOUNI on 28-Jul-16.
 */
public class ActivityTwo extends Activity {
    NetworkImageView image;
    TextView t;
    public  static ImageLoader img_loader;
    public static int position;
    ImageAndTitle it;
    List<ImageAndTitle> list=CardAdapter.img_list;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpager);
        Intent i=getIntent();
         position=i.getIntExtra("position",0);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);
        img_loader = CustomVolleyRequest.getInstance(this).getImageLoader();
        // Create instance of PagerAdapter
        CustomPagerAdapter adapter = new CustomPagerAdapter(this, list);

        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);



        /*String content=i.getStringExtra("content");
        String imageString=i.getStringExtra("imgurl");
        int position=i.getIntExtra("position",0);

        t=(TextView)findViewById(R.id.tv);
        image=(NetworkImageView)findViewById(R.id.img);

        img_loader = CustomVolleyRequest.getInstance(this).getImageLoader();
        image.setImageUrl(list.get(position).getImageUrl(), img_loader);

        t.setText(content);
        t.setMovementMethod(new ScrollingMovementMethod());*/
    }
    }
