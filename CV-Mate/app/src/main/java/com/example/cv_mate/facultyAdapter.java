package com.example.cv_mate;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class facultyAdapter extends FirebaseRecyclerAdapter<facultyModel,facultyAdapter.facultyViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public facultyAdapter(@NonNull FirebaseRecyclerOptions<facultyModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull facultyViewHolder holder, int position, @NonNull facultyModel model) {

    }

    @NonNull
    @Override
    public facultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class facultyViewHolder extends RecyclerView.ViewHolder{

        public facultyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
