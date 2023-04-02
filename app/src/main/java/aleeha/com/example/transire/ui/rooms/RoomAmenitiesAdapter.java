package aleeha.com.example.transire.ui.rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import aleeha.com.example.transire.R;

public class RoomAmenitiesAdapter extends ArrayAdapter<RoomAmenitiesPoints> {

    public RoomAmenitiesAdapter(@NonNull Context context, ArrayList <RoomAmenitiesPoints> roomAmenities) {
        super(context,0, roomAmenities);
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        RoomAmenitiesPoints data = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_room_amenities, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.tv_amenities);

        txtName.setText(data.roomAmenitiesName);
        return convertView;
    }

}
