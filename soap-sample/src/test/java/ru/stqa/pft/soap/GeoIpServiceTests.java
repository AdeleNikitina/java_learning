package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.147.9.136");
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>90</State></GeoIP>");
  }

}
