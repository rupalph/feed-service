import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.feed.dto.TweetDto;
import com.feed.modal.User;
import com.feed.repository.TweetRepository;
import com.feed.repository.UserRepository;
import com.feed.util.DBUtil;

public class RepositoryTests {

	@Test
	public void testTweetRepository1()
	{
		TweetDto e = new TweetDto();
		e.setMessage("test");
		e.setUserid("100");
		//e.setTimestamp("2016-05-20 18:00:00");
		TweetRepository.save(e);
		List<TweetDto> tweets=TweetRepository.fetchTweets("100");
		System.out.println(tweets);
		assertTrue(tweets.size()==1);
		TweetDto t=tweets.get(0);
		assertEquals(t.getMessage(),"test");
		assertEquals(t.getUserid(),"100");
	}

	@Test
	public void testUserRepository()
	{
		DBUtil.addUser("100");
		DBUtil.addFollower("100", "101");
		User u=UserRepository.findUserById("100");
		assertEquals("100",u.getUserid());
		System.out.println(u.getFollowers());
		List<User> followers=u.getFollowers();
		assertEquals(followers.size(),1);
		User follower=followers.get(0);
		assertEquals(follower.getUserid(),"101");
	}
}
