package com.example.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TelephonyInfor  implements Serializable {
    private String displayName;
    private String phoneNumber;

    public TelephonyInfor() {
    }

    public TelephonyInfor(String displayName, String phoneNumber) {
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarrier() {
        // Clean phone number - remove all non-digit characters
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Get the first 3 digits (some carriers have 2-digit prefixes)
        if (cleanNumber.length() >= 3) {
            String prefix3 = cleanNumber.substring(0, 3);
            String prefix2 = cleanNumber.substring(0, 2);

            // Viettel prefixes
            if (prefix3.matches("032|033|034|035|036|037|038|039|086|096|097|098")) {
                return "Viettel";
            }
            // MobiFone prefixes
            else if (prefix3.matches("070|076|077|078|079|089|090|093") ||
                    prefix2.equals("07") || prefix2.equals("09")) {
                return "MobiFone";
            }
            // Vinaphone prefixes
            else if (prefix3.matches("081|082|083|084|085|088|091|094") ||
                    prefix2.equals("08") || prefix2.equals("09")) {
                return "Vinaphone";
            }
        }
        return "Other";
    }

    @NonNull
    @Override
    public String toString() {
        return this.displayName + "\n" + this.phoneNumber;
    }
}
