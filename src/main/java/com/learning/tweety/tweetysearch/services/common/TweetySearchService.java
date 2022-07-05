package com.learning.tweety.tweetysearch.services.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.learning.tweety.tweetysearch.repository.TweetySearchRepository;
import com.learning.tweety.tweetysearch.repository.TwitterSearch;

@Service
public class TweetySearchService {

	@Autowired
	TweetySearchRepository tweetySearchRepo;
	
	public List<TwitterSearch> getTwitterSearch(String keyword, int pageIndex, int noOfRows) {
		Pageable pagination = PageRequest.of(pageIndex, noOfRows);
		return tweetySearchRepo.findTweets("#" + keyword, pagination);
	}
	
	public void updateFavourite(Long tweetID) {
		List<TwitterSearch> messages = tweetySearchRepo.findTweet(tweetID);
		for(TwitterSearch message : messages) {
			message.setFavouriteCount(message.getFavouriteCount() + 1);
			tweetySearchRepo.save(message);
		}
	}
	
	public void removeFavourite(Long tweetID) {
		List<TwitterSearch> messages = tweetySearchRepo.findTweet(tweetID);
		for(TwitterSearch message : messages) {
			message.setFavouriteCount(message.getFavouriteCount() - 1);
			tweetySearchRepo.save(message);
		}
	}
	
	public void updateRetweet(Long tweetID) {
		List<TwitterSearch> messages = tweetySearchRepo.findTweet(tweetID);
		for(TwitterSearch message : messages) {
			message.setRetweetCount(message.getRetweetCount() + 1);
			tweetySearchRepo.save(message);
		}
	}
	
	public void removeRetweet(Long tweetID) {
		List<TwitterSearch> messages = tweetySearchRepo.findTweet(tweetID);
		for(TwitterSearch message : messages) {
			message.setRetweetCount(message.getRetweetCount() - 1);
			tweetySearchRepo.save(message);
		}
	}
	
	public void createSearchMessage(TwitterSearch message) {
		tweetySearchRepo.save(message);
	}
	
	public void deleteSearchMessage(Long tweetID) {
		tweetySearchRepo.deleteTweetyMessage(tweetID);
	}
}
