package lat.ta.ujianpemrograman.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import lat.ta.ujianpemrograman.R;

import static lat.ta.ujianpemrograman.utils.Utils.showMessage;

public class InputNameDialog extends AlertDialog.Builder {

    private AlertDialog alertDialog;

    public static void display(Context context) {
        InputNameDialog dialog = new InputNameDialog(context);
        AlertDialog alertDialog = dialog.create();

        dialog.setAlertDialog(alertDialog);
        alertDialog.show();
    }

    private InputNameDialog(@NonNull Context context) {
        super(context);
        setView(R.layout.dialog_input_name);
    }

    private void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        EditText edtName = view.findViewById(R.id.editTextNama);
        Button btnOk =  view.findViewById(R.id.buttonOK);
        btnOk.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            if (! TextUtils.isEmpty(name)) {
                alertDialog.dismiss();
            } else {
                String emptyInput = getContext().getResources()
                        .getString(R.string.warning_empty_input);
                showMessage(getContext(), emptyInput);
            }
        });

        return super.setView(view);
    }
}
