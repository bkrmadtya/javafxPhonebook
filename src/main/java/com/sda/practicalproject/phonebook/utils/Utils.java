package com.sda.practicalproject.phonebook.utils;

import javafx.application.HostServices;

public class Utils {
    private static HostServices hostServices;

    public static void setHostServices(HostServices hostService) {
        if (hostServices == null) {
            hostServices = hostService;
        }
    }

    public static HostServices getHostServices() {
        return hostServices;
    }

}
