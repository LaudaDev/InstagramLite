package eu.execom.instagramlite.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import eu.execom.instagramlite.models.UserPost;

/**
 * Created by nikolalukic on 5/6/16.
 */
public class UserPostDAO extends RuntimeExceptionDao<UserPost, Integer> {

    public UserPostDAO(Dao<UserPost, Integer> dao) {
        super(dao);
    }

}
