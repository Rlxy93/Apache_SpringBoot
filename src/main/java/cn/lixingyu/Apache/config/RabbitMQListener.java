package cn.lixingyu.Apache.config;

import cn.lixingyu.Apache.entity.UserInfo;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Rlxy93
 * @time 2020/01/07 17:06
 */
@Component
public class RabbitMQListener {

    Logger logger = LoggerFactory.getLogger(Log.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailConfig mailConfig;

    @Value("${project.url}")
    private String projectUrl;

    //监听sendEmail队列，并发送邮件
    @RabbitListener(queues = "sendEmail")
    public void listenerQUEUE(UserInfo userInfo) {
        logger.info("开始给" + userInfo.getUserAccount() + "发送邮件！");
        try {
            mailConfig.sendSimpleMail(userInfo.getUserEmailAddress(), "lixingyu.cn激活邮件", "<a href=\"https://" + projectUrl + "/activeUser?uuid=" + userInfo.getUserUuid() + "\">点我激活</a>", logger);
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

}
