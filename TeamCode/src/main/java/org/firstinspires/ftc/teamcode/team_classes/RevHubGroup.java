package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.revextensions2.ExpansionHubEx;

public class RevHubGroup {
    //Properties
    public ExpansionHubEx[] Hubs;
    public int HubCount;

    //Constructor
    RevHubGroup(ExpansionHubEx[] InputHubs){
        Hubs = InputHubs;
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        Hubs[0] = Hmap.get(ExpansionHubEx.class, "Expansion Hub 1");
        Hubs[1] = Hmap.get(ExpansionHubEx.class, "Expansion Hub 2");
        Tm.addData("RevHub Initialization","Complete");
        Tm.update();
    }
}
