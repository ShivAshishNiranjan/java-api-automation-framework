package com.shivashish.test;

import com.shivashish.apis.weatherapis.GetCurrentWeather;
import com.shivashish.helper.config.ConfigureConstantFields;
import com.shivashish.helper.ig.HashtagManager;
import com.shivashish.helper.ig.MyInstagram;
import com.shivashish.utils.commonutils.ConfigReader;
import com.shivashish.utils.commonutils.DateUtils;
import com.shivashish.utils.commonutils.FileUtils;
import com.shivashish.utils.commonutils.RandomNumberUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLikeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-12
 */
public class TestInstagram {

    private final static Logger logger = LoggerFactory.getLogger(TestCurrentWeatherAPI.class);
    GetCurrentWeather getCurrentWeather;
    SoftAssert softAssert;
    MyInstagram myInstagram;
    Instagram4j account;
    HashtagManager hashtagManager;

    FileUtils fileUtils;
    String filename;
    int likeCount;
    String todaysDate;


    @BeforeClass
    public void beforeClass() {
        logger.debug("In Before Class method");
        getCurrentWeather = new GetCurrentWeather();
    }


    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        logger.debug("In Before Method");
        logger.debug("method name is: {} ", method.getName());
        softAssert = new SoftAssert();
        fileUtils = new FileUtils();
        hashtagManager = new HashtagManager();

        String liketrackerFileName = "src/main/resources/config" + "/liketracker.txt";
        filename = "src/main/resources/config" + "/dailyliketracker.txt";

        todaysDate = DateUtils.getCurrentDateInDDMMYYYY();
        logger.info("Today's Date is :[{}]", todaysDate);

        fileUtils.createFileIfNotExist(filename);

        if (!fileUtils.checkIfFileIsEmpty(filename)) {
            String fileContent = fileUtils.getDataInFile(filename);
            if (fileContent.contains(todaysDate)) {
                likeCount = Integer.parseInt(fileContent.split("->")[1]);
            } else {
                FileUtils.appendStrToFile(liketrackerFileName, "\n" + fileContent);
                fileUtils.dumpResponseInFile(filename, todaysDate + "->" + "0");
                likeCount = 0;
            }
        } else {
            fileUtils.dumpResponseInFile(filename, todaysDate + "->" + "0");
            likeCount = 0;
        }
        logger.debug("***********************************************************************************************************************");

    }


    @Test()
    public void testIgLogin() throws IOException, InterruptedException {
        String username = ConfigReader.getValueFromConfigFile(ConfigureConstantFields.getConfigFilesPath(),
                ConfigureConstantFields.getInstaConfigFileName(), "default", "username");
        String password = ConfigReader.getValueFromConfigFile(ConfigureConstantFields.getConfigFilesPath(),
                ConfigureConstantFields.getInstaConfigFileName(), "default", "password");

        myInstagram = new MyInstagram(username, password);
        account = myInstagram.getAccount();


        int randomTimeForPolling = 10;
        int likeCountOnPostLowerLimit = 0;
        int likeCountOnPostUpperLimit = 30;

        List<String> uniqueUserNameList = new ArrayList<>();

        List<String> hashtags = hashtagManager.hashtagtoOperate();
        Map<String, InstagramFeedResult> tagFeeds = new HashMap();
        for (String hashtag : hashtags) {
            InstagramFeedResult tagFeed = account.sendRequest(new InstagramTagFeedRequest(hashtag));
            tagFeeds.put(hashtag, tagFeed);
        }


        int countForIndividualHashtag = 25;


        for (String hashtag : hashtags) {


            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            logger.info("Liking post from hashtag : [{}]", hashtag);

            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            InstagramFeedResult tagFeed = tagFeeds.get(hashtag);

            logger.info("**************************************************************************");
            logger.info("Total Result are :[{}]", tagFeed.getItems().size());
            logger.info("**************************************************************************");


            int j = 0;
            for (InstagramFeedItem feedResult : tagFeed.getItems()) {
                if (j < countForIndividualHashtag) {
                    int randomWait = RandomNumberUtils.generateRandomInt(randomTimeForPolling);
                    logger.info("Post ID: " + feedResult.getPk());
                    int likeCountOnPost = feedResult.getLike_count();
                    logger.info("Post Like Count : [{}]", likeCountOnPost);
                    boolean validationCondition = (likeCountOnPost >= likeCountOnPostLowerLimit &&
                            likeCountOnPost <= likeCountOnPostUpperLimit &&
                            !uniqueUserNameList.contains(feedResult.getUser().getUsername()));

                    if (validationCondition) {

                        logger.info("UserName : [{}]", feedResult.getUser().getUsername());
                        uniqueUserNameList.add(feedResult.getUser().getUsername());
                        logger.info("Follower Count : [{}]", feedResult.getUser().getFollower_count());
                        likeCount++;
                        InstagramLikeResult instagramLikeResult = account.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
                        // if spam reported we will stop
                        if (instagramLikeResult.isSpam())
                            break;

                        // to string response from instagramLikeResult
                        // InstagramLikeResult(super=StatusResult(status=fail, message=feedback_required, spam=true, lock=false, feedback_title=Action Blocked, feedback_message=This action was blocked. Please try again later. We restrict certain content and actions to protect our community. Tell us if you think we made a mistake., error_type=null, checkpoint_url=null), spam=true, feedback_ignore_label=OK, feedback_title=Action Blocked, feedback_message=This action was blocked. Please try again later. We restrict certain content and actions to protect our community. Tell us if you think we made a mistake., feedback_url=repute/report_problem/instagram_like_add/, feedback_action=report_problem, feedback_appeal_label=Tell us)

                        fileUtils.dumpResponseInFile(filename, todaysDate + "->" + likeCount);
                        logger.info("Waiting for [{}] seconds", randomWait);
                        logger.info("Liking post from hashtag : [{}]", hashtag);
                        logger.info("--------------------------------------------------------");
                        Thread.sleep(randomWait * 1000);
                        j++;
                        logger.info("--------------------------------------------------------");
                    } else {
                        logger.info("--------------------------------------------------------");
                    }
                } else {
                    logger.info("Enough for This Hashtag [{}] Plz", hashtag);
                    break;
                }
            }
        }


        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("Total Likes Given today : [{}]", likeCount);
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
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
