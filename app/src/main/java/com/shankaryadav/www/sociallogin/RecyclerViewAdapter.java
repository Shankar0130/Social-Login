package com.shankaryadav.www.sociallogin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context context;
    private List<TaskObject> myTasklist;



    public RecyclerViewAdapter(Context context, List<TaskObject> myTasklist) {
        this.context = context;
        this.myTasklist = myTasklist;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View rootView = inflater.inflate (R.layout.card_layout,parent,false);

        MyHolder holder = new MyHolder (rootView);
        return holder;
    }



    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.myTopic.setText ( myTasklist.get (position).getTopic () );
        holder.mySub.setText ( myTasklist.get (position).getSubject () );
        holder.myButton.setEnabled (true);
    }

    @Override
    public int getItemCount() {
        return myTasklist.size ();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView mySub, myTopic;
        Button myButton;

        public MyHolder(View itemView) {
            super (itemView);
            mySub = itemView.findViewById (R.id.mainSubject);
            myTopic = itemView.findViewById (R.id.maintopic);
            myButton = itemView.findViewById (R.id.delete);
        }
    }
}
