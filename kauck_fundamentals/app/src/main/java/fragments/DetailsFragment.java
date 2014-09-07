//Kyle Kauck

package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kyle.java2_fundamentals.R;

public class DetailsFragment extends Fragment {

    public static final String TAG = "Details Fragment";
    public static final String TITLE = "title";
    public static final String RATING = "rating";
    public static final String SCORE = "score";
    public static final String CRITIC = "critic";
    public static final String YEAR = "year";

    public static DetailsFragment newInstance(String _name, String _rating, Integer _score, String _scoreName, Integer _year){

        DetailsFragment frag = new DetailsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE, _name);
        args.putString(RATING, _rating);
        args.putInt(SCORE, _score);
        args.putString(CRITIC, _scoreName);
        args.putInt(YEAR, _year);

        frag.setArguments(args);

        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){

        return _inflater.inflate(R.layout.detail_fragment, _container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();

        if(args != null){

            setDisplay(args.getString(TITLE), args.getString(RATING), args.getInt(SCORE), args.getString(CRITIC), args.getInt(YEAR));

        }

    }

    public void setDisplay(String _name, String _rating, Integer _score, String _scoreName, Integer _year){

        TextView name = (TextView) getView().findViewById(R.id.movieName);
        TextView rating = (TextView) getView().findViewById(R.id.movieRating);
        TextView year = (TextView) getView().findViewById(R.id.movieYear);
        TextView score = (TextView) getView().findViewById(R.id.movieScore);
        TextView scoreName = (TextView) getView().findViewById(R.id.movieScoreName);

        name.setText(_name);
        year.setText("Year Released: " + _year.toString());
        rating.setText("MPAA Rating: " + _rating);
        score.setText("Average Critic Score: " + _score.toString());
        scoreName.setText("Rotten Tomatoes Score: " + _scoreName);

    }

}
