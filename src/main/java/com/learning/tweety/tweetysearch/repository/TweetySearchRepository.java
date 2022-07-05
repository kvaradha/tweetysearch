package com.learning.tweety.tweetysearch.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TweetySearchRepository extends CrudRepository<TwitterSearch, Long> {

	@Query(value = "select * from twittersearch where tweetkeyword = ?1 order by created desc", nativeQuery = true)
	List<TwitterSearch> findTweets(String keyword, Pageable pageable);
	
	@Query(value = "delete from twittersearch where twittermessages_id = ?1", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteTweetyMessage(Long tweetID);
}