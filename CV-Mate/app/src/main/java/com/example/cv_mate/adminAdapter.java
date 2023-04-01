package com.example.cv_mate;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adminAdapter extends FirebaseRecyclerAdapter<adminModel,adminAdapter.adminViewAdapter> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    Activity activity;
    public adminAdapter(@NonNull FirebaseRecyclerOptions<adminModel> options, Context context, Activity activity) {
        super(options);
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull adminViewAdapter holder, int position, @NonNull adminModel model) {

    }

    @NonNull
    @Override
    public adminViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class adminViewAdapter extends RecyclerView.ViewHolder{

        public adminViewAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }

}
