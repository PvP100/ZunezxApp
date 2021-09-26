package com.example.zunezxapp.ultis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

public class Ultis {

    public static boolean checkNetwork(Context context) {
        boolean available = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable() && info.isConnected()) {
            available = true;
        }
        return available;
    }

    @BindingAdapter("android:setImg")
    public static void setImg(ImageView view, String url) {
        if (url != null) {
            Random random = new Random();
            int randomInt = random.nextInt();
            Glide.with(view.getContext())
                    .applyDefaultRequestOptions(new RequestOptions()
                            .centerCrop()
                            .circleCrop().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .load(url + "?" + randomInt)
                    .into(view);
        } else {
//            Glide.with(view.getContext()).load(R.drawable.logo_placeholder).apply(RequestOptions.skipMemoryCacheOf(true))
//                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).circleCrop().into(view);
        }
    }
}
