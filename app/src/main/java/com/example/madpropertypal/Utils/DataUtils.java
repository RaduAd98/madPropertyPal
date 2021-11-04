package com.example.madpropertypal.Utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.madpropertypal.R;
import com.google.android.material.textfield.TextInputLayout;

public class DataUtils {
    public static void spinnerAdapter(Context context, Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(context, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setMinMaxValue(NumberPicker numberPicker) {
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(9);
        numberPicker.setWrapSelectorWheel(true);
    }

    public static void checkTextBoxInput(Context context, String string, TextInputLayout textInputLayout) {
        if (string.isEmpty()) {
            textInputLayout.setError(context.getResources().getString(R.string.error_message));
        } else {
            textInputLayout.setError(null);
        }
    }

    public static void checkTextBoxInput(Context context, String string, TextView textInputLayout) {
        if (string.isEmpty()) {
            textInputLayout.setError(context.getResources().getString(R.string.error_message));
        } else {
            textInputLayout.setError(null);
        }
    }

    public static void checkSpinnerInput(Spinner spinner) {
        if (spinner.getSelectedItemPosition() == 0) {
            TextView spinnerSetTextError = (TextView) spinner.getSelectedView();
            spinnerSetTextError.setText(R.string.error_message);
            spinnerSetTextError.setTextColor(Color.RED);
        }
    }
}
