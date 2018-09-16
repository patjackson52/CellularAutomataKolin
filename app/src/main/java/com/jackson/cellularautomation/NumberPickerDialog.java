package com.jackson.cellularautomation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPickerDialog extends DialogFragment {
    static int ruleSet = 30;
    static int blockSize = 10;

    interface OnValueChangeListener {
        void onChanged(int rule, int blockSize);
    }

    private OnValueChangeListener valueChangeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View root = LayoutInflater.from(getActivity()).inflate(R.layout.num_picker, null);
        final NumberPicker rulePicker = root.findViewById(R.id.rule_picker);
        final NumberPicker blockSizePicker = root.findViewById(R.id.pixel_size_picker);

        rulePicker.setMinValue(1);
        rulePicker.setMaxValue(256);
        rulePicker.setValue(ruleSet);

        blockSizePicker.setMinValue(1);
        blockSizePicker.setMaxValue(100);
        blockSizePicker.setValue(blockSize);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Options");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ruleSet = rulePicker.getValue();
                blockSize = blockSizePicker.getValue();
                valueChangeListener.onChanged(
                        rulePicker.getValue(), blockSizePicker.getValue());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setView(root);
        return builder.create();
    }

    public void setValueChangeListener(OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}