package com.m_windle.xyz_conference;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.m_windle.xyz_conference.database.ConferenceContract;
import com.m_windle.xyz_conference.people.AttendeeFragment;
import com.m_windle.xyz_conference.people.PeopleFragment;
import com.m_windle.xyz_conference.people.SpeakerFragment;
import com.m_windle.xyz_conference.people.SponsorFragment;
import com.m_windle.xyz_conference.schedule.GenScheduleFragment;
import com.m_windle.xyz_conference.schedule.MyScheduleFragment;
import com.m_windle.xyz_conference.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            ScheduleFragment.OnFragmentInteractionListener,
                MyScheduleFragment.OnFragmentInteractionListener,
                GenScheduleFragment.OnFragmentInteractionListener,
            PeopleFragment.OnFragmentInteractionListener,
                SpeakerFragment.OnFragmentInteractionListener,
                AttendeeFragment.OnFragmentInteractionListener,
                SponsorFragment.OnFragmentInteractionListener,
            TwitterFragment.OnFragmentInteractionListener,
            SurveyFragment.OnFragmentInteractionListener,
            LeaderboardFragment.OnFragmentInteractionListener,
            MapFragment.OnFragmentInteractionListener{

    private static final String PREF_NAME = "user_info";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        prefs = getSharedPreferences(PREF_NAME, 0);

        TextView username = (TextView) navigationView.findViewById(R.id.username);
        username.setText(prefs.getString("username", "T. Sparkle"));

        TextView email = (TextView) navigationView.findViewById(R.id.email);
        email.setText(prefs.getString("email", "t.strong@canterlot.eq"));

        getSupportActionBar().setTitle("Schedule");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ScheduleFragment sf = new ScheduleFragment();
        ft.add(R.id.fragFrame, sf, "sched");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.scheduleTab) {
            getSupportActionBar().setTitle("Schedule");
            ScheduleFragment sf = new ScheduleFragment();
            ft.replace(R.id.fragFrame, sf, "sched");
        } else if(id == R.id.peopleTab){
            getSupportActionBar().setTitle("People");
            PeopleFragment pf = new PeopleFragment();
            ft.replace(R.id.fragFrame, pf, "people");
        } else if (id == R.id.twitterTab) {
            getSupportActionBar().setTitle("Twitter");
            TwitterFragment tf = new TwitterFragment();
            ft.replace(R.id.fragFrame, tf, "twitter");
        } else if (id == R.id.surveyTab) {
            getSupportActionBar().setTitle("Surveys");
            SurveyFragment survFrag = new SurveyFragment();
            ft.replace(R.id.fragFrame, survFrag, "survey");
        } else if (id == R.id.leaderboardTab) {
            getSupportActionBar().setTitle("Leaderboards");
            LeaderboardFragment lf = new LeaderboardFragment();
            ft.replace(R.id.fragFrame, lf, "leaderboard");
        } else if (id == R.id.mapsTab) {
            getSupportActionBar().setTitle("Maps & Points of Interest");
            MapFragment mf = new MapFragment();
            ft.replace(R.id.fragFrame, mf, "map");
        }

        //sf = (ScheduleFragment) getSupportFragmentManager().findFragmentByTag("sched");

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
