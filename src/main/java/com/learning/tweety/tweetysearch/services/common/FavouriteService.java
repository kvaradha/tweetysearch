package com.learning.tweety.tweetysearch.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.tweety.tweetysearch.repository.Favourite;
import com.learning.tweety.tweetysearch.repository.FavouriteRepository;
import com.learning.tweety.tweetysearch.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetysearch.services.jms.MessagePublisher;

@Service
public class FavouriteService {

	@Autowired
	FavouriteRepository favouriteRepository;
	
	@Autowired
	MessagePublisher<Favourite> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	public void updateFavourite(Favourite favourite) {
		Long count = favouriteRepository.findByFavID(favourite.getFavouriteId());
		if (count == 0) {
			favouriteRepository.save(favourite);
		}
	}
	
	public void deleteFavourite(Favourite favourite) {
		favouriteRepository.deleteById(favourite.getFavouriteId());
	}
	
	public void deleteFavouriteById(Long tweetyID) {
		favouriteRepository.deleteByTweetId(tweetyID);
	}
}
