package com.example.cv_mate;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class shareAdapter extends FirebaseRecyclerAdapter<shareModel,shareAdapter.shareViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    Activity activity;
    public shareAdapter(@NonNull FirebaseRecyclerOptions<shareModel> options, Context context, Activity activity) {
        super(options);
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull shareViewHolder holder, int position, @NonNull shareModel model) {

    }

    @NonNull
    @Override
    public shareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class shareViewHolder extends RecyclerView.ViewHolder{

        public shareViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
