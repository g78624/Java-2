//Kyle Kauck

package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kyle.multi_activity.DetailView;
import com.example.kyle.multi_activity.R;

import Data_and_Adapters.DataHelper;

public class DetailFragment extends Fragment {

    public static final String TAG = "Detail Fragment";
    private DataHelper mNewDetails = new DataHelper();

    public static DetailFragment newInstance(){

        return new DetailFragment();

    }

    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceBundle){

        return _inflater.inflate(R.layout.detail_view, _container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        final View view = getView();
        assert view != null;

        mNewDetails = ((DetailView) getActivity()).setValues();

        TextView name = (TextView) view.findViewById(R.id.detailName);
        TextView phone = (TextView) view.findViewById(R.id.detailPhone);
        TextView relationship = (TextView) view.findViewById(R.id.detailRelationship);
        TextView birthday = (TextView) view.findViewById(R.id.detailBirthday);

        name.setText(mNewDetails.getName());
        phone.setText("Phone Number: " + mNewDetails.getPhone());
        relationship.setText("Relationship: " + mNewDetails.getRelationship());
        birthday.setText("Birthday: " + mNewDetails.getBirthday());

        //This button click will call the delete function to delete the entry
        Button deleteButton = (Button) view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((DetailView) getActivity()).deleteContact();

            }

        });

        //This button click will call the implicit intent to make a phone call based on the users information
        Button callButton = (Button) view.findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNewDetails.getPhone()));
                startActivity(intent);

            }

        });

    }
}
