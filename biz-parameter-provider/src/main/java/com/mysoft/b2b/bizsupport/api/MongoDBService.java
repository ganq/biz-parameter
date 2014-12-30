/**
 * 
 */
package com.mysoft.b2b.bizsupport.api;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;

/**
 * @author liucz
 *
 */
public interface MongoDBService {
	Datastore getDatastore();
	DB getDb();
    Morphia getMorphia();

}
