package com.parse.starter;

import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

public class Graph extends Activity {

	int val_count = 10;
	Context context = this;
	Bundle bundle = getIntent().getExtras();
	Intent graphIntent = getIntent();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("graph_Activity", "STARTED");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		
		 if (getIntent().getIntExtra("valcount", 0) != 0 && (getIntent().getFloatExtra("value0", 4) != 0)){
		        int val0 = bundle.getInt("valcount");
		        Log.d("NUMBERIWANTPLEASE", Integer.toString(val0));

		  } 
//		GraphViewData[] data = new GraphViewData[val_count];
//		for (int i=0; i< val_count; i++){
//			Log.d("NUMBER", "value" + Integer.toString(i));
//			Log.d("NUMBER_VAL", Integer.toString(graphIntent.getExtras().getInt("valcount")) );
//			data[i] = new GraphViewData(i, graphIntent.getFloatExtra("value" + Integer.toString(i), (float) 1.0f));
//		}
//		GraphViewSeries light_graph_series = new GraphViewSeries(data);
//		GraphView lightGraphView = new LineGraphView(context, "GraphViewDemo");
//		lightGraphView.addSeries(light_graph_series);
//		LinearLayout layout = (LinearLayout) findViewById(R.id.lightLinearLayout);
//		layout.addView(lightGraphView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph, menu);
		return true;
	}

}
