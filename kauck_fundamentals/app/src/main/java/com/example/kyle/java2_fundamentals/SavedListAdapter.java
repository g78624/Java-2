package com.example.kyle.java2_fundamentals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SavedListAdapter extends BaseAdapter {

    private static final long CONSTANT_MOVIEID = 0x010101010L;
    private Context mContext;
    private ArrayList<MovieData> mSavedArray;

    public SavedListAdapter (Context _context, ArrayList<MovieData> _movieArray){

        mContext = _context;
        mSavedArray = _movieArray;

    }

    @Override
    public int getCount() {

        return  mSavedArray.size();

    }

    @Override
    public Object getItem(int _position) {

        return mSavedArray.get(_position);

    }

    @Override
    public long getItemId(int _position) {

        return CONSTANT_MOVIEID + _position;

    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {

        if (_convertView == null){

            _convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_list, _parent, false);

        }

        MovieData movie = (MovieData) getItem(_position);

        TextView movieTitle = (TextView) _convertView.findViewById(R.id.movieTitle);
        movieTitle.setText(movie.getName());

        TextView movieYear = (TextView) _convertView.findViewById(R.id.releaseYear);
        movieYear.setText(movie.getYear().toString());

        return _convertView;

    }

}
