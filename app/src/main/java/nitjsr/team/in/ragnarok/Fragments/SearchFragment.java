package nitjsr.team.in.ragnarok.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.SearchItemActivity;

public class SearchFragment extends Fragment {

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    RecyclerView searchResultsList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        init(root);
        receiveClicks();

        return root;
    }

    private void receiveClicks() {
        textInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (AppConstants.mItemList.size() == 0) {
//                    if (AppConstants.isNetworkAvailable(getActivity())) {
//                        AppConstants.fetchGoodsItemList(getActivity());
//                    } else {
//                        Toast.makeText(getActivity(), "Please make sure you have a secure internet connection.", Toast.LENGTH_SHORT).show();
//                    }
//                    return;
//                }
                startActivity(new Intent(getContext(), SearchItemActivity.class));
            }
        });
    }

    private void init(View root) {
        textInputLayout=root.findViewById(R.id.search_input);
        textInputEditText=root.findViewById(R.id.search_text);
        searchResultsList=root.findViewById(R.id.search_results_list);
    }
}