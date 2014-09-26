package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kyle.spinneradapter.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private  static final long CONSTANT_WEATHERID = 0x010101010L;
    private Context mContext;
    private ArrayList<HourlyInfo> mHourly;

    public ListAdapter (Context _context, ArrayList<HourlyInfo> _houlryArray){

        mContext = _context;
        mHourly = _houlryArray;

    }

    @Override
    public int getCount() {

        return mHourly.size();

    }

    @Override
    public Object getItem(int position) {

        return mHourly.get(position);

    }

    @Override
    public long getItemId(int position) {

        return CONSTANT_WEATHERID + position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.hourly_layout, parent, false);

        }

        HourlyInfo hourly = (HourlyInfo) getItem(position);

        TextView time = (TextView) convertView.findViewById(R.id.hourlyTime);
        TextView condition = (TextView) convertView.findViewById(R.id.hourlyCondition);
        TextView temp = (TextView) convertView.findViewById(R.id.hourlyTemp);

        time.setText(hourly.getTime());
        condition.setText(hourly.getCondition());
        temp.setText(hourly.getTemp());

        return convertView;

    }
}
