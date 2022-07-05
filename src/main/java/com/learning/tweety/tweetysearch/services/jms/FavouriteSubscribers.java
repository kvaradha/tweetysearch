package com.learning.tweety.tweetysearch.services.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learning.tweety.tweetysearch.repository.Favourite;
import com.learning.tweety.tweetysearch.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetysearch.services.common.FavouriteService;
import com.learning.tweety.tweetysearch.services.common.GsonGenerator;
import com.learning.tweety.tweetysearch.services.common.TweetyMessageService;

@Component
public class FavouriteSubscribers {

	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	GsonGenerator gsonGenerator;

	/**
	 * Container factory name defined in @see ActiveMQConfig JMSException - In case
	 * of any exception message will not pop. [Configuration for this in @see
	 * ActiveMQConfig]
	 * 
	 * @param messages
	 * @throws JMSException
	 */
	@JmsListener(destination = ActiveMQConstants.CONS_FAVOURITE_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void favouriteMessage(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.updateFavourite(favourite);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_FAVOURITE_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteFavouriteMessage(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.removeFavourite(favourite);
	}
	
	/*@JmsListener(destination = ActiveMQConstants.CONS_FAVOURITE_COUNT_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void addFavouriteCount(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.updateFavouriteCount(favourite);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DEL_FAVOURITE_COUNT_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteFavouriteCount(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.removeFavouriteCount(favourite);
	}*/
}
