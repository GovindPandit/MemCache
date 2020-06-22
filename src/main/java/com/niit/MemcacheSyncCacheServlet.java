package com.niit;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "MemcacheSync",urlPatterns = "/memcache")
public class MemcacheSyncCacheServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    
	  Cache cache;
	  
	  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	  Map<Object, Object> properties = new HashMap<>();
        //properties.put(MemcacheService.SetPolicy.SET_ALWAYS, true);
        //properties.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
        //properties.put(MemcacheService.SetPolicy.REPLACE_ONLY_IF_PRESENT, true);
        
        properties.put(GCacheFactory.EXPIRATION_DELTA, TimeUnit.HOURS.toSeconds(1));
        //properties.put(GCacheFactory.EXPIRATION_DELTA_MILLIS, 1000);
        //properties.put(GCacheFactory.EXPIRATION, new Date());
       
      try 
      {
    	  
    	  User user=new User();
    	  user.setUsername("Govind123");
    	  user.setEmail("Govind@gmail.com");
    	  
          CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
          cache = cacheFactory.createCache(properties);
          
          cache.put("user", user);
          syncCache.put("user", user);
          resp.sendRedirect("SecondServlet");
      }
      catch (CacheException e) 
      {
          
      }
  }
}