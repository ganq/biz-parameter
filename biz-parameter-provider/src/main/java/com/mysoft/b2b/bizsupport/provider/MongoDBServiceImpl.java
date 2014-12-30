package com.mysoft.b2b.bizsupport.provider;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mysoft.b2b.bizsupport.api.BidOperationCategory;
import com.mysoft.b2b.bizsupport.api.MongoDBService;
import com.mysoft.b2b.bizsupport.api.SupplierOperationCategory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * MONGODB服务
 * 
 * @author liucz
 * 
 */
public class MongoDBServiceImpl implements MongoDBService {
	private static final Log log = LogFactory.getLog(MongoDBServiceImpl.class);
	private static Datastore datastore;
	
	private String adds;
	private String databaseName;
	private String userName;
	private String password;

	private static DB db;
	private String address;
	private int port;
    private Morphia morphia;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DB getDb() {
		return db;
	}

	/*public void creatMongoDb() {
		List<ServerAddress> addr = new ArrayList<ServerAddress>();
		try {
			String [] _adds = adds.split(",");
			for(String _path : _adds){
				addr.add(new ServerAddress(_path));
			}			
			Mongo mongo = new Mongo(addr);
		//try {
			//Mongo mongo = new Mongo(address, port);
			db = mongo.getDB(databaseName);
			if (!db.authenticate(userName, password.toCharArray())) {
				log.info("MongoDb验证失败！ ");
			}
		} catch (Exception e) {
			log.error("creatMongoDb error ", e);
		}
	}*/
	
	public Datastore getDatastore() {
		return datastore;
	}
	
	/**
	 * 启动MONGO
	 */
	public void creatMongoDb() {
		log.info("mongo run***************");
		List<ServerAddress> addr = new ArrayList<ServerAddress>();
		try {
			String [] _adds = adds.split(",");
			for(String _path : _adds){
				addr.add(new ServerAddress(_path));
			}
			
			Mongo mongo = new Mongo(addr);
			Morphia morphia = new Morphia();
			morphia.map(BidOperationCategory.class);
			//morphia.getMapper().getMappedClass(mongo).addAnnotation(clazz, ann);
			morphia.map(SupplierOperationCategory.class);
			//morphia.mapPackage("com.mysoft.b2b.bizsupport.api");			//加载包CALSS
			datastore = morphia.createDatastore(mongo, databaseName);
			datastore.ensureIndexes();  // ensureIndexes() 调用可以指示数据存储创建所需且不存在的索引
            this.morphia = morphia;
		} catch (Exception e) {
			log.info("mongo run   error*******************");
			log.error("mongo run error ",e);
		}
	}

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }
}
