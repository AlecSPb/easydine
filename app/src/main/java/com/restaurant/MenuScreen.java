package com.restaurant;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MenuScreen extends ExpandableListActivity {
	//Expandable list is used to display data
    
	ExpandableListAdapter mAdapter;
	
	//DatabaseHelper menuHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);

        Drawable devider = getResources().getDrawable(R.drawable.line);


        registerForContextMenu(getExpandableListView());
        getExpandableListView().setCacheColorHint(5);
        getExpandableListView().setBackgroundResource(R.color.bgcolor);
        getExpandableListView().setPadding(5,20,0,20);
        getExpandableListView().setDivider(devider);
        getExpandableListView().setDividerHeight(10);

        
        getExpandableListView().setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				//Toast.makeText(MenuScreen.this, ((TextView)v).getText().toString(), Toast.LENGTH_LONG).show();
				String s=((TextView)v).getText().toString();
				Intent i =new Intent (MenuScreen.this, OrderScreen.class);
				i.putExtra("order", s);
                startActivity(i);
				return false;
			}
		});

    }
    
   
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        menu.add(0, 0, 0, R.string.app_name);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

    
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        //items are defined here
    	private String[] groups = { "    Starters", "    Soups","    North Indian Food","    Chinese", "    Sandwiches",
                "    Cold Drinks","    Juice","    Pastries" };
        private String[][] children = {
                { "       Veg Manchurian - 85","       Veg Roll - 80","       Paneer Manchurian - 95"},
                {"       Tomato - 75","       Veg Manchaw - 85"},
                {"       Veg Handi - 115","       Veg Makhanwala - 155","       Paneer Kadai - 165","       Butter Nan - 110","       Cheese Kulcha - 60","       Paratha - 50"},
                {"       Hakka Noodles - 95","       Veg Noodles - 85"},
                {"       Cheez Grilled - 55","       Chatni Cheese - 45" },
                {"       Thums UP - 15","       Sprite - 15","       Maza - 15" },
                {"       Mango Juice - 55","       Orange Juice - 45","       Grap Juice - 65","       Apple Juice - 75"},
                {"       Black forest - 65","       White Forest - 65","       Pina Strawbery - 55"}
                
                
        };

        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64);

            TextView textView = new TextView(MenuScreen.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
           
            return textView;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }

}