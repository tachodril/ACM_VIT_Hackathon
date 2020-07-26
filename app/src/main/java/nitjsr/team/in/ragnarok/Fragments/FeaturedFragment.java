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
    String sliderText1 = "Hi";
    String sliderText2 = "This";
    String sliderText4 = "is";
    String sliderText5 = "Me";

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
                String url = "https://www.lowes.com/c/Appliances?int_cmp=Home%3AA2%3AMajorAppliances%3AOther%3APC_Appliances";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Appliances");
                startActivity(intent);
            }
        });

        bath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.lowes.com/l/bath-event.html?int_cmp=Home%3AA2%3AFashionFixtures%3AOther%3APC_Bath";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Bath");
                startActivity(intent);
            }
        });

        lighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.lowes.com/c/Lighting-ceiling-fans?int_cmp=Home%3AA2%3ALighting%3APct_Off%3APC_Lighting";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Lighting");
                startActivity(intent);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.lowes.com/c/Tools?int_cmp=Home%3AA2%3AToolsHardware%3AOther%3APC_Tools";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Tools");
                startActivity(intent);
            }
        });

        flooring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.lowes.com/c/Flooring?int_cmp=Home%3AA2%3AFlooring%3AOther%3APC_Flooring";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Flooring");
                startActivity(intent);
            }
        });

        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.lowes.com/l/Outdoor-tools-equipment-Outdoors?int_cmp=Home%3AA2%3AOutdoors%3AOther%3APC_OPE";
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "Outdoor");
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
        sliderImages.add(R.drawable.paint);
        sliderImages.add(R.drawable.image_5);
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