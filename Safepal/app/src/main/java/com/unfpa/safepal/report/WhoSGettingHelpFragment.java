package com.unfpa.safepal.report;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unfpa.safepal.R;
import com.unfpa.safepal.messages.EMessageDialogFragment;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class WhoSGettingHelpFragment extends Fragment {

    Toolbar wsghToolbar;

//    /**
//     * Next and buttonExit button
//     */
//    Button buttonNext;
//    Button buttonExit;

    static Spinner wsghRelationshipSpinner;
    static RadioButton wsghYesRB;
    static RadioButton wsghSomeelseRb;
    RadioGroup wsghWhoRG;
    static Snackbar wsghFeedbackSnackbar;
    RelativeLayout wsghSpinnerRl;
    TextView wsghEncouragingMessagesTv;


    
    
    public WhoSGettingHelpFragment() {
    }

    public static WhoSGettingHelpFragment newInstance() {
        WhoSGettingHelpFragment fragment = new WhoSGettingHelpFragment();
        return fragment;
    }



    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_whos_getting_help, container, false);

        /***
         * Declaration and assignments of xml user interfaces*
         * */

        //Toolbar of the who's getting help activity
        wsghToolbar = (Toolbar) rootView.findViewById(R.id.wsgh_toolbar);
//        //Abort fab of  who's getting help activity
//        buttonExit = (Button) rootView.findViewById(R.id.exit_app);
//        //Next fab of  who's getting help activity
//        buttonNext = (Button) rootView.findViewById(R.id.finish);
        // choose someone else relationship spinner
        wsghRelationshipSpinner = (Spinner) rootView.findViewById(R.id.wsgh_relationship_spinner);

        wsghSpinnerRl = (RelativeLayout)rootView.findViewById(R.id.wsgh_spinner_rl);
        wsghWhoRG = (RadioGroup)rootView.findViewById(R.id.wsgh_who_rg);
        wsghYesRB = (RadioButton)rootView.findViewById(R.id.wsgh_yes_rb);
        wsghSomeelseRb = (RadioButton)rootView.findViewById(R.id.wsgh_someoneelse_rb);
        wsghEncouragingMessagesTv = (TextView)rootView.findViewById(R.id.wsgh_ecouraging_messages_tv);

//        setSupportActionBar(wsghToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        /**  wsghRelationshipSpinner  **/

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> wsghRSadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.wsgh_someone_else_array,  R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        wsghRSadapter.setDropDownViewResource( R.layout.spinner_dropdown );
        // Apply the (wsghRSadapter to the spinner
        wsghRelationshipSpinner.setAdapter(wsghRSadapter);
        /** ends wsghRelationshipSpinner **/

        loadFeedbackMessages();

        wsghEncouragingMessagesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WsghEncouragingMessagesDialog();
            }
        });

        //exit application
//        buttonExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Build.VERSION.SDK_INT>=21)  getActivity().finishAndRemoveTask();
//                else getActivity().finish();
//
//
//            }
//        });
        //uninstall application
//        buttonExit.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Uri packageURI = Uri.parse("package:com.unfpa.safepal");
//                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
//                startActivity(uninstallIntent);
//                return true;
//            }
//        });
        //go to report form
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(wsghYesRB.isChecked())
//                    startActivity(new Intent(getActivity(), SurvivorIncidentFormActivity.class));
//
//                else if (wsghSomeelseRb.isChecked()){
//                    //checks if the relationship to the survivor has been selected
//                    if (wsghRelationshipSpinner.getSelectedItemPosition() <= 0) {
//                        wsghFeedbackSnackbar = Snackbar.make(view, "what is your relationship to survivor?", Snackbar.LENGTH_LONG);
//                        wsghFeedbackSnackbar.show();
//                        return;
//                    }
//
//                    Intent apifIntent = new Intent(view.getContext(),AnotherPersonIncidentFormActivity.class);
//                    apifIntent.putExtra("relationshipToSurvivor", wsghRelationshipSpinner.getSelectedItem().toString());
//                    startActivity(apifIntent);
//
//                }
//                else{
//                    Toast.makeText(getActivity(), "Who did the incident happen to? Choose one of the options to proceed.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        });

        wsghYesRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                boolean checked = ((RadioButton) view).isChecked();

                // Check which radio button was clicked
                switch(view.getId()) {
                    case R.id.wsgh_yes_rb:
                        if (checked)
                            //startActivity(new Intent(getApplicationContext(), SurvivorIncidentFormActivity.class));
                            // Pirates are the best
                            wsghSpinnerRl.setVisibility(View.GONE);
                        break;
                    case R.id.wsgh_someoneelse_rb:
                        if (checked)
                            wsghSpinnerRl.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        wsghSomeelseRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                boolean checked = ((RadioButton) view).isChecked();

                // Check which radio button was clicked
                switch(view.getId()) {
                    case R.id.wsgh_yes_rb:
                        if (checked)
                            //startActivity(new Intent(getApplicationContext(), SurvivorIncidentFormActivity.class));
                            // Pirates are the best
                            wsghSpinnerRl.setVisibility(View.GONE);
                        break;
                    case R.id.wsgh_someoneelse_rb:
                        if (checked)
                            wsghSpinnerRl.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        return rootView;
    }

    public void  loadFeedbackMessages(){
        //randomly picks a message
        String[] wsghMessagesArray = getResources().getStringArray(R.array.not_your_fault_messages);
        wsghEncouragingMessagesTv.setText(wsghMessagesArray[randMessageIndex(0, wsghMessagesArray.length)].toString());
        //shows the load message in a dialog
        //WsghEncouragingMessagesDialog();
    }
    //random interger to randomly pick messages from arrays
    public static int randMessageIndex(int min, int max) {
        Random randomNum = new Random();
        int index = randomNum.nextInt(max - min);
        return index;
    }
    public void WsghEncouragingMessagesDialog(){

        EMessageDialogFragment emDialog = EMessageDialogFragment.newInstance(
                getString(R.string.not_your_fault_alert_header),
                wsghEncouragingMessagesTv.getText().toString(),
                getString(R.string.close_dialog));
        emDialog.show(this.getFragmentManager(), "encouraging message");
    }
    public void onClickWSGHRadioButton(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.wsgh_yes_rb:
                if (checked)
                    //startActivity(new Intent(getActivity(), SurvivorIncidentFormActivity.class));
                    // Pirates are the best
                    wsghSpinnerRl.setVisibility(View.GONE);
                break;
            case R.id.wsgh_someoneelse_rb:
                if (checked)
                    wsghSpinnerRl.setVisibility(View.VISIBLE);
                break;
        }
    }
    public void onClickWsghIvSpinner(View view){
        wsghRelationshipSpinner.performClick();
    }

    public static boolean areAllFieldsSet(){
        if(wsghYesRB.isChecked() || ( wsghSomeelseRb.isChecked()
        && (wsghRelationshipSpinner.getSelectedItemPosition() >= 1) )){
            return true;
        }else {
            return false;
        }
    }
    
    
}