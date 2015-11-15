package com.example.hoang.bookmovietickets;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 11/14/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieItemt> implements Filterable{

    private Context mContext;
    private List<MovieModel> list = new ArrayList<MovieModel>();
    private List<MovieModel> orig;
    private MovieModel movieModel;
    private DBHelper dbHelper;

    public RVAdapter(Context context, List<MovieModel> arr){
        this.mContext = context;
        this.list = arr;
        dbHelper = new DBHelper(context);
    }

    @Override
    public MovieItemt onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieItemt(itemView);
    }

    @Override
    public void onBindViewHolder(MovieItemt holder, int position) {
        movieModel = list.get(position);
        int imageResource = mContext.getResources().getIdentifier(movieModel.getImage(), null, mContext.getPackageName());
        Drawable drawable = mContext.getResources().getDrawable(imageResource);
        holder.itemImage.setImageDrawable(drawable);
        holder.itemTitle.setText(movieModel.getTitle());
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(list != null){
            size = list.size();
        }
        return size;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<MovieModel> results = new ArrayList<MovieModel>();
                if (orig == null)
                    orig = list;
                    if (constraint != null){
                        if(orig != null & orig.size() > 0){
                            for (final MovieModel g : orig){
                                if(g.getTitle().toLowerCase().contains(constraint.toString()))
                                    results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<MovieModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class MovieItemt extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView container;
        private ImageView itemImage;
        private TextView itemTitle;

        public MovieItemt(View itemView) {
            super(itemView);

            container = (CardView) itemView.findViewById(R.id.item_container);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MovieModel model = dbHelper.getMovie(list.get(getAdapterPosition()).getId());
            Intent intent = new Intent(mContext, MovieDetails.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ID", model.getId());
            intent.putExtra("DATA", bundle);
            mContext.startActivity(intent);
        }
    }

}
