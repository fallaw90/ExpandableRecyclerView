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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    private static final String TAG = "MyAdapter1";

    private DataHolder myData;
    private Context context;

    public MyAdapter1(Context context) {
        this.context = context;
        this.myData = new DataHolder();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Item item = myData.getItems().get(position);
        holder.textViewItem.setText(item.getItemName());

        boolean isExpanded = myData.getItems().get(position).isExpanded();
        //holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        if (!isExpanded){
            holder.expandableLayout.setVisibility(View.GONE);
            holder.imageView.animate().rotation(360F).start();
        }else {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.expandableLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.expandableLayout.startAnimation(slideDown);
            holder.imageView.animate().rotation(180F).start();
        }
    }

    @Override
    public int getItemCount() {
        return myData.getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "MyViewHolder";

        RelativeLayout expandableLayout;
        RecyclerView recyclerView;
        TextView textViewItem;
        ImageView imageView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewItem = itemView.findViewById(R.id.textView_item);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            imageView = itemView.findViewById(R.id.imageView);

            MyAdapter2 myAdapter2 = new MyAdapter2(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(myAdapter2);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < getItemCount(); i++) {
                        if (i != getAdapterPosition()) {
                            boolean isExpanded = myData.getItems().get(i).isExpanded();
                            if (isExpanded) {
                                myData.getItems().get(i).setExpanded(false);
                                notifyItemChanged(i);
                            }
                        }
                    }

                    Item item = myData.getItems().get(getAdapterPosition());
                    item.setExpanded(!item.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}