package com.example.eatit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eatit.Interface.ItemClickListener;
import com.example.eatit.Model.Category;
import com.example.eatit.Model.Food;
import com.example.eatit.VIewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryId ="";
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView  = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null)
        {
           loadListFood(categoryId);
        }

    }

    private void loadListFood(String categoryId) {
        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(foodList,Food.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.food_name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.food_image);

                final Food local = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.food_item,viewGroup,false);
                return new FoodViewHolder(itemView);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
