package com.m_windle.xyz_conference.people;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import android.widget.ListView;

import com.m_windle.xyz_conference.ExpandableAdapter;
import com.m_windle.xyz_conference.R;
import com.m_windle.xyz_conference.database.ConferenceDbHelper;
import com.m_windle.xyz_conference.database.ConferenceContract.PresenterTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpeakerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpeakerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeakerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    // ExpandableListView Vars
    private ExpandableListView lvSpeakers;
    private ExpandableAdapter listAdapter;
    private List<String> listHeaders;
    private HashMap<String, List<String>> listData;

    private OnFragmentInteractionListener mListener;

    public static SpeakerFragment newInstance(int sectionNumber) {
        SpeakerFragment fragment = new SpeakerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SpeakerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Views
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);
        lvSpeakers = (ExpandableListView)view.findViewById(R.id.lvSpeakers);

        prepareData(view);

        listAdapter = new ExpandableAdapter(view.getContext(), listHeaders, listData);
        lvSpeakers.setAdapter(listAdapter);

/*        // Listview Group expanded listener
        lvSpeakers.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listHeaders.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        lvSpeakers.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listHeaders.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        return view;
    }

    private void prepareData(View view){
        // DB Access Vars
        ConferenceDbHelper dbHelper = ConferenceDbHelper.getInstance(view.getContext());
        Cursor c = dbHelper.getAllPresenters();

        // Data
        listHeaders = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();


        if(c.moveToFirst()){
            String fname, lname, fullName;

            do{
                // Get header
                fname = c.getString(c.getColumnIndexOrThrow(PresenterTable.COLUMN_NAME_FNAME));
                lname = c.getString(c.getColumnIndexOrThrow(PresenterTable.COLUMN_NAME_LNAME));
                fullName = fname + " " + lname;
                listHeaders.add(fullName);

                // Get details (Data)
                List<String> tempData = new ArrayList<String>();
                tempData.add(c.getString(c.getColumnIndexOrThrow(PresenterTable.COLUMN_NAME_AFFILIATION)));
                tempData.add(c.getString(c.getColumnIndexOrThrow(PresenterTable.COLUMN_NAME_EMAIL)));
                tempData.add(c.getString(c.getColumnIndexOrThrow(PresenterTable.COLUMN_NAME_BIO)));
                listData.put(fullName, tempData);

                c.moveToNext();
            }while(!c.isAfterLast());
        }
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
/*        try {
            PresenterDataSource pDataSource = new PresenterDataSource(getContext());
            Presenter created = pDataSource.createPresenter("John", "Smith", "abc@123.com", "He is a cool man", "No affiliation");
            // ArrayList<Presenter> presenters = pDataSource.getAllPresenters();

            Cursor pCursor = pDataSource.presenterCursor();
            pCursor.moveToFirst();

            String[] fromCols = {PresenterTable.COLUMN_NAME_FNAME,  PresenterTable.COLUMN_NAME_EMAIL};
            int[] toViews = {R.id.lvSpeakers};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                    R.layout.fragment_people, pCursor, fromCols, toViews, 0);
            listView = (ListView) getView().findViewById(R.id.lvSpeakers);
            listView.setAdapter(adapter);

            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
