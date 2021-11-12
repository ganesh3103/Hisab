package com.fitness.hisabh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapt extends RecyclerView.Adapter<Adapt.ViewHolder>  {
    private static final String TAG = "rrr";
    Context context1;
    ArrayList<Student> al1;

    public Adapt(Context ct,ArrayList<Student> al) {
        this.context1 = ct;
        this.al1 = al;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from( context1 );
        View view = inflater.inflate( R.layout.kia,viewGroup,false );
        ViewHolder holder = new ViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Student gl=al1.get(i);
     //  viewHolder.tw0.setText( ""+gl.getId() );
        viewHolder.tw1.setText( ""+gl.getName() );
        viewHolder.tw2.setText( ""+gl.getSurname() );
        viewHolder.tw3.setText( ""+gl.getDateId() );
    }

    @Override
    public int getItemCount() {
        return al1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tw1,tw2,tw3,tw0;
        LinearLayout lau1;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            tw0 = itemView.findViewById( R.id.id0_txt );
            tw1 = itemView.findViewById( R.id.id1_txt1 );
            tw2 = itemView.findViewById( R.id.id2_txt2 );
            tw3 = itemView.findViewById( R.id.id3_xt3);
        }
    }
}
