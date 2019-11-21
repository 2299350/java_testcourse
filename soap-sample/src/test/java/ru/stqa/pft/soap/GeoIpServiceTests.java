package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIP() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
    Assert.assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
  }
}
