package com.roboraider75.frcscouter2019.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.roboraider75.frcscouter2019.R;
import com.roboraider75.frcscouter2019.model.SearchResults;

import java.util.ArrayList;

/**
 * Created by Amaury Valdes on 2/28/2018.
 */

public class SearchMatchAdapter extends ArrayAdapter<SearchResults> {

  private static final String TAG = "SearchMatchAdapter";

  public SearchMatchAdapter(Context context, ArrayList<SearchResults> matches) {
    super(context, 0, matches);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    SearchResults match = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_details, parent, false);
    }
    // Lookup view for data population
    TextView teamNumber = convertView.findViewById(R.id.searchTeamID);
    TextView matchNumber = convertView.findViewById(R.id.searchMatchID);
    ImageView alliance = convertView.findViewById(R.id.searchAllianceID);

    // Populate the data into the template view using the data object
    teamNumber.setText(String.valueOf(match.getTeamNumber()));
    matchNumber.setText(String.valueOf(match.getMatchNumber()));
    if (match.getIsRed() == 1) {
      alliance.setImageDrawable(alliance.getResources().getDrawable(R.drawable.flag_red));
    } else {
      alliance.setImageDrawable(alliance.getResources().getDrawable(R.drawable.flag_blue));
    }

    // Return the completed view to render on screen
    return convertView;
  }
}
