package nitjsr.team.in.ragnarok.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import nitjsr.team.in.ragnarok.Activity.StoreMapActivity;
import nitjsr.team.in.ragnarok.R;

public class StartShoppingFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button startShopping;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_start_shopping, container, false);

        startShopping=root.findViewById(R.id.startShopping);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        startShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), StoreMapActivity.class));
            }
        });

        return root;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 19.6f; //This goes up to 21
        mMap.setIndoorEnabled(true);
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);

        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.store);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng sydney = new LatLng(28.567892, 77.323089);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Lowe's India")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));


        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bookcase);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf1 = new LatLng(28.568010, 77.322767);
        mMap.addMarker(new MarkerOptions().position(shelf1).title("Shelf 1")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shelf);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf2 = new LatLng(28.567813, 77.322782);
        mMap.addMarker(new MarkerOptions().position(shelf2).title("Shelf 2")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shelves);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf3 = new LatLng(28.567573, 77.323008);
        mMap.addMarker(new MarkerOptions().position(shelf3).title("Shelf 3")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bookcase);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf4 = new LatLng(28.568155, 77.322871);
        mMap.addMarker(new MarkerOptions().position(shelf4).title("Shelf 4")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shelf);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf5 = new LatLng(28.568174, 77.323079);
        mMap.addMarker(new MarkerOptions().position(shelf5).title("Shelf 5")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shelves);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf6 = new LatLng(28.568050, 77.323194);
        mMap.addMarker(new MarkerOptions().position(shelf6).title("Shelf 6")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bookcase);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf7 = new LatLng(28.567674, 77.323212);
        mMap.addMarker(new MarkerOptions().position(shelf7).title("Shelf 7")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shelf);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng shelf8 = new LatLng(28.568017, 77.322567);
        mMap.addMarker(new MarkerOptions().position(shelf8).title("Shelf 8")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
    }


}