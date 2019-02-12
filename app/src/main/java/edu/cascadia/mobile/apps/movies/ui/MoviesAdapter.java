package edu.cascadia.mobile.apps.movies.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.cascadia.mobile.apps.movies.R;
import edu.cascadia.mobile.apps.movies.model.DirectorEntity;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final List<MovieEntity> mMovies;
    private final List<DirectorEntity> mDirectors;
    private final Context mContext;

    public MoviesAdapter(List<MovieEntity> mMovies, List<DirectorEntity> mDirectors, Context mContext) {
        this.mMovies = mMovies;
        this.mDirectors = mDirectors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieEntity movie = mMovies.get(position);
        final DirectorEntity director = mDirectors.get(movie.getDirectorId()-1);

        holder.mTextView.setText(movie.getTitle() + ": " + director.getLastName()+", "+director.getFirstName());

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public ViewHolder(View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.movie_text);
        }
    }
}
