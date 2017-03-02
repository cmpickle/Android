package com.cmpickle.restpractice;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.cmpickle.restpractice.CanvasObjects.Course;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    @BindView(R.id.fab)
    protected FloatingActionButton btnListCourses;

//    @BindView(R.id.button)
//    protected Button btnListCourses;

    @BindView(R.id.list)
    protected ListView lsvCourses;

    protected ArrayAdapter<String> courseAdapter;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        courseAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        lsvCourses.setAdapter(courseAdapter);

        btnListCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetCanvasCourses().execute("");
            }
        });

        return view;
    }

    public class GetCanvasCourses extends AsyncTask<String, Integer, String> {

        String AUTH_TOKEN = Authorization.AUTH_TOKEN;
        String rawJson = "";

        @Override
        protected String doInBackground(String... params) {
            Log.d(PlaceholderFragment.class.getName(), "In AsyncTask getCanvasCourses");
            try {
                URL url = new URL("https://weber.instructure.com/api/v1/courses");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
                conn.connect();
                int status = conn.getResponseCode();
                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        rawJson = br.readLine();
                        Log.d(PlaceholderFragment.class.getName(), "raw Json String Length = " + rawJson.length());
                        Log.d(PlaceholderFragment.class.getName(), "raw Json first 256 chars: " + rawJson.substring(0, 256));
                        Log.d(PlaceholderFragment.class.getName(), "raw Json last 256 chars: " + rawJson.substring(rawJson.length()-256, rawJson.length()));
                }
            } catch (MalformedURLException e) {
                Log.d(PlaceholderFragment.class.getName(), e.getMessage());
            } catch (IOException e) {
                Log.d(PlaceholderFragment.class.getName(), e.getMessage());
            } catch (Exception e) {
                Log.d(PlaceholderFragment.class.getName(), e.getMessage());
            }
            return rawJson;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            courseAdapter.clear();
            try {
                Course[] courses = jsonParse(result);
                for(Course course: courses) {
                    Log.d(PlaceholderFragment.class.getName(), "adding course to list: " + course.name);
                    if(course.name != null)
                        courseAdapter.add(course.name);
                }
            } catch (Exception e) {
                Log.d(PlaceholderFragment.class.getName(), e.getMessage());
            }
        }

        private Course[] jsonParse(String rawJson) {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();

            Course[] courses = null;

            try {
                courses = gson.fromJson(rawJson, Course[].class);
                Log.d(PlaceholderFragment.class.getName(), "Number of courses returned is: " + courses.length);
                Log.d(PlaceholderFragment.class.getName(), "First Course returned is: " + courses[0].name);
            } catch (Exception e) {
                Log.d(PlaceholderFragment.class.getName(), e.getMessage());
            }

            return courses;
        }
    }
}
