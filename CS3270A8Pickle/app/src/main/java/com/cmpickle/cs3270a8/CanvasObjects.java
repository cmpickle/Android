package com.cmpickle.cs3270a8;


/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 3/1/2017.
 */

public class CanvasObjects {

    public static class Course {

        public Course(String id, String name, String course_code, String start_at, String end_at) {
            this.id = id;
            this.name = name;
            this.course_code = course_code;
            this.start_at = start_at;
            this.end_at = end_at;
        }

        public String id;
        public String sis_course_id;
        public String name;
        public String course_code;
        public String account_id;
        public String start_at;
        public String end_at;
        public Enrollment[] enrollments;
        public Calendar calendar;
        public String syllabus_body;
        public String needs_grading_count;
        public Term term;
    }

    public class Term {

        public String id;
        public String name;
        public String start_at;
        public String end_at;
    }

    public class Calendar {

        public String ics;
    }

    public class Enrollment {

        public String type;
        public String role;
        public String computed_final_score;
        public String computed_current_score;
        public String computed_final_grade;
    }

    public class Assignments {

        public String name;
    }


}
