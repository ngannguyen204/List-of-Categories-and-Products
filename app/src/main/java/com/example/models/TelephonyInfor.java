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
        // Clean phone number - remove all non-digit characters and country code
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Remove country code (84) if present
        if (cleanNumber.startsWith("84")) {
            cleanNumber = cleanNumber.substring(2);
        }

        // Get the first 3 digits (some carriers have 2-digit prefixes)
        if (cleanNumber.length() >= 3) {
            String prefix3 = cleanNumber.substring(0, 3);
            String prefix2 = cleanNumber.substring(0, 2);

            // Viettel prefixes (032-039, 086, 096-098)
            if (prefix3.matches("032|033|034|035|036|037|038|039|086|096|097|098")) {
                return "Viettel";
            }
            // MobiFone prefixes (070, 076-079, 089, 090, 093)
            else if (prefix3.matches("070|076|077|078|079|089|090|093")) {
                return "MobiFone";
            }
            // Vinaphone prefixes (081-085, 088, 091, 094)
            else if (prefix3.matches("081|082|083|084|085|088|091|094")) {
                return "Vinaphone";
            }
            // Vietnamobile (052, 056, 058, 092)
            else if (prefix3.matches("052|056|058|092")) {
                return "Vietnamobile";
            }
            // Gmobile (059, 099)
            else if (prefix3.matches("059|099")) {
                return "Gmobile";
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
