package com.learning.tweety.tweetysearch.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "twittersearch")
public class TwitterSearch {
	@Id
	@Column(name = "twittersearch_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long tweetySearch;
	@Column(name = "twittermessages_id")
	Long tweetyMessagesId;
	@Column(name = "tweetmessage")
	String tweetyMessage;
	@Column(name = "tweetkeyword")
	String tweetyKeyword;
	@Column(name = "favouritecount")
	Long favouriteCount;
	@Column(name = "retweetcount")
	Long retweetCount;
	@Column(name = "created")
	Date tweetyCreationDate;
	@Column(name = "username")
	String userName;
	public Long getTweetyMessagesId() {
		return tweetyMessagesId;
	}
	public void setTweetyMessagesId(Long tweetyMessagesId) {
		this.tweetyMessagesId = tweetyMessagesId;
	}
	public String getTweetyMessage() {
		return tweetyMessage;
	}
	public void setTweetyMessage(String tweetyMessage) {
		this.tweetyMessage = tweetyMessage;
	}
	public String getTweetyKeyword() {
		return tweetyKeyword;
	}
	public void setTweetyKeyword(String tweetyKeyword) {
		this.tweetyKeyword = tweetyKeyword;
	}
	public Long getFavouriteCount() {
		return favouriteCount;
	}
	public void setFavouriteCount(Long favouriteCount) {
		this.favouriteCount = favouriteCount;
	}
	public Long getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(Long retweetCount) {
		this.retweetCount = retweetCount;
	}
	public Date getTweetyCreationDate() {
		return tweetyCreationDate;
	}
	public void setTweetyCreationDate(Date tweetyCreationDate) {
		this.tweetyCreationDate = tweetyCreationDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
