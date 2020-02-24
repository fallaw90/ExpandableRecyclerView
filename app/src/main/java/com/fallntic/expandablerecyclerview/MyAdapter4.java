package com.fallntic.expandablerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.MyViewHolder> {

    private static final String TAG = "MyAdapter3";

    private DataHolder myData;
    private Context context;

    public MyAdapter4(Context context) {
        this.context = context;
        myData = new DataHolder();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = myData.getElements().get(position);
        //creating an animation
        Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

        //toggling visibility
        holder.textViewItem.setText(item.getItemName());

        //adding sliding effect
        holder.expandableLayout.startAnimation(slideDown);
    }

    @Override
    public int getItemCount() {
        return myData.getElements().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "MyViewHolder";

        RelativeLayout expandableLayout;
        TextView textViewItem;
        ImageView imageView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewItem = itemView.findViewById(R.id.textView_item);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setVisibility(View.GONE);
            expandableLayout.setVisibility(View.GONE);
        }
    }
}