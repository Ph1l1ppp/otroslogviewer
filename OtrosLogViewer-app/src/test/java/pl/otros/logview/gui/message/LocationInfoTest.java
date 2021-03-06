package pl.otros.logview.gui.message;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LocationInfoTest {

  @DataProvider(name = "removeLabdas")
  public Object[][] removeLabdasDataProvider() {
    return new Object[][]{
      {"\tat java.util.concurrent.FutureTask.report(FutureTask.java:122)", "\tat java.util.concurrent.FutureTask.report(FutureTask.java:122)"},
      {"at test.sampleapp.SampleAppMultiThreadedFix2.lambda$performRequests$16(SampleAppMultiThreadedFix2.java:36)", "at test.sampleapp.SampleAppMultiThreadedFix2.lambda$performRequests$16(SampleAppMultiThreadedFix2.java:36)"},
      {"at test.sampleapp.SampleAppMultiThreadedFix2$$Lambda$16/2016207428.accept(Unknown Source)", "at test.sampleapp.SampleAppMultiThreadedFix2$Lambda.accept(Unknown Source)"},
    };
  }


  @Test(dataProvider = "removeLabdas")
  public void testRemoveLambdas(String stacktraceLine,String expected){
    Assert.assertEquals(LocationInfo.removeLambdas(stacktraceLine),expected);
  }

  @DataProvider(name = "parse")
  public Object[][] parseDataProvider() {
    return new Object[][]{
      {"\tat java.util.concurrent.FutureTask.report(FutureTask.java:122)", new LocationInfo("java.util.concurrent", "java.util.concurrent.FutureTask", "report", "FutureTask.java", 122)},
      {"at test.sampleapp.SampleAppMultiThreadedFix2.lambda$performRequests$16(SampleAppMultiThreadedFix2.java:36)", new LocationInfo("test.sampleapp", "test.sampleapp.SampleAppMultiThreadedFix2", "lambda$performRequests$16", "SampleAppMultiThreadedFix2.java", 36)},
      {"at test.sampleapp.SampleAppMultiThreadedFix2$$Lambda$16/2016207428.accept(Unknown Source)", null},
      {"at test.sampleapp.services.hotels.HotelsService.getHotels(HotelsService.java:30)", new LocationInfo("test.sampleapp.services.hotels", "test.sampleapp.services.hotels.HotelsService", "getHotels", "HotelsService.java", 30)},
    };
  }


  @Test(dataProvider = "parse")
  public void testParse(String s, LocationInfo expected) throws Exception {
    //when
    final LocationInfo actual = LocationInfo.parse(s);

    //then
    Assert.assertEquals(actual, expected);

  }
}