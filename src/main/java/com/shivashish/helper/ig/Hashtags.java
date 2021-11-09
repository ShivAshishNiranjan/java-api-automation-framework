package com.shivashish.helper.ig;

import com.shivashish.utils.commonutils.RandomNumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 17/11/19
 */
public class Hashtags {

    public static List<String> hastagsPoolForLikes = new ArrayList<>(Arrays.asList("like4like", "likeforlike", "likes",
            "likeforfollow", "likesforlikes", "like4follow", "likes4likes", "likeback", "likeme", "likeforlikes",
            "like4likes", "liker", "likeit", "likebackteam", "likeall", "likers", "likealways", "liketkit", "liketeam",
            "likeforlikealways", "likeforlikeback", "likesreturned", "likeforfollowers", "like4likeback", "likesforfollow",
            "likeforme", "LikeThis", "likebackalways", "likeaboss"));


    public static List<String> hastagsPoolForFollowers = new ArrayList<>(Arrays.asList("follow", "followforfollowback",
            "followfollow", "follow4like", "following", "followers", "followｍe", "follow4followback", "follow_me",
            "followmeto", "follower", "followtofollow", "follow4likes", "followforafollow", "followus",
            "followtrain", "followyou", "followmenow", "followalways", "followfollowfollow", "followmeback", "follownow"
            , "followmee", "followforlike", "likeforfollow", "followmeplease",
            "followbackalways", "followparty", "followbackinstantly"));


    public static List<String> hasttagsSet = new ArrayList<>(Arrays.asList("jaw_dropping_shots", "splendid_shotz",
            "longexpohunter", "longexpoelite",
            "ig_shotz_le", "longexposure_shots", "earthexperience", "hubs_united", "global_hotshotz", "ig_exquisite", "splendid_shotz",
            "longexpohunter", "geonusantara", "loves_world", "longexposure_shots", "earthexperience", "big_shotz", "global_hotshotz",
            "igbest_shotz", "main_vision", "master_shots", "ourplanetdaily", "ig_dynamic", "awesome_earthpix",
            "worldbestshot", "thebest_capture", "ig_exquisite", "jaw_dropping_shots"
            , "travelmore", "instavacation", "travelcommunity", "travelporn", "ilovetravel", "endlesstraveling", "travelwriter", "traveltheworld", "travelpic", "incredibleindia",
            "streetphotographyindia", "igtraveller", "worldtraveler", "travels", "travelpics", "traveldiaries", "travelphotographer",
            "travelgram", "travelphotography", "instatravel", "traveling", "travel", "dslrofficial" , "himachalisluv"));


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String himanchal = "#himachalpradesh#himalayas#himachal#india#travel#mountains#travelphotography#nature" +
            "#incredibleindia#wanderlust#travelgram#instahimachal#photography#travelblogger#manali#naturephotography#spiti#himachaltourism#shimla#indiapictures#landscape#traveller#instagood#himachaldiaries#photooftheday#love#travelrealindia#instatravel#trekking#spitivalley";

    public static String manali = "#manali  #india  #himachal  #travel  #himachalpradesh  #himalayas  #mountains" +
            "#nature  #shimla  #kullu  #photography  #travelphotography  #incredibleindia  #wanderlust  #leh  #travelgram  #kasol  #chamba  #delhi  #love  #ladakh  #instahimachal  #trip  #mandi  #adventure  #instagood  #manalidiaries  #chandigarh  #traveller  #travelblogger";


    public static String Photography = "#photography#blackandwhitephotography#naturephotography#photographylovers#Instaphoto#Instaphoto#photogram#photographyislife#justgoshoot#picoftheday#photooftheday";

    public static String uttarakhand = "#uttarakhand  #india  #himalayas  #travel  #nature  #mountains  #incredibleindia  #travelphotography  #photography  #wanderlust  #himachal  #travelgram  #uttarakhandheaven  #dehradun  #rishikesh  #naturephotography  #love  #uttarakhandtourism  #pahadi  #nainital  #himalaya  #instagood  #uttarakhanddiaries  #himachalpradesh  #garhwal  #photooftheday  #travelblogger  #landscape  #trekking  #adventure";


    public static String sikkim = "#himachalpradesh#himalayas#himachal#india#travel#mountains#travelphotography" +
            "#nature#incredibleindia#wanderlust#travelgram#instahimachal#photography#travelblogger#manali#naturephotography#spiti#himachaltourism#shimla#indiapictures#landscape#traveller#instagood#himachaldiaries#photooftheday#love#travelrealindia#instatravel#trekking#spitivalley";


    public static String temples = "#temple  #india  #travel  #photography  #travelphotography  #architecture" +
            "#nature  #incredibleindia  #photooftheday  #instagood  #travelgram  #wanderlust  #love  #picoftheday  #instagram  #instatravel  #hindu  #art  #beautiful  #peace  #god  #culture  #travelblogger  #shiva  #religion  #karnataka  #sky  #hinduism  #naturephotography  #traveller";

    public static String bjp = "#bjp #india #modi #narendramodi #amitshah #congress  #rss #rahulgandhi #politics" +
            "#indian #hindu #namo #bjpindia #news #indianpolitics #election #hinduism #bharat #yogiadityanath #inc #indianarmy ";

    public static String coronavirus = "#indiafightscorona #covid #corona #coronavirus #coronaindia #socialdistancing" +
            " #coronaviruspandemic #chinavirus #coronaprotection #coronavirusoutbreak ";

    public static String secularchacha = "#indiafightscorona #covid #corona #coronavirus #coronaindia " +
            "#socialdistancing" +
            " #coronaviruspandemic #chinavirus #coronaprotection #coronavirusoutbreak ";

    public static String secular_chacha = "";

    public static List<String> convertToListofString(String hashtags) {
        return new ArrayList<>(Arrays.asList(hashtags.replaceAll("//s", "")
                .replaceAll("[\\n\\t ]", "").split("#")));
    }

    public static void main(String[] args) {


        List<String> hashtagsTargetting = new ArrayList<>(
                Arrays.asList(manali, temples));

        List<String> finalHashtags = new ArrayList<>();


        for (String hashtag : hashtagsTargetting) {
            finalHashtags.addAll(RandomNumberUtils.getRandomElements(convertToListofString(hashtag), 10));
        }

        finalHashtags.addAll(RandomNumberUtils.getRandomElements(hastagsPoolForLikes, 2));
        finalHashtags.addAll(RandomNumberUtils.getRandomElements(hastagsPoolForFollowers, 2));

        for (String tag : finalHashtags)
            System.out.println("#" + tag);

    }


}
