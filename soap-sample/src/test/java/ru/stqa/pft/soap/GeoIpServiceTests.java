package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by chesnokova.sa on 13.12.2016.
 */
public class GeoIpServiceTests {

     @Test
     public void testMyIp() {
         GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("80.89.129.115");
         assertEquals(geoIp.getCountryCode(), "RUS");
     }


}
