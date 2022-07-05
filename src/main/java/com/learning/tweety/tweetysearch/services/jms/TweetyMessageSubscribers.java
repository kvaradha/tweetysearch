package com.learning.tweety.tweetysearch.services.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.learning.tweety.tweetysearch.repository.TweetyMessages;
import com.learning.tweety.tweetysearch.repository.TwitterSearch;
import com.learning.tweety.tweetysearch.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetysearch.services.common.GsonGenerator;
import com.learning.tweety.tweetysearch.services.common.TweetyMessageService;
import com.learning.tweety.tweetysearch.services.common.TweetySearchService;

@Component
public class TweetyMessageSubscribers {
	
	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	TweetySearchService tweetySearchService;
	
	@Autowired
	GsonGenerator gsonGenerator;
	
	
	@JmsListener(destination = ActiveMQConstants.CONS_TWEETY_MESSAGES_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void addTweetyMessage(String messages) throws JMSException {
		TweetyMessages message = gsonGenerator.buildGson().fromJson(messages, TweetyMessages.class);
		tweetyMessageService.createTweetyMessage(message);
	}
	
	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_TWEETY_MESSAGES_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteTweetyMessage(String messages) throws JMSException {
		TweetyMessages message = gsonGenerator.buildGson().fromJson(messages, TweetyMessages.class);
		tweetyMessageService.deleteTweetyMessage(message.getTweetyMessagesId());
	}
	
	@JmsListener(destination = ActiveMQConstants.CONS_TWEETY_SEARCH_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void addTweetySearch(String messages) throws JMSException {
		TwitterSearch message = gsonGenerator.buildGson().fromJson(messages, TwitterSearch.class);
		tweetySearchService.createSearchMessage(message);
	}
}
