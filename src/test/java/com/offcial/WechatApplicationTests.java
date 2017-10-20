package com.offcial;

import com.offcial.dao.HouseMessageDao;
import com.offcial.utils.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

    @Autowired
    HouseMessageDao houseMessageDao;
    @Autowired
    private ImageUtil imageUtil;

    @Test
    public void contextLoads() {
        try {
            String accessToken = "zza";
            String mediaId = "05808040";
            String picName = "666";
            imageUtil.saveImageToDisk(accessToken, mediaId, picName, "E:/");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
