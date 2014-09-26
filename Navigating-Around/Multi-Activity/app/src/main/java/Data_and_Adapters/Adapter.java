//Kyle Kauck

package Data_and_Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kyle.multi_activity.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    public static final long CONSTANT_ID = 0x010101010L;
    private Context mContext;
    private ArrayList<DataHelper> mContactDetail;

    public Adapter (Context _context, ArrayList<DataHelper> _contactArray){

        mContext = _context;
        mContactDetail = _contactArray;

    }

    @Override
    public int getCount() {

        return mContactDetail.size();

    }

    @Override
    public Object getItem(int position) {

        return mContactDetail.get(position);

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

        DataHelper helper = (DataHelper) getItem(position);

        TextView name = (TextView) convertView.findViewById(R.id.listName);
        TextView phone = (TextView) convertView.findViewById(R.id.listPhone);

        name.setText(helper.getName());
        phone.setText(helper.getPhone());

        return convertView;

    }
}
