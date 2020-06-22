package com.niit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;

@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		  Cache cache;
		  
		  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		 
	        
		  PrintWriter out=resp.getWriter();

	      try 
	      {
	    	  
	    	  CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	          cache = cacheFactory.createCache(Collections.emptyMap());
	          
	          User user1=(User)cache.get("user");
	          User user2=(User)syncCache.get("user");
	          
	          out.println("Username: "+user1.getUsername()+"<br>");
	          out.println("Email: "+user1.getEmail()+"<br>");
	          out.println("<br><br>");
	          
	          out.println("Username: "+user2.getUsername()+"<br>");
	          out.println("Email: "+user2.getEmail()+"<br>");
	          out.println("<br>");
	      }
	      catch (CacheException e) 
	      {
	          
	      }
	}
}
