package com.feed.util;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoginMockHelper {
	private static int[] tokens=new int[10];
	private static int counter=0;
	private static boolean isInit =false;

	static {
		//if(!isInit) {
			Random randomGenerator = new Random();
			for (int idx = 0; idx < 10; ++idx){
				int randomInt = randomGenerator.nextInt(1000000);

				tokens[idx]=randomInt;
				//System.out.println(tokens[idx]);
			}


			//isInit=true;
		//}
	}

	private static ConcurrentMap<String,Integer> loginMap=new ConcurrentHashMap<String,Integer>();

	public static int  userLogin(String userid)
	{
		//init();
		if(counter>=10) counter=0;
		int token=tokens[counter++];
		loginMap.put(userid, token);
		return token;
	}

	public static boolean isUserLoggedIn(String userid, int token)
	{
		System.out.println("TokenService:"+loginMap);
		if(loginMap.containsKey(userid))
		{
			int tkValue=loginMap.get(userid);
			return tkValue == token ? true:false;
		}
		return false;

	}
}
