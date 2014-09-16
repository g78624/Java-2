//Kyle Kauck

package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        final View view = getView();
        assert view != null;

        mName = (TextView) view.findViewById(R.id.createName);
        mPhone = (TextView) view.findViewById(R.id.createPhone);
        mRelationship = (TextView) view.findViewById(R.id.createRelationship);
        mBirthday = (TextView) view.findViewById(R.id.createBirthday);

        Button createButton = (Button) view.findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager hideKeyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyboard.hideSoftInputFromWindow(mBirthday.getWindowToken(), 0);
                hideKeyboard.hideSoftInputFromWindow(mName.getWindowToken(), 0);
                hideKeyboard.hideSoftInputFromWindow(mPhone.getWindowToken(), 0);
                hideKeyboard.hideSoftInputFromWindow(mRelationship.getWindowToken(), 0);

                Toast.makeText(getActivity(), "You have successfully added a new contact", Toast.LENGTH_LONG).show();

                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();
                String relationship = mRelationship.getText().toString();
                String birthday = mBirthday.getText().toString();

                mDetails.details(name, phone, relationship, birthday);

                mName.setText("");
                mBirthday.setText("");
                mPhone.setText("");
                mRelationship.setText("");

            }
        });

    }
}
