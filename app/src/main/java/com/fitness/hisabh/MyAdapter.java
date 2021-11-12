package com.fitness.hisabh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Student> al;

    public MyAdapter(Context ct,ArrayList<Student> al){
        this.context = ct;
        this.al = al;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View view = inflater.inflate( R.layout.custom_listview,viewGroup,false );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Student gl=al.get(i);
        myViewHolder.t1.setText( ""+gl.getId() );
        myViewHolder.t2.setText( ""+gl.getName() );
        myViewHolder.t3.setText( ""+gl.getSurname() );
        myViewHolder.t4.setText( ""+gl.getDateId() );
        /*
        Intent in = new Intent( context,Aa.class );
        in.putExtra( "ki",gl.getDateId() );
        context.startActivity( in ); */
        myViewHolder.lau.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( context,Aa.class );
                in.putExtra( "ki",gl.getDateId() );
                context.startActivity( in );
            }
        } );
    }



    @Override
    public int getItemCount() {
        return al.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4;
        LinearLayout lau;


        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            lau = itemView.findViewById( R.id.lau );
            t1 = itemView.findViewById( R.id.id_txt );
            t2 = itemView.findViewById( R.id.id_txt1 );
            t3 = itemView.findViewById( R.id.id_txt2 );
            t4 = itemView.findViewById( R.id.id_txt3 );

        }
    }
}
