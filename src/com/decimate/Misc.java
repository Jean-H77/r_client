package com.decimate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.List;

public class Misc {

    public static String formatNumber(double number) {
        return NumberFormat.getInstance().format(number);
    }

    private static String OS = null;

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static <T> T getRandomElement(T[] arr) {
        return arr[random(arr.length - 1)];
    }

    public static <T> T getRandomElement(List<T> list) {
        return list.get(random(list.size() - 1));
    }

    public static int random(int range) {
        return (int) (Math.random() * (range + 1));
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static String getUnderscoredNumber(int number) {
        String string = String.valueOf(number);
        for (int i = string.length() - 3; i > 0; i -= 3) {
            string = string.substring(0, i) + "_" + string.substring(i);
        }
        return string;
    }

    public static <T>T copyObject(Object object){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(object).getAsJsonObject();
        return gson.fromJson(jsonObject,(Type) object.getClass());
    }

}