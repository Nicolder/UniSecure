package Unisecure;

import Unisecure.view.TelaOcorrencia;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class VerDados {
    public static void main(String[] args) {
        Preferences prefs = Preferences.userRoot().node("unisecure_login");

        try {
            for (String key : prefs.keys()) {
                String value = prefs.get(key, "n/a");
                System.out.println(key + " = " + value);
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        TelaOcorrencia acidente = new TelaOcorrencia();

        System.out.println(acidente.getOcorrencias());
    }
}
