//Kyle Kauck

package fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyle.java2_fundamentals.MainActivity;
import com.example.kyle.java2_fundamentals.Movie;
import com.example.kyle.java2_fundamentals.MovieData;
import com.example.kyle.java2_fundamentals.R;

public class MasterFragment extends ListFragment {

    public static final String TAG = "Master Fragment";
    private TextView mMovieTitle;
    private apiSearchWord mSearch;
    private dataDetails mData;

    public interface apiSearchWord {

        public void searchWord(String movie);

    }

    public interface dataDetails{

        public void movieDetails (String _name, String _rating, Integer _score, String _scoreName, Integer _year);

    }

    public static MasterFragment newInstance(){

        return new MasterFragment();

    }

    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        if (activity instanceof apiSearchWord){

            mSearch = (apiSearchWord) activity;


        } else {

            throw new IllegalArgumentException("Must implement apiSearchWord");

        }

        if (activity instanceof dataDetails){

            mData = (dataDetails) activity;

        } else {

            throw new IllegalArgumentException("Must implement dataDetails");

        }

    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){

        return _inflater.inflate(R.layout.master_fragment, _container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);
        final View masterView = getView();
        assert masterView != null;

        mMovieTitle = (TextView) masterView.findViewById(R.id.movieTitleSearch);



        if (((MainActivity) getActivity()).networkConnection()) {

            Button searchButton = (Button) masterView.findViewById(R.id.searchButton);
            searchButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    FragmentManager manager = getFragmentManager();
                    DetailsFragment frag = (DetailsFragment) manager.findFragmentByTag(DetailsFragment.TAG);

                    if (frag == null){

                        Log.i (TAG, "This is null right now!");

                    } else {

                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.remove(frag);
                        transaction.commit();

                    }

                    InputMethodManager hideKeyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    hideKeyboard.hideSoftInputFromWindow(mMovieTitle.getWindowToken(), 0);

                    if (((MainActivity) getActivity()).networkConnection()) {

                        Toast.makeText(getActivity(), "You Are Connected To A Network", Toast.LENGTH_SHORT).show();

                        String movieTitle = mMovieTitle.getText().toString().replace(" ", "+");

                        mSearch.searchWord(movieTitle);

                        mMovieTitle.setText("");

                    } else {

                        Toast.makeText(getActivity(), "You Are Not Connected", Toast.LENGTH_SHORT).show();

                        ((MainActivity) getActivity()).noNetworkData();

                    }

                }
            });

        } else {

            Toast.makeText(getActivity(), "You Are Not Connected", Toast.LENGTH_SHORT).show();

            ((MainActivity) getActivity()).noNetworkData();

        }

    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {

        if (((MainActivity) getActivity()).networkConnection()) {

            Movie movie = (Movie) _l.getItemAtPosition(_position);

            mData.movieDetails(movie.getName(), movie.getRating(), movie.getScore(), movie.getScoreName(), movie.getYear());

        } else {

            MovieData movieData = (MovieData) _l.getItemAtPosition(_position);

            mData.movieDetails(movieData.getName(), movieData.getRating(), movieData.getScore(), movieData.getScoreName(), movieData.getYear());

        }

    }

}
