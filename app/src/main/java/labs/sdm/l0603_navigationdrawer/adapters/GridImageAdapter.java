/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.sdm.l0603_navigationdrawer.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import labs.sdm.l0603_navigationdrawer.R;

/*
* Adapter that maps a given array of Drawables (provided as a resource) and a GridView.
* */
public class GridImageAdapter extends BaseAdapter {

    // Hold reference to the Context
    Context context;
    // Array of Drawables obtained from a resource
    TypedArray images;

    /*
    * Gets the required Context and obtains the array of Drawables from a resource.
    * */
    public GridImageAdapter(Context context) {
        this.context = context;
        // Get the array of Drawables, which is already defined as a resource
        images = context.getResources().obtainTypedArray(R.array.grid_list);
    }

    /*
    * Gets the number of Drawables available in the array.
    * */
    @Override
    public int getCount() {
        return images.length();
    }

    /*
    * Gets the Drawable available in a given position in the array.
    * */
    @Override
    public Object getItem(int position) {
        return images.getDrawable(position);
    }

    /*
    * Gets the Id of the Drawable at a given position in the array.
    * */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
    * Gets the View to display the data in the required position of the array.
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        // Views are recycled while scrolling through Lists and Grids, so reuse them
        if (convertView == null) {
            // Get the existing ImageView to display the Drawable
            imageView = new ImageView(context);
        }
        else {
            // Create a new ImageView to display the Drawable
            imageView = (ImageView) convertView;
        }
        // Assign the Drawable at the given position of the array to the obtained ImageView
        imageView.setImageDrawable(images.getDrawable(position));
        // Return the ImageView built
        return imageView;
    }

    /*
    * It is necessary to recycle TypedArrays to be re-used later.
    * The Activity/Fragment using this Adapter should call this method when no longer used.
    * */
    public void recycle() {
        images.recycle();
    }
}
