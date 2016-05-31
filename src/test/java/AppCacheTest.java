import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.feed.modal.Tweet;
import com.feed.util.AppCache;
import com.feed.util.DBUtil;

public class AppCacheTest {
	
	Tweet t1=new Tweet.TweetBuilder("1000", "my new car").timestamp(10000L).build();
	Tweet t2=new Tweet.TweetBuilder("1000", "lets go to park").timestamp(20000L).build();
	Tweet t3=new Tweet.TweetBuilder("1000", "hello").timestamp(30000L).build();
	//Tweet t4=new Tweet.TweetBuilder("1003", "fire").build();
	//Tweet t5=new Tweet.TweetBuilder("1003", "fireman is here").build();
	//Tweet t6=new Tweet.TweetBuilder("1003", "message3").build();
	
	
	@Test
	public void testAppCache1(){
		AppCache.clear();
		AppCache.addToCache("1000", t1);
		AppCache.addToCache("1000", t2);
		AppCache.addToCache("1000", t3);
		java.util.List<Tweet> tweets=
		AppCache.getTweets("1000");
		assertTrue(tweets.contains(t1));
		assertTrue(tweets.contains(t2));
		assertTrue(tweets.contains(t3));
		Tweet t4=new Tweet.TweetBuilder("1000", "fire").timestamp(40000L).build();
		AppCache.addToCache("1000", t4);
		tweets=
				AppCache.getTweets("1000");
		System.out.println(tweets);
		assertFalse(tweets.contains(t1));
	}

	@Test
	public void testAppCache2(){
		List<Tweet> list=new ArrayList<Tweet>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		AppCache.clear();
		AppCache.addToCache("1000", list);		
		List<Tweet> tweets=
		AppCache.getTweets("1000");
		assertTrue(tweets.containsAll(list));
		
		
		
	}

	@Test
	public void testAppCache3(){
		AppCache.clear();
		Tweet t1=new Tweet.TweetBuilder("1001", "test").timestamp(40000L).build();
		//saving in db only
		DBUtil.resetDb();
		DBUtil.saveTweet(t1);
		List<Tweet> tweets=DBUtil.getRecentTweetsFromDB("1001");
		assertEquals(1,tweets.size());
		Tweet t=tweets.get(0);
		assertEquals(t.getUserid(),"1001");
	}

	
}
