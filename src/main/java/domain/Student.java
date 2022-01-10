package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private final List<JsonObject> exams = new ArrayList(){};

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        for (Tuple<String, Integer> exam : exams) {
            JsonPair course = new JsonPair("course", new JsonString(exam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(exam.value));
            JsonPair ifPassed = new JsonPair("passed", new JsonBoolean(exam.value >= 3));

            this.exams.add(new JsonObject(course, mark, ifPassed));
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject ans = new JsonObject();
        ans.add(new JsonPair("name", new JsonString(this.name)));
        ans.add(new JsonPair("surname", new JsonString(this.surname)));
        ans.add(new JsonPair("year", new JsonNumber(this.year)));
        ans.add(new JsonPair("exams", new JsonArray(exams.toArray(new Json[0]))));
        return ans;
    }
}