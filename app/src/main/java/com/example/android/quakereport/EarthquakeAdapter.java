package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    String primaryLocation;
    String locationOffset;

    private static final String LOCATION_SEPARATOR = " of ";

    private String formatDate(Date dataObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dataObject);
    }

    private String formatTime(Date dataObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma");
        return timeFormat.format(dataObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    //Constructor
    //context of the app
    //earthquakes is the list of earthquakes, data source for adapter
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    //Return a list item view that displays info abt the given position
    //in the list of earthquakes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Check if there is an existing list item view (convertView) that we can reuse
        //otherwise, if convertView is null, then inflate a new list item layout
         View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                R.layout.earthquake_list_item, parent, false);
        }

        //Find the earthquake at a given position in the list of earthquake
    Earthquake currentEarthquake = getItem(position);

        String originalLocation = currentEarthquake.getLocation();

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        //Set the proper background color on the magnitude circle
        //Fetch the background from the TextView which is a GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        //Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        //set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);




        //Find the TextView with view ID location offset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        //Display the location of the current earthquake in textview
        locationOffsetView.setText(locationOffset);

        //Find the TextView with view ID primary location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        //Display the location of the current earthquake in textview
        primaryLocationView.setText(primaryLocation);

        //Create a new date object from the time in milliseconds of the earthquake
        Date dataObject = new Date(currentEarthquake.getTimeInMilliseconds());

        //Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        //Format the date string(eg: "Mar 3, 1984")
        String formattedDate = formatDate(dataObject);
        //Display the location of the current earthquake in textview
        dateView.setText(formattedDate);

        //Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        //Format the time string(eg: "4.30PM")
        String formattedTime = formatTime(dataObject);
        //Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        //Return the list item view that is now showing the apporiate data
        return listItemView;
    }
}
