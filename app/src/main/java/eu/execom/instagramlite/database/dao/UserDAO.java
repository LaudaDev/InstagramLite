package eu.execom.instagramlite.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import eu.execom.instagramlite.models.User;

/**
 * Created by nikolalukic on 5/6/16.
 */
public class UserDAO extends RuntimeExceptionDao<User, Integer> {

    public UserDAO(Dao<User, Integer> dao) {
        super(dao);
    }

}
