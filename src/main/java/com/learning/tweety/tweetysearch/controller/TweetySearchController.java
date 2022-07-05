package com.learning.tweety.tweetysearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.tweety.tweetysearch.repository.TwitterSearch;
import com.learning.tweety.tweetysearch.services.common.TweetySearchService;
import com.learning.tweety.tweetysearch.services.token.JWTTokenService;

@RestController
@RequestMapping("/tweetysearch/keyword")
public class TweetySearchController {
	
	@Autowired
	TweetySearchService tweetySearchService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@GetMapping("/getMessage/{userName}/{keyword}/{pageIndex}/{noOfRows}")
	public List<TwitterSearch> getTwittyMessage(@RequestHeader("Authorization") String token,
			@PathVariable String userName, 
			@PathVariable int pageIndex,
			@PathVariable int noOfRows) {
		jwtTokenService.isValidUser(token, userName);
		return tweetySearchService.getProfileTweetyMessage(userName, pageIndex, noOfRows);
	}

}
