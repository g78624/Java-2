//Kyle Kauck

package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kyle.multi_activity.R;


public class CreateFragment extends Fragment {

    public static final String TAG = "Create Fragment";
    private TextView mName;
    private TextView mPhone;
    private TextView mRelationship;
    private TextView mBirthday;
    private contactDetails mDetails;

    public interface contactDetails {

        public void details(String _name, String _phone, String _relationship, String _birthday);

    }

    public static CreateFragment newInstance(){

        return new CreateFragment();

    }

    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceBundle){

        return _inflater.inflate(R.layout.create_view, _container, false);

    }

    public void onAttach(Activity activity){

        super.onAttach(activity);

        if (activity instanceof contactDetails){

            mDetails = (contactDetails) activity;

        } else {

            throw  new IllegalArgumentException("Must Implement Contact Details");
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }
}
