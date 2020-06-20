package lat.ta.ujianpemrograman.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;

public class InputNameDialog extends AlertDialog.Builder {

    private AlertDialog alertDialog;
    private Runnable mRunnable;

    public static InputNameDialog display(Context context) {
        InputNameDialog dialog = new InputNameDialog(context);
        dialog.setCancelable(false);
        AlertDialog alertDialog = dialog.create();

        dialog.setAlertDialog(alertDialog);
        alertDialog.show();

        return dialog;
    }

    public static void display(Context context, Runnable runnable) {
        display(context).setRunnable(runnable);
    }

    private InputNameDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_input_name, null);
        setView(view);
    }

    private void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
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
                App.setSharedPreferences(App.KEY_USERNAME, name);
                alertDialog.dismiss();
                mRunnable.run();
            } else {
                String emptyInput = getContext().getResources()
                        .getString(R.string.warning_empty_input);
                Toast.makeText(getContext(), emptyInput, Toast.LENGTH_SHORT).show();
            }
        });

        return super.setView(view);
    }
}
