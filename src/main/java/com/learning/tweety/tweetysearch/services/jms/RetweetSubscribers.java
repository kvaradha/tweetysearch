package com.learning.tweety.tweetysearch.services.jms;

import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learning.tweety.tweetysearch.repository.Retweet;
import com.learning.tweety.tweetysearch.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetysearch.services.common.GsonGenerator;
import com.learning.tweety.tweetysearch.services.common.RetweetService;
import com.learning.tweety.tweetysearch.services.common.TweetyMessageService;

@Component
public class RetweetSubscribers {

	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	GsonGenerator gsonGenerator;
	
	@Autowired
	RetweetService retweetService;
	

	/**
	 * Container factory name defined in @see ActiveMQConfig
	 * 
	 * @param messages
	 * @throws JMSException
	 */
	@JmsListener(destination = ActiveMQConstants.CONS_RETWEET_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void retweetMessage(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.updateRetweet(retweet);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_RETWEET_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteRetweetMessage(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.removeRetweet(retweet);
	}
	
	/*@JmsListener(destination = ActiveMQConstants.CONS_RETWEET_COUNT_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void addRetweetCount(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.updateRetweetCount(retweet);
	}
	
	@JmsListener(destination = ActiveMQConstants.CONS_DEL_RETWEET_COUNT_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteRetweetCount(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.removeRetweetCount(retweet);
	}*/
}
