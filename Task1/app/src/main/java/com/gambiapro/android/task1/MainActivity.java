package com.gambiapro.android.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final Map<Integer, Integer> btnIdToDrawableResource;

    static {
        btnIdToDrawableResource = new HashMap<Integer, Integer>();
        btnIdToDrawableResource.put(R.id.radWater, R.drawable.water);
        btnIdToDrawableResource.put(R.id.radTea, R.drawable.tea);
        btnIdToDrawableResource.put(R.id.radPinga, R.drawable.pinga);
        btnIdToDrawableResource.put(R.id.radBeer, R.drawable.beer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial selection
        //RadioButton b = (RadioButton) findViewById(R.id.radWater);
        //b.setChecked(true);
        //b.callOnClick();
    }

    /*
     * Listeners
     */

    public void onRadioClick(View view) {
        RadioButton checkedRadio = getCheckedRadioButton();
        if (checkedRadio != null) {
            setImg(checkedRadio.getId());
        }
    }

    public void onBtnConfirmClick(View view) {
        RadioButton checkedRadio = getCheckedRadioButton();
        showMsg(checkedRadio);
    }

    /*
     * Actions
     */

    private RadioButton getCheckedRadioButton() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radGrpBeverages);
        return (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
    }

    private void setImg(int radId) {
        ImageView imageView = (ImageView) findViewById(R.id.imgViewer);
        imageView.setImageResource(btnIdToDrawableResource.get(radId));
    }

    private void showMsg(RadioButton checkedRadio) {
        String msg;
        if (checkedRadio == null) {
            msg = getString(R.string.main_msgNotChosen);
        } else {
            msg = String.format(getString(R.string.main_msgCheers), checkedRadio.getText().toString());
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
