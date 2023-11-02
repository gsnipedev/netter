package com.netheve.netter.common.util;


import java.time.LocalDateTime;
import java.time.ZoneId;


public class EpochTimeHelper {

    public static Long next30Days()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next30Days = now.plusDays(30);
        return next30Days.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static Long next7Days()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next7Days = now.plusDays(7);
        return next7Days.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static Long nextDay()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next7Days = now.plusDays(1);
        return next7Days.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static Long plusDay(int dayCount)
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextCustomDays = now.plusDays(dayCount);
        return nextCustomDays.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static Long now()
    {
        return System.currentTimeMillis() / 1000;
    }

}
