package nitjsr.team.in.ragnarok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nitjsr.team.in.ragnarok.Activity.MainActivity;
import nitjsr.team.in.ragnarok.Modals.ItemModal;
import nitjsr.team.in.ragnarok.Modals.ListItem;
import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;

import static nitjsr.team.in.ragnarok.Fragments.ShoppingListFragment.itemList;
import static nitjsr.team.in.ragnarok.Fragments.ShoppingListFragment.recyclerView;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.Lviewholder> {

    private Context context;
    private ArrayList<ItemModal> data;
    private FragmentActivity currentActivity;

    public SearchResultAdapter(Context context1, ArrayList<ItemModal> currentList, FragmentActivity searchResultsActivity) {
        this.context = context1;
        this.data = currentList;
        this.currentActivity = searchResultsActivity;
    }

    @NonNull
    @Override
    public Lviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        return new Lviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lviewholder holder, final int position) {
        ItemModal currentItem = data.get(position);
        holder.nameSubCat.setText(currentItem.getName() + ", " + currentItem.getSubCategory());
        holder.price.setText(currentItem.getPrice());
        holder.location.setText("Floor " + currentItem.getFloor() + ", Shelf-" + currentItem.getShelf());
        holder.description.setText(currentItem.getDescription() + " Category-" + currentItem.getCategory());

        Glide.with(context)
                .load(currentItem.getImageUrl().toString())
                //.apply(new RequestOptions().placeholder(R.drawable.ic_image_black_24dp).error(R.drawable.ic_image_black_24dp))
                .into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.openAddItemDialog(context, data.get(position), 1);
            }
        });

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModal itemModal = data.get(position);

                SharedPreferences sharedPreferences = AppConstants.mSearchProductActivity
                        .getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                ArrayList<ListItem> myList = new ArrayList<>();


                //fetch older item list
                Gson gson = new Gson();
                String response = sharedPreferences.getString("ItemList", "");

                if (gson.fromJson(response, new TypeToken<List<ListItem>>() {
                }.getType()) != null)
                    myList = gson.fromJson(response, new TypeToken<List<ListItem>>() {
                    }.getType());
                else
                    myList = new ArrayList<>();

                boolean ifAlreadyInList = false;
                //check if this item is already in the list
                for (ListItem li : myList) {
                    if (li.getName().equalsIgnoreCase(itemModal.getName())
                            && li.getDescription().equalsIgnoreCase(itemModal.getDescription())) {
                        li.setItemCount(li.getItemCount() + 1);
                        ifAlreadyInList = true;
                    }
                }

                //add to item list
                if (!ifAlreadyInList) {
                    myList.add(new ListItem(
                            itemModal.getCategory(),
                            itemModal.getSubCategory(),
                            itemModal.getPrice(),
                            itemModal.getFloor(),
                            itemModal.getShelf(),
                            itemModal.getDescription(),
                            itemModal.getName(),
                            1, false
                    ));
                }
                Collections.reverse(myList);
                myList = AppConstants.sortItemList(myList);
                itemList = myList;

                Gson gson2 = new Gson();
                String json = gson2.toJson(myList);

                editor = sharedPreferences.edit();
                editor.remove("ItemList").commit();
                editor.putString("ItemList", json);
                editor.commit();

                //currentActivity.finish();
                //AppConstants.searchKeyWord="";
//                AppConstants.mCreateShoppingListActivity.recyclerView
//                        .setAdapter(new MyShoppingListAdapter(
//                                context, myList
//                        ));
                Toast.makeText(context, "Added to shopping list", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Lviewholder extends RecyclerView.ViewHolder {
        TextView nameSubCat, price, location, description, addButton;
        ImageView itemImage;

        public Lviewholder(@NonNull View itemView) {
            super(itemView);
            nameSubCat = itemView.findViewById(R.id.search_item_name_sr);
            price = itemView.findViewById(R.id.search_item_price_sr);
            location = itemView.findViewById(R.id.search_product_floor_sr);
            description = itemView.findViewById(R.id.search_item_description_sr);
            itemImage = itemView.findViewById(R.id.image_sr);
            addButton = itemView.findViewById(R.id.add_to_list_sr);
        }
    }
}
