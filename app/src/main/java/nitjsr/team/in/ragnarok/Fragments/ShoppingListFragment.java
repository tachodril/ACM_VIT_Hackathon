package nitjsr.team.in.ragnarok.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

import nitjsr.team.in.ragnarok.Activity.SearchItemActivity;
import nitjsr.team.in.ragnarok.Activity.StoreMapActivity;
import nitjsr.team.in.ragnarok.Adapter.MyShoppingListAdapter;
import nitjsr.team.in.ragnarok.Modals.ItemModal;
import nitjsr.team.in.ragnarok.Modals.ListItem;
import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;

public class ShoppingListFragment extends Fragment {

    FloatingActionButton fab;
    public static RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    public static ArrayList<ListItem> itemList = new ArrayList<>();

    String key = "ItemList";
    private static final String SHARED_PREF = "SharedPref";
    SharedPreferences shref;
    SharedPreferences.Editor editor;
    public static MyShoppingListAdapter adapter;
    private boolean firstTimeFlag = true;

    private ProgressDialog progressDialog;
    private ImageView clearListButton, overFlowMenuButton;

    private SpeedDialView sv_fab;

    @Override
    public void onResume() {
        super.onResume();
        AppConstants.mCreateShoppingListActivity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppConstants.isCreateShoppingListActivityOpen = false;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        init(root);

        //Adding list in local storage
        fetchItemList();
        setUpFabItems();

        receiveClicks();
        setUpRecyclerView();

        return root;
    }

    private void setUpFabItems() {
//        sv_fab.addActionItem(new SpeedDialActionItem.Builder(R.id.item1, getResources().getDrawable(R.drawable.ic_baseline_add_24))
//                .setFabBackgroundColor(Color.WHITE)
//                .setFabImageTintColor(Color.parseColor("#0e76a8"))
//                .setLabel("Add items")
//                .setLabelColor(Color.parseColor("#0e76a8"))
//                .setLabelBackgroundColor(Color.WHITE)
//                .setLabelClickable(true)
//                .create());
        sv_fab.addActionItem(new SpeedDialActionItem.Builder(R.id.item2, getResources().getDrawable(R.drawable.ic_clear_all_black_24dp))
                .setFabBackgroundColor(Color.WHITE)
                .setFabImageTintColor(Color.parseColor("#0e76a8"))
                .setLabel("Clear list")
                .setLabelColor(Color.parseColor("#0e76a8"))
                .setLabelBackgroundColor(Color.WHITE)
                .setLabelClickable(true)
                .create());
        sv_fab.addActionItem(new SpeedDialActionItem.Builder(R.id.item3, getResources().getDrawable(R.drawable.ic_shopping_cart_black_24dp))
                .setFabBackgroundColor(Color.WHITE)
                .setFabImageTintColor(Color.parseColor("#0e76a8"))
                .setLabel("Proceed to shop")
                .setLabelColor(Color.parseColor("#0e76a8"))
                .setLabelBackgroundColor(Color.WHITE)
                .setLabelClickable(true)
                .create());
    }

    private void setUpRecyclerView() {
        layoutManager = new GridLayoutManager(getActivity(), 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        itemList = AppConstants.sortItemList(itemList);
        adapter = new MyShoppingListAdapter(getActivity(), itemList);
        recyclerView.setAdapter(adapter);
        firstTimeFlag = false;
        AppConstants.mCreateShoppingListActivity = getActivity();
        AppConstants.isCreateShoppingListActivityOpen = true;
    }

    private void init(View root) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching shopping list...");
        progressDialog.setCancelable(false);

        sv_fab = root.findViewById(R.id.speedDial);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
    }

    private void fetchItemList() {
        shref = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if (!AppConstants.listFromScan.equals("")) {
            progressDialog.show();

            String response = AppConstants.listFromScan;
            String oneLine[] = response.split("\n");
            for (String pd : oneLine) {
                String keywords[] = pd.split(" ");
                for (String oneWord : keywords) {
                    boolean flag = false;
                    for (ItemModal im : AppConstants.mItemList) {
                        if (im.getName().toLowerCase().contains(oneWord.toLowerCase())) {

                            boolean ifAlreadyInList = false;
                            //check if this item is already in the list
                            for (ListItem li : itemList) {
                                if (li.getName().equalsIgnoreCase(im.getName())
                                        && li.getDescription().equalsIgnoreCase(im.getDescription())) {
                                    li.setItemCount(li.getItemCount() + 1);
                                    ifAlreadyInList = true;
                                }
                            }

                            //add to item list
                            if (!ifAlreadyInList) {
                                itemList.add(new ListItem(im.getCategory(),
                                        im.getSubCategory(), im.getPrice(), im.getFloor(),
                                        im.getShelf(), im.getDescription(),
                                        im.getName(), 1, false));
                            }

                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
            }

            // add scanned list to shared preferences
            String json = gson.toJson(itemList);

            editor = shref.edit();
            editor.remove("ItemList").commit();
            editor.putString("ItemList", json);
            editor.commit();

            AppConstants.listFromScan = "";
            progressDialog.dismiss();
            return;
        }


        String response = shref.getString(key, "");

        if (gson.fromJson(response, new TypeToken<List<ListItem>>() {
        }.getType()) != null)
            itemList = gson.fromJson(response, new TypeToken<List<ListItem>>() {
            }.getType());
    }

    private void receiveClicks() {

        sv_fab.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
//                if (actionItem.getId() == R.id.item1) {
//                    if (AppConstants.mItemList.size() == 0) {
//                        if (AppConstants.isNetworkAvailable(getActivity())) {
//                            AppConstants.fetchGoodsItemList(getActivity());
//                        } else {
//                            Toast.makeText(getActivity(), "Please make sure you have a secure internet connection.", Toast.LENGTH_SHORT).show();
//                        }
//                        return true;
//                    }
//
//                    startActivity(new Intent(getActivity(), SearchItemActivity.class));
//                    return true;
//                } else
                    if (actionItem.getId() == R.id.item2) {
                    shref = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                    Gson gson = new Gson();
                    String response = shref.getString(key, "");

                    if (gson.fromJson(response, new TypeToken<List<ListItem>>() {
                    }.getType()) != null)
                        itemList = gson.fromJson(response, new TypeToken<List<ListItem>>() {
                        }.getType());

                    if (itemList.size() == 0) {
                        Toast.makeText(getActivity(), "Item List is Empty...", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Clear Shopping List");
                    alertDialog.setMessage("Are you sure you want to clear your list?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "CLEAR",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    shref = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                                    editor = shref.edit();
                                    editor.remove("ItemList").commit();
                                    itemList.clear();
                                    adapter = new MyShoppingListAdapter(getActivity(), itemList);
                                    recyclerView.setAdapter(adapter);

                                }
                            });
                    alertDialog.show();
                    return true;
                } else if (actionItem.getId() == R.id.item3) {
                        startActivity(new Intent(getActivity(), StoreMapActivity.class));
                    // switch to tab 5
                }
                return false;
            }
        });

    }

}