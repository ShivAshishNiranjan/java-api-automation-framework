package com.shivashish.test;

import com.shivashish.apis.weatherapis.GetCurrentWeather;
import com.shivashish.helper.config.ConfigureConstantFields;
import com.shivashish.utils.commonutils.ConfigReader;
import com.shivashish.utils.commonutils.DateUtils;
import com.shivashish.utils.commonutils.FileUtils;
import com.shivashish.utils.commonutils.RandomNumberUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-12
 */
public class TestInstagram {

    private final static Logger logger = LoggerFactory.getLogger(TestCurrentWeatherAPI.class);
    GetCurrentWeather getCurrentWeather;
    SoftAssert softAssert;

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

        //Instagram4j instagram = Instagram4j.builder().username(System.getProperty("username")).password(System.getProperty("password")).build();
        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        instagram.login();

        List<String> hastagsPoolClassic = new ArrayList<>(Arrays.asList("ig_exquisite", "jaw_dropping_shots"
                , "travelmore", "instavacation", "travelcommunity", "travelporn", "ilovetravel", "endlesstraveling", "travelwriter", "traveltheworld", "travelpic", "incredibleindia",
                "streetphotographyindia", "igtraveller", "worldtraveler", "travels", "travelpics", "traveldiaries", "travelphotographer",
                "travelgram", "travelphotography", "instatravel", "traveling", "travel", "likeforlikes", "dslrofficial"));


        List<String> hastagsPoolForLikes = new ArrayList<>(Arrays.asList("like4like", "likeforlike", "like", "likes",
                "likeforfollow", "likesforlikes", "like4follow", "likes4likes", "likeback", "likeme", "likeforlikes",
                "like4likes", "liker", "likeit", "likebackteam", "likeall", "likers", "likealways", "liketkit", "liketeam",
                "likeforlikealways", "likeforlikeback", "likesreturned", "likeforfollowers", "like4likeback", "likesforfollow",
                "likeforme", "LikeThis", "likebackalways", "likeaboss"));


        List<String> hastagsPoolForFollowers = new ArrayList<>(Arrays.asList("follow", "followforfollowback", "followfollow",
                "follow4like", "following", "followers", "followｍe", "follow4followback", "follow_me", "followmeto", "follower",
                "followtofollow", "follow4likes", "followforafollow", "followus", "followtrick", "followtrain", "followyou", "followmenow",
                "followalways", "followfollowfollow", "followmeback", "follownow", "followmee", "followforlike", "likeforfollow", "followmeplease",
                "followbackalways", "followparty", "followbackinstantly"));


        List<String> hasttagsSet1 = new ArrayList<>(Arrays.asList("jaw_dropping_shots", "splendid_shotz", "longexpohunter", "longexpoelite",
                "ig_shotz_le", "longexposure_shots", "earthexperience", "hubs_united", "global_hotshotz", "ig_exquisite", "splendid_shotz",
                "longexpohunter", "geonusantara", "loves_world", "longexposure_shots", "earthexperience", "big_shotz", "global_hotshotz",
                "igbest_shotz", "main_vision", "master_shots", "ourplanetdaily", "ig_dynamic", "awesome_earthpix",
                "worldbestshot", "thebest_capture"));


        int randomTimeForPolling = 6;
        int likeCountOnPostLowerLimit = 0;
        int likeCountOnPostUpperLimit = 50;

        List<String> uniqueUserNameList = new ArrayList<>();

        List<String> hashtags = new ArrayList<>();
        //hashtags.add(hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(hastagsPoolForLikes.size())));
        //hashtags.add(hastagsPoolForFollowers.get(RandomNumberUtils.generateRandomInt(hastagsPoolForFollowers.size())));
        //hashtags.add(hasttagsSet1.get(RandomNumberUtils.generateRandomInt(hasttagsSet1.size())));
        //hashtags.add(hastagsPoolClassic.get(RandomNumberUtils.generateRandomInt(hastagsPoolClassic.size())));


        //hashtags.add("likeforlike");
        //hashtags.add("like4like");
        hashtags.add("like4likes");
        //hashtags.add("tagsforlikes")
        //hashtags.add("l4l");
        //hashtags.add("f4f");
        //hashtags.add("instalike");
        //hashtags.add("likeforlikes");
        hashtags.add(hastagsPoolClassic.get(RandomNumberUtils.generateRandomInt(hastagsPoolClassic.size())));


        for (String hashtag : hashtags) {
            int randomWait = RandomNumberUtils.generateRandomInt(randomTimeForPolling); // for randomizing wait after
            int i = 0;

            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            logger.info("Liking post from hashtag : [{}]", hashtag);

            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(hashtag));

            logger.info("**************************************************************************");
            logger.info("Total Result are :[{}]", tagFeed.getItems().size());
            logger.info("**************************************************************************");

            for (InstagramFeedItem feedResult : tagFeed.getItems()) {

                if (hastagsPoolForLikes.contains(hashtag) || hastagsPoolForFollowers.contains(hashtag)) {
                    likeCountOnPostLowerLimit = 0;
                    likeCountOnPostUpperLimit = 20;

                }

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
                    instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
                    fileUtils.dumpResponseInFile(filename, todaysDate + "->" + likeCount);
                    logger.info("Waiting for [{}] seconds", randomWait);
                    logger.info("--------------------------------------------------------");
                    Thread.sleep(randomWait * 1000);
                    logger.info("--------------------------------------------------------");
                } else {
                    logger.info("--------------------------------------------------------");
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
