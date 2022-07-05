package com.learning.tweety.tweetysearch.services.common;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learning.tweety.tweetysearch.repository.Favourite;
import com.learning.tweety.tweetysearch.repository.Retweet;
import com.learning.tweety.tweetysearch.repository.TweetyMessages;
import com.learning.tweety.tweetysearch.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetysearch.services.jms.MessagePublisher;

@Service
public class TweetyMessageService {

	@Autowired
	MessagePublisher<TweetyMessages> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	RetweetService retweetService;
	
	@Autowired
	TweetySearchService tweetSearchService;
	
	/**
	 * create message, and store in the DB.
	 * if DB save is successful. Publish it to the activeMQ topic.
	 * @param message
	 */
	public void createTweetyMessage(TweetyMessages message) {
		Long count = tweetyMessagesRepo.findByTweetID(message.getTweetyMessagesId());
		if(count == 0) {
			tweetyMessagesRepo.save(message);
		}
	}
	
	/**
	 * Delete message based on id.
	 * @param tweetyID
	 */
	@Transactional
	public void deleteTweetyMessage(Long tweetyID) {
		tweetyMessagesRepo.deleteById(tweetyID);
		favouriteService.deleteFavouriteById(tweetyID);
		retweetService.deleteRetweetById(tweetyID);
		tweetSearchService.deleteSearchMessage(tweetyID);
	}
	
	public Iterable<TweetyMessages> getTweetyMessage(String userName) {
		return tweetyMessagesRepo.findOnlyUserCreatedMessages(userName);
	}
	
	public List<TweetyMessages> getProfileTweetyMessage(String userName, int pageIndex, int noOfRows) {
		Pageable pagination = PageRequest.of(pageIndex, noOfRows);
		return tweetyMessagesRepo.findTweetyStatus(userName, pagination);
	}
	
	@Transactional
	public void updateRetweet(Retweet retweet) {
		retweetService.updateRetweet(retweet);
		Long tweetyID = retweet.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setRetweetCount(message.getRetweetCount() + 1);
		tweetyMessagesRepo.save(message);
		tweetSearchService.updateRetweet(tweetyID);
	}
	
	@Transactional
	public void removeRetweet(Retweet retweet) {
		retweetService.deleteRetweet(retweet);
		Long tweetyID = retweet.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setRetweetCount(message.getRetweetCount() - 1);
		tweetyMessagesRepo.save(message);
		tweetSearchService.removeRetweet(tweetyID);
	}
	
	@Transactional
	public void updateFavourite(Favourite favourite) {
		favouriteService.updateFavourite(favourite);
		Long tweetyID = favourite.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setFavouriteCount(message.getFavouriteCount() + 1);
		tweetyMessagesRepo.save(message);
		tweetSearchService.updateFavourite(tweetyID);
	}
	
	@Transactional
	public void removeFavourite(Favourite favourite) {
		favouriteService.deleteFavourite(favourite);
		Long tweetyID = favourite.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setFavouriteCount(message.getFavouriteCount() - 1);
		tweetyMessagesRepo.save(message);
		tweetSearchService.removeFavourite(tweetyID);
	}
}


