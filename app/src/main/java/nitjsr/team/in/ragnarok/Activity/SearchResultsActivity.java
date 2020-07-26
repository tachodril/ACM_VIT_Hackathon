//package nitjsr.team.in.ragnarok.Activity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//import nitjsr.team.in.ragnarok.Adapter.SearchResultAdapter;
//import nitjsr.team.in.ragnarok.Modals.ItemModal;
//import nitjsr.team.in.ragnarok.R;
//import nitjsr.team.in.ragnarok.utils.AppConstants;
//
//public class SearchResultsActivity extends Activity {
//
//    private ImageView clearButton;
//    private RelativeLayout openSearchActivity;
//    private RecyclerView searchResultsList;
//    private TextView searchText;
//    private SearchResultAdapter mAdapter;
//    private ArrayList<ItemModal> currentList;
//    private String type, key;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_results);
//
//        //remember to implement appconstant keyword clear on click for each item here
//        init();
//        receiveClicks();
//        setUpRecyclerView();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        AppConstants.mSearchResultsActivity = SearchResultsActivity.this;
//    }
//
//    private void setUpRecyclerView() {
//        filterDataList();
//        mAdapter = new SearchResultAdapter(this, currentList, SearchResultsActivity.this);
//        searchResultsList.setLayoutManager(new LinearLayoutManager(this));
//        searchResultsList.setAdapter(mAdapter);
//    }
//
//    private void filterDataList() {
//
//        if (type.equals("name")) {
//            Log.e("filterDataList: ", "name");
//            for (ItemModal im : AppConstants.mItemList) {
//                if (im.getName().equalsIgnoreCase(key)) {
//                    currentList.add(im);
//                }
//            }
//        } else if (type.equals("subcat")) {
//            Log.e("filterDataList: ", "subCat");
//            for (ItemModal im : AppConstants.mItemList) {
//                if (im.getSubCategory().equalsIgnoreCase(key)) {
//                    currentList.add(im);
//                }
//            }
//        } else if (type.equals("cat")) {
//            Log.e("filterDataList: ", "cat");
//            for (ItemModal im : AppConstants.mItemList) {
//                if (im.getCategory().equalsIgnoreCase(key)) {
//                    currentList.add(im);
//                }
//            }
//        }
//
//    }
//
//    private void receiveClicks() {
//        openSearchActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SearchResultsActivity.this, SearchItemActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SearchResultsActivity.this, SearchItemActivity.class);
//                AppConstants.searchKeyWord = "";
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    private void init() {
//        currentList = new ArrayList<>();
//        clearButton = findViewById(R.id.clear_button_sr);
//        openSearchActivity = findViewById(R.id.open_search_sr);
//        searchText = findViewById(R.id.search_keyword_sr);
//        searchText.setText(AppConstants.searchKeyWord);
//        searchResultsList = findViewById(R.id.search_results_list);
//
//        type = getIntent().getStringExtra("type");
//        key = getIntent().getStringExtra("key");
//
//    }
//}
