package com.knight.factory;

import com.knight.bean.AirPlane;

/**
 * 静态工厂
 */
public class AirPlaneStaticFactory {

    public static AirPlane getAirPlane(String captainName) {
        System.out.println("AirPlaneStaticFactory...正在造飞机");
        AirPlane airPlane = new AirPlane();
        airPlane.setEngine("虚幻引擎");
        airPlane.setCaptainName(captainName);
        airPlane.setCoCaptainName("李四副机长");
        airPlane.setPersonNumbers(300);
        airPlane.setWingLength("200m");
        return airPlane;
    }

}
