//Kyle Kauck

package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kyle.navigationdrawer.R;

import Helpers.TeamHelper;

public class TeamFragment extends Fragment {

    private static final String TEAM_NUMBER = "team_number";
    private TeamHelper mTeamDetails = new TeamHelper();

    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.team_display, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final View view = getView();
        assert view != null;

        Bundle args = getArguments();

        if (args != null){

            mTeamDetails = (TeamHelper) args.getSerializable("team_data");

            TextView name = (TextView) view.findViewById(R.id.teamName);
            TextView playerOne = (TextView) view.findViewById(R.id.firstPlayer);
            TextView playerTwo = (TextView) view.findViewById(R.id.secondPlayer);
            TextView playerThree = (TextView) view.findViewById(R.id.thirdPlayer);
            TextView playerFour = (TextView) view.findViewById(R.id.fourthPlayer);
            TextView playerFive = (TextView) view.findViewById(R.id.fifthPlayer);
            TextView playerSix = (TextView) view.findViewById(R.id.sixthPlayer);
            TextView playerSeven = (TextView) view.findViewById(R.id.seventhPlayer);
            TextView playerEight = (TextView) view.findViewById(R.id.eighthPlayer);
            TextView playerNine = (TextView) view.findViewById(R.id.ninthPlayer);

            name.setText(mTeamDetails.getTeam());
            playerOne.setText(mTeamDetails.getOne());
            playerTwo.setText(mTeamDetails.getTwo());
            playerThree.setText(mTeamDetails.getThree());
            playerFour.setText(mTeamDetails.getFour());
            playerFive.setText(mTeamDetails.getFive());
            playerSix.setText(mTeamDetails.getSix());
            playerSeven.setText(mTeamDetails.getSeven());
            playerEight.setText(mTeamDetails.getEight());
            playerNine.setText(mTeamDetails.getNine());

        }

    }
}
