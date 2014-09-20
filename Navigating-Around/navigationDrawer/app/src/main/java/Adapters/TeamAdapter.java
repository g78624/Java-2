//Kyle Kauck

package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kyle.navigationdrawer.R;

import java.util.ArrayList;

import Helpers.TeamHelper;

public class TeamAdapter extends BaseAdapter {

    public static final long CONSTANT_ID = 0x01010101L;
    private Context mContext;
    private ArrayList<TeamHelper> mTeamDetails;

    public TeamAdapter (Context _context, ArrayList<TeamHelper> _teamArray){

        mContext = _context;
        mTeamDetails = _teamArray;

    }

    @Override
    public int getCount() {

        return mTeamDetails.size();

    }

    @Override
    public Object getItem(int position) {

        return mTeamDetails.get(position);

    }

    @Override
    public long getItemId(int position) {

        return CONSTANT_ID + position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);

        }

        TeamHelper helper = (TeamHelper) getItem(position);

        TextView team = (TextView) convertView.findViewById(R.id.teamName);

        team.setText(helper.getTeam());

        return convertView;

    }
}
