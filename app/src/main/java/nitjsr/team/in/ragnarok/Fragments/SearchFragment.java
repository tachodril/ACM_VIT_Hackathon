package nitjsr.team.in.ragnarok.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import nitjsr.team.in.ragnarok.Activity.SearchItemActivity;
import nitjsr.team.in.ragnarok.Adapter.SearchResultAdapter;
import nitjsr.team.in.ragnarok.Modals.ItemModal;
import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;

public class SearchFragment extends Fragment {

    private RecyclerView searchResultsList;
    RelativeLayout searchBar;
    private SearchResultAdapter mAdapter;
    private ArrayList<ItemModal> currentList;
    private String type, key;
    private TextView searchText;

    @Override
    public void onResume() {
        super.onResume();
        AppConstants.mSearchResultsActivity = getActivity();
        if (!AppConstants.key.equals("-1")) {
            searchText.setText(AppConstants.key);
        }
        type = AppConstants.type;
        key = AppConstants.key;
        setUpRecyclerView();
        Log.e("onResume: ", "set up rv called..." + AppConstants.type);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        type = AppConstants.type;
        key = AppConstants.key;
        init(root);
        receiveClicks();
        //setUpRecyclerView();
        Log.e("onResCreate view", type + " hghg " + key);

        return root;
    }

    private void setUpRecyclerView() {
        filterDataList();
        mAdapter = new SearchResultAdapter(getActivity(), currentList, getActivity());
        searchResultsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchResultsList.setAdapter(mAdapter);
    }

    private void filterDataList() {

        if (type.equals("-1")) return;
        currentList.clear();

        if (type.equals("name")) {
            Log.e("filterDataList: ", "name");
            for (ItemModal im : AppConstants.mItemList) {
                if (im.getName().equalsIgnoreCase(key)) {
                    currentList.add(im);
                }
            }
        } else if (type.equals("subcat")) {
            Log.e("filterDataList: ", "subCat");
            for (ItemModal im : AppConstants.mItemList) {
                if (im.getSubCategory().equalsIgnoreCase(key)) {
                    currentList.add(im);
                }
            }
        } else if (type.equals("cat")) {
            Log.e("filterDataList: ", "cat");
            for (ItemModal im : AppConstants.mItemList) {
                if (im.getCategory().equalsIgnoreCase(key)) {
                    currentList.add(im);
                }
            }
        }

    }

    private void receiveClicks() {
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstants.mItemList.size() == 0) {
                    if (AppConstants.isNetworkAvailable(getActivity())) {
                        AppConstants.fetchGoodsItemList(getActivity());
                    } else {
                        Toast.makeText(getActivity(), "Please make sure you have a secure internet connection.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                startActivity(new Intent(getContext(), SearchItemActivity.class));
            }
        });
    }

    private void init(View root) {
        searchBar = root.findViewById(R.id.search_bar);
        searchResultsList = root.findViewById(R.id.search_results_list);
        searchText = root.findViewById(R.id.search_text);
        currentList = new ArrayList<>();
    }
}