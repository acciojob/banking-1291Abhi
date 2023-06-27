package com.driver;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance<5000)
            throw new Exception("Insufficient Balance");
        this.tradeLicenseId=tradeLicenseId;
        validateLicenseId();

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }






    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int size=tradeLicenseId.length();
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<size;i++){
            if(hm.containsKey(tradeLicenseId.charAt(i)))
                hm.put(tradeLicenseId.charAt(i),hm.get(tradeLicenseId.charAt(i))+1);
            else
                hm.put(tradeLicenseId.charAt(i),1);
        }
        if(Collections.max(hm.values())>(size+1)/2)
            throw new Exception("Valid License can not be generated");
        while (!isValid(size)) {
            List list=Arrays.asList(tradeLicenseId.toCharArray());
            Collections.shuffle(list);
            tradeLicenseId=list.toString();
        }

    }

    private boolean isValid(int size) {
        for(int i = 0, j = 1; i< size && j< size; i++,j++){
            if(((Character)tradeLicenseId.charAt(i)).equals((Character)tradeLicenseId.charAt(j)))
                break;
            if(j== size -1)
                return true;
        }
        return false;
    }

}
