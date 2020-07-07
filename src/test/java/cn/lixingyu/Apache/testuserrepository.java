package cn.lixingyu.Apache;

import cn.lixingyu.Apache.config.MailConfig;
import cn.lixingyu.Apache.entity.UserInfo;
import cn.lixingyu.Apache.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author Lxxxxxxy
 * @time 2020/01/05 17:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class testuserrepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailConfig mailConfig;

    @Value("${project.url}")
    private String projectUrl;

    Logger logger = LoggerFactory.getLogger(Log.class);

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserUuid(UUID.randomUUID().toString());
        userInfo.setUserUsername("Lxxxxxxy");
        userInfo.setUserPassword("Lxxxxxxy");
        userInfo.setUserEmailAddress("admin@lixingyu.cn");
        userInfo.setUserStatus(0);
        userRepository.register(userInfo);
    }

    @Test
    public void test1(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserUuid(UUID.randomUUID().toString());
        userInfo.setUserUsername("Lxxxxxxy");
        userInfo.setUserPassword("Lxxxxxxy");
        userInfo.setUserEmailAddress("admin@lixingyu.cn");
        userInfo.setUserStatus(0);
        rabbitTemplate.convertAndSend("email.direct","sendEmail",userInfo.getUserEmailAddress());
    }

    @Test
    public void test2(){
        mailConfig.sendSimpleMail("597973086@qq.com","1","1",logger);
    }

    @Test
    public void test3(){
//        RP rp = userRepository.queryRoles("679d1de8-9207-46a9-bf0d-9afec29f48d3");
//        System.out.println(rp);
    }

}
