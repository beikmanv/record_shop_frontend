package com.northcoders.mvvmhttprequestswithretrofit.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class BindingUtils {

    @BindingAdapter("android:text")
    public static void setIntValue(EditText editText, int value) {
        editText.setText(String.valueOf(value));
    }

    @BindingAdapter("android:text")
    public static void setDoubleValue(EditText editText, double value) {
        editText.setText(String.valueOf(value));
    }

    @BindingAdapter("android:textAttrChanged")
    public static void setIntValueChanged(EditText editText, final InverseBindingListener inverseBindingListener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                inverseBindingListener.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getIntValue(EditText editText) {
        return Integer.parseInt(editText.getText().toString());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static double getDoubleValue(EditText editText) {
        return Double.parseDouble(editText.getText().toString());
    }
}
