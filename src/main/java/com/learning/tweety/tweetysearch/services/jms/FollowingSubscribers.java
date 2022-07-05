package com.learning.tweety.tweetysearch.services.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learning.tweety.tweetysearch.repository.Following;
import com.learning.tweety.tweetysearch.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetysearch.services.common.FollowingService;
import com.learning.tweety.tweetysearch.services.common.GsonGenerator;

@Component
public class FollowingSubscribers {
	@Autowired
	GsonGenerator gsonGenerator;
	
	@Autowired
	FollowingService followingService;
	
	@JmsListener(destination = ActiveMQConstants.CONS_ADD_FOLLOWINGS_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void addFollowing(String messages) throws JMSException {
		Following following = gsonGenerator.buildGson().fromJson(messages, Following.class);
		followingService.addFollowers(following);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_FOLLOWINGS_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteFollowing(String messages) throws JMSException {
		Following following = gsonGenerator.buildGson().fromJson(messages, Following.class);
		followingService.deleteFollowers(following);	
	}

}
