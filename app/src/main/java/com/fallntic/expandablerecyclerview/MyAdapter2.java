package com.fallntic.expandablerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private static final String TAG = "MyAdapter1";

    private DataHolder myData;
    private Context context;

    public MyAdapter2(Context context) {
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

        Item item = myData.getSubItems().get(position);
        holder.textViewItem.setText(item.getItemName());

        boolean isExpanded = myData.getSubItems().get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        if (!isExpanded){
            holder.imageView.animate().rotation(360F).start();
        }else {
            holder.imageView.animate().rotation(180F).start();
        }
    }

    @Override
    public int getItemCount() {
        return myData.getSubItems().size();
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

            MyAdapter3 myAdapter3 = new MyAdapter3(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(myAdapter3);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < getItemCount(); i++) {
                        if (i != getAdapterPosition()) {
                            boolean isExpanded = myData.getSubItems().get(i).isExpanded();
                            if (isExpanded) {
                                myData.getSubItems().get(i).setExpanded(false);
                                notifyItemChanged(i);
                            }
                        }
                    }

                    Item item = myData.getSubItems().get(getAdapterPosition());
                    item.setExpanded(!item.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}