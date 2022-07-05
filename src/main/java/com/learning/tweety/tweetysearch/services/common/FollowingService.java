package com.learning.tweety.tweetysearch.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.tweety.tweetysearch.repository.Following;
import com.learning.tweety.tweetysearch.repository.FollowingRepository;
import com.learning.tweety.tweetysearch.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetysearch.services.jms.MessagePublisher;

@Service
public class FollowingService {
	@Autowired
	FollowingRepository followingRepository;
	
	@Autowired
	MessagePublisher<Following> publisher;
	
	public void addFollowers(Following following) {
		Long count = followingRepository.findByFollowID(following.getFollowingId());
		if(count == 0) {
			followingRepository.save(following);
			publisher.sendMessage(ActiveMQConstants.ACK_ADD_FOLLOWING, following);
		}
	}
	
	public void deleteFollowers(Following following) {
		followingRepository.deleteById(following.getFollowingId());
		publisher.sendMessage(ActiveMQConstants.ACK_DELETE_FOLLOWING, following);
	}
}
