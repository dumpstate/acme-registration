package com.dumpstate.acme.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.classowl.app.R;

import junit.framework.Assert;

import java.util.List;

/**
 * ArrayAdapter carrying items of type Item. Introduced, in order to display
 * default 'hint' value on the spinner, if no value has been selected.
 */
public class HintSpinnerAdapter<T> extends ArrayAdapter<HintSpinnerAdapter.Item<T>> {

    private static final int HINT_LOCATION = 0;

    /**
     * Adapter Item, that apart from value to display, carries the information,
     * whether the item is a hint. Used to setting proper text color. Has additional
     * mObject value, for carrying arbitrary value.
     */
    public static class Item<T> {
        private final T mObject;
        private final String mLabel;
        private final boolean mIsHint;

        public Item(final String text, final T object) {
            mLabel = text;
            mIsHint = false;
            mObject = object;
        }

        public Item(final String text, final boolean isHint, final T object) {
            mLabel = text;
            mIsHint = isHint;
            mObject = object;
        }

        public String getText() {
            return mLabel;
        }

        public boolean isHint() {
            return mIsHint;
        }

        public T getValue() {
            return mObject;
        }

        @Override
        public String toString() {
            return mLabel;
        }

    }

    /**
     * Helper method, that is setting the HintSpinnerAdapter object as an adapter,
     * for a given Spinner instance.
     *
     * @param context context, on which adapter is working
     * @param spinner Spinner instance that adapter will be attached to
     * @param items items to display
     * @param hint 'hint' value to display as default
     */
    public static <T> void setAdapter(
            final Context context,
            final Spinner spinner,
            final String hint,
            final List<Item<T>> items) {
        Assert.assertNotNull(spinner);
        Assert.assertNotNull(items);
        Assert.assertNotNull(hint);

        // adding hint item, at the hint location
        items.add(HINT_LOCATION, new Item<T>(hint, true, null));

        final HintSpinnerAdapter<T> adapter =
                new HintSpinnerAdapter<T>(
                        context,
                        R.layout.spinner_item,
                        items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    /**
     * Default and only constructor.
     */
    public HintSpinnerAdapter(Context context, int resource, List<Item<T>> items) {
        super(context, resource, items);
    }

    /**
     * Overriding, because of the workaround for providing default text, when
     * no item is selected. That's not the most efficient solution - the views
     * are not using convert view while being created. The best would be to
     * override a spinner.
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        final View view;
        if(position == HINT_LOCATION) {
            final TextView textView = new TextView(getContext());
            // hiding the hint and setting height to 0
            textView.setVisibility(View.GONE);
            textView.setHeight(0);
            view = textView;
        } else {
            view =super.getDropDownView(position, null, parent);
        }
        return view;
    }
}