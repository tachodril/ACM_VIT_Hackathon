package nitjsr.team.in.ragnarok.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import nitjsr.team.in.ragnarok.Activity.WebViewActivity;
import nitjsr.team.in.ragnarok.Carousel.SliderAdapter;
import nitjsr.team.in.ragnarok.R;

public class FeaturedFragment extends Fragment {

    ViewPager viewPager;
    TabLayout indicator;
    List<Integer> sliderImages;
    List<String> sliderText;
    String sliderText1 = "";
    String sliderText2 = "";
    String sliderText4 = "";
    String sliderText5 = "";

    LinearLayout mItemFinder, mShoppingList, mPriceChecker;
    RelativeLayout mCaptureShoppingList;

    LinearLayout appliances, bath, lighting, tools, flooring, outdoor;
    Button navigateBtn, storeMap;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_featured, container, false);

        appliances = (LinearLayout) root.findViewById(R.id.appliances);
        bath = (LinearLayout) root.findViewById(R.id.bath);
        lighting = (LinearLayout) root.findViewById(R.id.lighting);
        tools = (LinearLayout) root.findViewById(R.id.tools);
        flooring = (LinearLayout) root.findViewById(R.id.flooring);
        outdoor = (LinearLayout) root.findViewById(R.id.outdoor);

        //carousel
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        indicator = (TabLayout) root.findViewById(R.id.indicator);

        setCarouselViewPager(); //to implement carousel using viewpager

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.reliancedigital.in/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Reliance Ltd.");
                startActivity(intent);
            }
        });

        bath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.bigbazaar.com/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Big Bazaar");
                startActivity(intent);
            }
        });

        lighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.vmartretail.com/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "V Mart");
                startActivity(intent);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.walmart.com/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Walmart");
                startActivity(intent);
            }
        });

        flooring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.pantaloons.com/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Pantaloons");
                startActivity(intent);
            }
        });

        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.westside.com/";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Westside");
                startActivity(intent);
            }
        });

        return root;
    }

    //carousel/slider implementation function
    public void setCarouselViewPager() {

        sliderText = new ArrayList<>();
        sliderText.add(sliderText1);
        sliderText.add(sliderText2);
        sliderText.add(sliderText4);
        sliderText.add(sliderText5);

        sliderImages = new ArrayList<Integer>();
        sliderImages.add(R.drawable.image_1);
        sliderImages.add(R.drawable.big_w);
        sliderImages.add(R.drawable.walmart_img);
        sliderImages.add(R.drawable.smart_home);

        viewPager.setAdapter(new SliderAdapter(getActivity(), sliderImages, sliderText));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 5000, 6000);
    }

    //carousel image auto-slider
    public class SliderTimer extends TimerTask {
        @Override
        public void run() {

            if (getActivity() != null) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < sliderImages.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            } else
                return;
            ;
        }
    }


}