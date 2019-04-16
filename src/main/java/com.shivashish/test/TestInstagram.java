package com.shivashish.test;

import com.shivashish.apis.weatherapis.GetCurrentWeather;
import com.shivashish.helper.config.ConfigureConstantFields;
import com.shivashish.utils.commonutils.ConfigReader;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.InstagramPostCommentRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-12
 */
public class TestInstagram {

    private final static Logger logger = LoggerFactory.getLogger(TestCurrentWeatherAPI.class);
    GetCurrentWeather getCurrentWeather;
    SoftAssert softAssert;


    @BeforeClass
    public void beforeClass() {
        logger.debug("In Before Class method");
        getCurrentWeather = new GetCurrentWeather();
    }


    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.debug("In Before Method");
        logger.debug("method name is: {} ", method.getName());
        softAssert = new SoftAssert();
        logger.debug("***********************************************************************************************************************");

    }


    @Test()
    public void testIgLogin() throws IOException, InterruptedException {
        String username = ConfigReader.getValueFromConfigFile(ConfigureConstantFields.getConfigFilesPath(),
                ConfigureConstantFields.getInstaConfigFileName(),"default","username");
        String password = ConfigReader.getValueFromConfigFile(ConfigureConstantFields.getConfigFilesPath(),
                ConfigureConstantFields.getInstaConfigFileName(),"default","password");

        //Instagram4j instagram = Instagram4j.builder().username(System.getProperty("username")).password(System.getProperty("password")).build();
        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        instagram.login();

        //String[] hashtags = System.getProperty("hashtags").trim().split(" ");
        String[] hashtags = ("travelgram").trim().split(" ");

        for (String hashtag : hashtags) {
            logger.info("Testing hastag is [{}]",hashtag);
            InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(hashtag));
            for (InstagramFeedItem feedResult : tagFeed.getItems()) {
                logger.info("Post ID: " + feedResult.getPk());
                Thread.sleep(5000);
                instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
                Thread.sleep(5000);
                instagram.sendRequest(new InstagramPostCommentRequest(feedResult.getPk(), "Nice One"));
            }
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        softAssert.assertAll();
        logger.debug("In After Method");
        logger.debug("method name is: {}", result.getMethod().getMethodName());
        logger.debug("***********************************************************************************************************************");
    }

    @AfterClass
    public void afterClass() {
        logger.debug("In After Class method");
    }
}
