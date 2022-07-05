package com.learning.tweety.tweetysearch.securityconfig.activemq;

public class ActiveMQConstants {
	
	public static final String DELETE_FOLLOWING = "ackdeletefollowing";
	
	public static final String ADD_FOLLOWING = "ackaddfollowing";

	public static final String CONTAINER_FACTORY = "jmsListenerContainerFactory";
	
	public static final String ACK_DELETE_FOLLOWING = "VirtualTopic.ackdeletefollowing";
	
	public static final String ACK_ADD_FOLLOWING = "VirtualTopic.ackaddfollowing";
	
	public static final String CONS_ADD_FOLLOWINGS_TOPIC = "Consumer.myConsumer1.VirtualTopic.addfollowings";
	
	public static final String CONS_DELETE_FOLLOWINGS_TOPIC = "Consumer.myConsumer1.VirtualTopic.deletefollowings";
	
	public static final String CONS_RETWEET_TOPIC = "Consumer.myConsumer2.VirtualTopic.retweetmessages";
	
	public static final String CONS_FAVOURITE_TOPIC = "Consumer.myConsumer2.VirtualTopic.favouritemessages";
	
	public static final String CONS_TWEETY_MESSAGES_TOPIC = "Consumer.myConsumer1.VirtualTopic.tweetymessages";
	
	public static final String CONS_DELETE_TWEETY_MESSAGES_TOPIC = "Consumer.myConsumer1.VirtualTopic.deletetweetymessages";
	
	public static final String CONS_TWEETY_SEARCH_TOPIC = "Consumer.myConsumer1.VirtualTopic.tweetysearch";
	
	public static final String CONS_DELETE_RETWEET_TOPIC = "Consumer.myConsumer2.VirtualTopic.delretweetmessages";
	
	public static final String CONS_DELETE_FAVOURITE_TOPIC = "Consumer.myConsumer2.VirtualTopic.delfavouritemessages";
}
