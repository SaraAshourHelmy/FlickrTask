package sara.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sara.com.Models.Group;
import sara.com.Flickrtask.R;

public class GroupAdapter extends BaseAdapter {

    private ArrayList<Group> groups;
    private Context context;

    public GroupAdapter(Context context, ArrayList<Group> groups) {

        this.groups = groups;
        this.context = context;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addAll(ArrayList<Group> lst) {

        groups.addAll(lst);
        notifyDataSetChanged();
    }

    public void clear() {
        groups.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final HolderView holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_group, parent, false);
            holder = new HolderView();
            holder.tvGroupName = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

        holder.tvGroupName.setText(groups.get(position).getName());
        return convertView;
    }

    private class HolderView {

        public TextView tvGroupName;


    }

}
