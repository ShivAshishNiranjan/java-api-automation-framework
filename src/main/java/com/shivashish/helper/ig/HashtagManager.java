package com.shivashish.helper.ig;

import com.shivashish.utils.commonutils.RandomNumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 17/11/19
 */
public class HashtagManager {
    public List<String> hashtagtoOperate(String username) {
        List<String> hashtags = new ArrayList<>();
        Random r = new Random();

        int chance = r.nextInt(2);
        if (chance == 1) {
            hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
        } else {
            hashtags.add(Hashtags.hastagsPoolForFollowers.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForFollowers.size())));
        }

        chance = r.nextInt(2);
        if (chance == 1) {
            hashtags.add(Hashtags.hastagsPoolForLikes.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForLikes.size())));
        } else {
            hashtags.add(Hashtags.hastagsPoolForFollowers.get(RandomNumberUtils.generateRandomInt(Hashtags.hastagsPoolForFollowers.size())));
        }

        hashtags.add(Hashtags.hasttagsSet.get(RandomNumberUtils.generateRandomInt(Hashtags.hasttagsSet.size())));
        hashtags.add(Hashtags.hasttagsSet.get(RandomNumberUtils.generateRandomInt(Hashtags.hasttagsSet.size())));
        return hashtags;
    }
}
