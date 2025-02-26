package utils;

public class Misc {

    public static String formatToRs(int damage) {
        String formatedDamage = "" + damage;
        if (damage > 9999 && damage <= 999999) {
            int damageD = damage / 1000;

            formatedDamage = damageD + "K";
        } else if (damage > 999999) {
            double damageD = (double) damage / 1000000;
            formatedDamage = "" + Misc.round(damageD, 1) + "M";
        }

        return formatedDamage;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getTimeLeft(int seconds) {
        int hours = 0;
        int minutes = 0;
        int secondsLeft = 0;

        String time = "";
        if (seconds > 3600) {
            hours = seconds / 3600;
            time = hours + "h";
        }
        int value1 = 0;
        value1 = seconds - (hours * 3600);
        if (value1 > 0) {
            minutes = value1 / 60;
            if (minutes > 0) {
                if (!time.isEmpty()) {
                    time = time + " ";
                }
                time = time + minutes + "m";
            }
        }
        int value2 = value1 - (minutes * 60);
        if (value2 >= 0 && minutes == 0) {
            secondsLeft = value2;


            if (!time.isEmpty()) {
                time = time + " ";
            }
            time = time + secondsLeft + "s";
        }
        return time;
    }


}
