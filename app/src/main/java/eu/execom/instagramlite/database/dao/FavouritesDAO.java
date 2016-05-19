package eu.execom.instagramlite.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import eu.execom.instagramlite.models.Favourite;

/**
 * Created by nikolalukic on 5/6/16.
 */
public class FavouritesDAO extends RuntimeExceptionDao<Favourite, Integer> {

    public FavouritesDAO(Dao<Favourite, Integer> dao) {
        super(dao);
    }

}
