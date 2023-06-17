package com.driver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public static int minBalance = 5000;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, minBalance);
        if(balance < minBalance) {
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        if(!tradeLicenseId.equals(tradeLicenseId.toUpperCase())) {
            throw new Exception("Valid License can not be generated");
        }
        int size = tradeLicenseId.length();
        HashMap<Character,Integer> map = new HashMap<>();

        getFrequencyMap(size, map);
        //odd even
        if(Collections.max(map.values()) > size/2) {
            throw new Exception("Valid License can not be generated");
        }

        while (!isValidTradeId()) {
            List<Character> list = tradeLicenseId.chars()
                    .mapToObj(e -> (char)e)
                    .collect(Collectors.toList());


            int arr[] = new int[list.size()];
            int index = 0; //array 0,2,4,6,8,10,12,14,16
            int index2 = 1;
            for(var entry:map.entrySet()) { // a:5, b:4, c:7, d:7
                int freq = entry.getValue();
                int i=0;
                for(;i<freq && index<size;i++) { //4
                    arr[index] = entry.getKey();
                    index+=2;
                }
                if(index >= size) {
                    for(;i<freq && index2<size;i++) {
                        arr[index2] = entry.getKey();
                        index2+=2;
                    }
                }
            }

            //Collections.shuffle(list);
            StringBuilder sb = new StringBuilder();

            // Appends characters one by one
            for (Character ch : list) {
                sb.append(ch);
            }

            String tradeId = sb.toString();
            this.tradeLicenseId = tradeId;
        }

        return;

        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
    }

    private void getFrequencyMap(int size, HashMap<Character, Integer> map) {
        for(int i = 0; i< size; i++) {
            if(map.containsKey(tradeLicenseId.charAt(i))) {
                int value = map.get(tradeLicenseId.charAt(i));
                map.put(tradeLicenseId.charAt(i), value+1 );
            } else {
                map.put(tradeLicenseId.charAt(i), 1);
            }
        }
    }

    private boolean isValidTradeId() {
        int size = tradeLicenseId.length();
        for(int i = 0, j = 1; i< size && j< size; i++, j++) {
            if(((Character)tradeLicenseId.charAt(i)).equals((Character) tradeLicenseId.charAt(j))) {
                break;
            }
            if(j== size -1) {
                return true;
            }
        }
        return false;
    }

}
