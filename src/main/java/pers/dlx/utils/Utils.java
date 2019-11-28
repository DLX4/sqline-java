package pers.dlx.utils;

public class Utils {


    public static long fnv_64(String input) {
        return FnvHash.fnv1a_64(input);
    }

    public static long fnv_64_lower(String key) {
        return FnvHash.fnv1a_64_lower(key);
    }

    public static long fnv_32_lower(String key) {
        return FnvHash.fnv_32_lower(key);
    }

}
