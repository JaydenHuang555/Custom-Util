package jay.util;

import jay.util.hashtable.HashTable;
import jay.util.math.Math;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        HashTable<String, String> table = new HashTable<>();
        table.put("test", "a");
        table.put("test", "1");
        table.put("test", "2");
        table.put("test", "3");

        for(HashTable.Entry entry : table){
            System.out.println("key: "+entry.key());
            System.out.println("val: "+entry.val());
        }


    }
}