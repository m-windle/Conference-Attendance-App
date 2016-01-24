package com.m_windle.xyz_conference;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SurveyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SurveyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurveyFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static SurveyFragment newInstance() {
        SurveyFragment fragment = new SurveyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_survey, container, false);
        final EditText text = (EditText)view.findViewById(R.id.txtInput);
        final CheckBox willReturn = (CheckBox)view.findViewById(R.id.chkReturn);

        // Set OnClickListener to submit button
        Button submit = (Button)view.findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Get values of user input
                String input = text.getText().toString();
                Boolean returning = willReturn.isChecked();

                try {
                    // Get File
                    FileOutputStream output = view.getContext().openFileOutput("surveyResponses.txt", view.getContext().MODE_APPEND);

                    // Get Timestamp
                    String timeStamp = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
                    output.write(("[" + timeStamp + "]\t").getBytes());

                    // Create jsonObject for Survey response
                    JSONObject surveyResponse = new JSONObject();
                    surveyResponse.put("Returning", returning);
                    surveyResponse.put("Review", input);

                    // Write object to file
                    output.write((surveyResponse.toString() + "\r\n").getBytes());
                    //output.write(surveyResponse.toString().getBytes());

                    // Flush and Close to be safe (Close calls flush as well)
                    output.flush();
                    output.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

                // Confirmation message
                Toast.makeText(getActivity().getApplicationContext(), "Thank you! Response recorded", Toast.LENGTH_SHORT).show();

                // Reset Fields
                text.getText().clear();
                if(willReturn.isChecked())
                    willReturn.toggle();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
