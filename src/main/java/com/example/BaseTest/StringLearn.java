package com.example.BaseTest;

public class StringLearn {
    String  c = new String("12");
    StringBuffer a = new StringBuffer("1").append("2");
    /**
     public final class String
     implements java.io.Serializable, Comparable<String>, CharSequence {
//     存储空间
    private final char value[];

//    hash值(尚未理解，留待填坑)
    private int hash; // Default to 0

//    序列号(尚未理解)
    private static final long serialVersionUID = -6849794470754667710L;

     **/



    /**
    @Override
    public synchronized StringBuffer append(Object obj) {
        toStringCache = null;
        super.append(String.valueOf(obj));
        return this;
    }
    StringBuilder b = new StringBuilder("2").append("2");
    @Override
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }
    public AbstractStringBuilder append(String str) {
        if (str == null)
            return appendNull();
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }
    **/

}
