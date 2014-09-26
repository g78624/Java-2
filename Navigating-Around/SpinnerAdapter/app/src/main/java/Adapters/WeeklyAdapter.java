//Kyle Kauck

package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kyle.spinneradapter.R;

import java.util.ArrayList;

public class WeeklyAdapter extends BaseAdapter {

    private  static final long CONSTANT_WEATHERID = 0x010101010L;
    private Context mContext;
    private ArrayList<WeeklyInfo> mWeekly;

    public WeeklyAdapter (Context _context, ArrayList<WeeklyInfo> _weeklyArray){

        mContext = _context;
        mWeekly = _weeklyArray;

    }

    @Override
    public int getCount() {

        return mWeekly.size();

    }

    @Override
    public Object getItem(int position) {

        return mWeekly.get(position);

    }

    @Override
    public long getItemId(int position) {

        return CONSTANT_WEATHERID + position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.weekly_layout, parent, false);

        }

        WeeklyInfo weekly = (WeeklyInfo) getItem(position);

        TextView time = (TextView) convertView.findViewById(R.id.weeklyTime);
        TextView condition = (TextView) convertView.findViewById(R.id.weeklyCondition);

        time.setText(weekly.getTime());
        condition.setText(weekly.getCondition());

        return convertView;


    }

}
