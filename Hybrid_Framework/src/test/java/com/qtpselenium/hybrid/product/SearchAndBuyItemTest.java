package com.qtpselenium.hybrid.product;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.hybrid.Keywords;
import com.qtpselenium.hybrid.base.BaseTest;
import com.qtpselenium.hybrid.util.Constants;
import com.qtpselenium.hybrid.util.DataUtil;
import com.qtpselenium.hybrid.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class SearchAndBuyItemTest extends BaseTest{
	@BeforeTest
	public void init(){
		 xls = new Xls_Reader(Constants.ProductSuite_XLS);
		 testName = "SearchAndBuyItemTest";
	}
	
	@Test(dataProvider="getData")
	public void filterMobileTest(Hashtable<String,String> data){
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, data.toString());
		
		if(DataUtil.isSkip(xls, testName) || data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		
		test.log(LogStatus.INFO, "Starting "+testName);
		
		app = new Keywords(test);
		test.log(LogStatus.INFO, "Executing keywords");
		app.executeKeywords(testName, xls,data);
		// add the screenshot
		//app.getGenericKeyWords().reportFailure("xxxx");
		test.log(LogStatus.PASS, "PASS");
		app.getGenericKeyWords().takeScreenShot();
	}
}
