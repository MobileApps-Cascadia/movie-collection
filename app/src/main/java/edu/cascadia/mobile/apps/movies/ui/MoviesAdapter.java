package edu.cascadia.mobile.apps.movies.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.cascadia.mobile.apps.movies.EditMovie;
import edu.cascadia.mobile.apps.movies.R;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MovieEntity> mMovies;
    private final Context mContext;

    public MoviesAdapter(List<MovieEntity> mMovies, Context mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void updateData(List<MovieEntity> movies) {
        this.mMovies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private FloatingActionButton mFab;

        public ViewHolder(View itemView){
            super(itemView);

            mTextView = itemView.findViewById(R.id.movie_text);
            mFab = itemView.findViewById(R.id.mfab);

            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieEntity movie = mMovies.get(getAdapterPosition());
                    Intent editMovie = new Intent(mContext, EditMovie.class);
                    editMovie.putExtra(EditMovie.MOVIE_ID_KEY, movie.getId());
                    mContext.startActivity(editMovie);

                }
            });
        }

        void bindData(int position) {
            MovieEntity movie = mMovies.get(position);
            mTextView.setText(movie.getTitle() + ": " + movie.getDirector());
        }

    }
}
