package com.soshians_co.aab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yousef on 5/10/2016.
 */
public class PackageAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public PackageAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    public void setGridData(ArrayList<PackageItem> mGridData) {
        this.data = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            //Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Yekan.ttf");

            holder.ServiceName = (TextView) row.findViewById(R.id.ServiceName);
            holder.ServicePrice = (TextView) row.findViewById(R.id.ServicePrice);
            holder.ProfileName = (TextView) row.findViewById(R.id.ProfileName);
            holder.ServiceID = (TextView) row.findViewById(R.id.ServiceID);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        PackageItem item = (PackageItem) data.get(position);
        holder.ServiceName.setText(item.getServiceName());
        holder.ServicePrice.setText(item.getServicePrice());
        holder.ProfileName.setText(item.getProfileName());
        holder.ServiceID.setText(item.getServiceID());

         return row;
    }
    static class ViewHolder {
        TextView ServiceName;
        TextView ServicePrice;
        TextView ProfileName;
        TextView ServiceID;


    }
}
