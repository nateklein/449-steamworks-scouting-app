package robo.scoutingappmockup;

/*
 * SteamworksDatabase is a class that stores all data being collected.
 *
 * Used for preventing data loss between page flips and for submission.
 *
 * Created by Nate on 10/10/2017.
 */

public class SteamworksDatabase {

    // All data being collected
    public String scoutName;
    public int matchNumber;
    public int teamNumber;
    public boolean noShow;
    public boolean noAuto;
    public boolean movedForward;
    public int autoGear;
    public int autoLowFuel;
    public int autoHighFuel;
    public int teleopGears;
    public int teleopHighFuel;
    public int teleopLowFuel;
    public int dead;
    public boolean achievedNothing;
    public int climb;
    public int catchTime;
    public int climbTime;
    public String comment;

    // Default entries
    public SteamworksDatabase() {
        scoutName = "";
        matchNumber = 0;
        teamNumber = 0;
        noShow = false;
        noAuto = false;
        movedForward = false;
        autoGear = 0;
        autoLowFuel = 0;
        autoHighFuel = 0;
        teleopGears = 0;
        teleopHighFuel = 0;
        teleopLowFuel = 0;
        dead = 0;
        achievedNothing = false;
        climb = 0;
        catchTime = 0;
        climbTime = 0;
        comment = "";
    }

    public String toString() {
        return scoutName+','+goToPrematch.getMatchNum()+','+goToPrematch.getTeamNum()+','+(noShow ? 1 : 0)+','+(noAuto ? 1 : 0)+','+(movedForward ? 1 : 0)+','+
                autoGear+','+autoLowFuel+','+autoHighFuel+','+teleopGears+','+teleopHighFuel+','+teleopLowFuel+','+dead+','+
                (achievedNothing ? 1 : 0)+','+climb+','+catchTime+','+climbTime+','+comment;
    }


    public static String checkData() {
        String errors = "";
        if (MainActivity.db.scoutName.equals("")) {
            errors += "Scout name cannot be empty\n";
        }
        if (MainActivity.db.matchNumber == 0) {
            errors += "Match number cannot be empty\n";
        }
        if (MainActivity.db.teamNumber == 0) {
            errors += "Team number cannot be empty\n";
        }
        if (MainActivity.db.autoGear == 0) {
            errors += "Select an option for auto gear\n";
        }
        if (MainActivity.db.dead == 0) {
            errors += "Select an option for deadness\n";
        }
        if (MainActivity.db.climb == 0) {
            errors += "Select an option for climbing\n";
        }
        return errors;
    }
}