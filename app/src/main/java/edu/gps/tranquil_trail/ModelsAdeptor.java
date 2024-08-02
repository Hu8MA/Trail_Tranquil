package edu.gps.tranquil_trail;

import static android.view.LayoutInflater.from;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelsAdeptor   extends RecyclerView.Adapter<ModelsAdeptor.MyHolder>{
    Context context;
    ArrayList<Models> arrayList = new ArrayList<>();
    LayoutInflater layoutInflater;

    public ModelsAdeptor( Context context , ArrayList<Models> arrayList )
    {
        this.context = context;
        this.arrayList=arrayList;
        layoutInflater=from(context);
    }

    @NonNull
    @Override
    public ModelsAdeptor.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_itemsfiles,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelsAdeptor.MyHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getName());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.images.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView title , description;
        ImageView images;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            images=itemView.findViewById(R.id.img);
        }
    }
}
