package com.shivashish.helper.instahelp;

import com.shivashish.utils.commonutils.RandomNumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 17/11/19
 */
public class GenerateHashtagsForSecularChcha {

    public static String bjp = "#india #modi #narendramodi #amitshah #congress  #rss #rahulgandhi #politics" +
            "#indian #hindu #namo  #news #indianpolitics #election #hinduism #bharat #yogiadityanath #inc #indianarmy ";

    public static String coronavirus = "#indiafightscorona #covid #corona #coronavirus #coronaindia #socialdistancing" +
            " #coronaviruspandemic #chinavirus #coronaprotection #coronavirusoutbreak ";

    public static String secularchacha = "#indiafightscorona #covid #corona #coronavirus #coronaindia " +
            "#socialdistancing" +
            " #coronaviruspandemic #chinavirus #coronaprotection #coronavirusoutbreak";

    public static String ndtv = "#ndtv #aajtak #india #ravishkumar #zeenews #bjp #news #modi #godimedia #congress" +
            " #narendramodi #like #republictv #bjpkmkb #rejectnrc #andhbhakt #delhi #nrc #caa #boycottgodimedia " +
            "#rahulgandhi #amitshah #kanhaiyakumar  #modia #asaduddinowaisi #abpnews #indianews #bhfyp #godimedia #boycottgodimedia #amitshah #bjp #narendramodi #india #nrc #indiaagainstcaa #caa #rahulgandhi #bjpagainstdemocracy #indiaagainstcab #modi #indiaagainstnrc #ndtv #g #ravishkumar #mediahunter #modia #andhbhakts #delhi #kashmir #modihaitonamumkinhai #congress #citizenshipamendmentbill #andhebhakt #rejectnrc #boycottmodia #bhakts #bhfyp";

    public static String secular_chacha = "";

    public static List<String> convertToListofString(String hashtags) {
        return new ArrayList<>(Arrays.asList(hashtags.replaceAll("//s", "")
                .replaceAll("[\\n\\t ]", "").split("#")));
    }

    public static void main(String[] args) {
        List<String> hashtagsTargetting = new ArrayList<>(
                Arrays.asList( ndtv));

        List<String> finalHashtags = new ArrayList<>();


        for (String hashtag : hashtagsTargetting) {
            finalHashtags.addAll(RandomNumberUtils.getRandomElements(convertToListofString(hashtag), 15));
        }


        for (String tag : finalHashtags)
            System.out.println("#" + tag);

    }


}
