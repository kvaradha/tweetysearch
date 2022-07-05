package com.learning.tweety.tweetysearch.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.tweety.tweetysearch.repository.Retweet;
import com.learning.tweety.tweetysearch.repository.RetweetRepository;
import com.learning.tweety.tweetysearch.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetysearch.services.jms.MessagePublisher;

@Service
public class RetweetService {
	@Autowired
	RetweetRepository retweetRepository;
	
	@Autowired
	MessagePublisher<Retweet> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	public void updateRetweet(Retweet retweet) {
		Long count = retweetRepository.findByRetweetId(retweet.getRetweetId());
		if (count == 0) {
			retweetRepository.save(retweet);
		}
	}
	
	public void deleteRetweetById(Long tweetyID) {
		retweetRepository.deleteByTweetId(tweetyID);
	}
	
	public void deleteRetweet(Retweet retweet) {
		retweetRepository.deleteById(retweet.getRetweetId());
	}
	
}
