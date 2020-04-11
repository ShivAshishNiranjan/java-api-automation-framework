package com.shivashish.helper.ig;

import com.shivashish.utils.commonutils.RandomNumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 17/11/19
 */
public class HashtagManager {


    public List<String> hashtagtoOperate() {
        List<String> hashtags = new ArrayList<>();

        hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
        hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
        hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
        hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
//        hashtags.add(Hashtags.hastagsPoolForPhotography.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForPhotography.size())));
//        hashtags.add(Hashtags.hastagsPoolForFollowers.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForFollowers.size())));
//        hashtags.add(Hashtags.hasttagsSet1.get(RandomNumberUtils.generateRandomInt(Hashtags.hasttagsSet1.size())));
        hashtags.add(Hashtags.hasttagsSet2.get(RandomNumberUtils.generateRandomInt(Hashtags.hasttagsSet2.size())));

        //hashtags.add("followtrick");
        //hashtags.add("followbackinstantly"); // bahot hard
        return hashtags;
    }
}
