package com.learning.tweety.tweetysearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.tweety.tweetysearch.repository.TweetyMessages;
import com.learning.tweety.tweetysearch.services.common.TweetyMessageService;
import com.learning.tweety.tweetysearch.services.token.JWTTokenService;

@RestController
@RequestMapping("/tweetysearch/status")
public class TweetyStatusController {
	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@GetMapping("/getMessage/{userName}/{pageIndex}/{noOfRows}")
	public List<TweetyMessages> getTwittyMessage(@RequestHeader("Authorization") String token,
			@PathVariable String userName, 
			@PathVariable int pageIndex,
			@PathVariable int noOfRows) {
		jwtTokenService.isValidUser(token, userName);
		return tweetyMessageService.getProfileTweetyMessage(userName, pageIndex, noOfRows);
	}
}
