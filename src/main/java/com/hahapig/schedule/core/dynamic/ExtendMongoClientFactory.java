package com.hahapig.schedule.core.dynamic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;


/** 
 * 创建mongoclient 越想定制得多，写的代码越多
 * @author yangt 
 * @date 2014年9月4日 下午9:16:35 
 *  
 */
public class ExtendMongoClientFactory  implements FactoryBean<MongoClient>,DisposableBean{
 			
	final static Logger logger = LoggerFactory.getLogger(ExtendMongoClientFactory.class);
	
	private MongoClient mongoClient;
	//以下值需要出现在配置文件中------begin
	private String host;//单个数据库时，使用此地址
	
	private int port = 27017;//单个数据库时，使用此地址默认端口
	
	private Map<String, String> credentials;//授权信息<userName,psw>
	
	private String replicaSetIPs;//集群使用此IP集群
	
	private int replicaSetPort = 0;//集群使用此IP集群端口
	
	private String replicaSetName;//名字
	
	private String useReplicate;//是否使用集群
	
	private String authenticationDbname;//授权数据库的名称
	
	//以上值需要出现在配置文件中---end
	//以下值为选择配置----begin
	private int connectTimeout = 15000;
	
	private int maxWaitTime = 5000;
	
	private int socketTimeout = 0;
	
	private int threadsAllowedToBlockForConnectionMultiplier = 500;
	
	private boolean autoConnectRetry = true;
	//以上值为选择配置----end
	
	private static final String USEREPLICATE_CONDITION = "1";
	
	private static final String DEFAULT_REPLICATSET_NAME = "default_replicateset_name";
	
	@SuppressWarnings("deprecation")
	@Override
	public MongoClient getObject() throws Exception{
		Builder builder = MongoClientOptions.builder();
		builder.connectTimeout(connectTimeout);
		builder.maxWaitTime(maxWaitTime);
		builder.socketTimeout(socketTimeout);
		builder.autoConnectRetry(autoConnectRetry);
		builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
		logger.debug(builder.build().toString());
		
		if(!USEREPLICATE_CONDITION.equals(useReplicate)){//单机跑的情况----现在不需要用户名，密码---你妹通用配置也改来改去--现在使用集群的授权配置
			ServerAddress address = new ServerAddress(host,port);
			logger.debug("mongo地址为"+address.toString());
			mongoClient = new MongoClient(address,getAllCredentials(),builder.build());
		}else{//主-从复制的情况----现在需要用户名和密码
			builder.requiredReplicaSetName(replicaSetName==null?DEFAULT_REPLICATSET_NAME:replicaSetName);
			builder.readPreference(ReadPreference.nearest());
			if(StringUtils.isEmpty(replicaSetIPs)||replicaSetPort==0){
				throw new IllegalArgumentException("ExtendMongoClientFactory.replicaSetIPS和replicaSetPort不能为空，请检查配置");
			}
			
			String[] addresses = StringUtils.commaDelimitedListToStringArray(replicaSetIPs);
			List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
			for (String address : addresses) {
				ServerAddress adrs = new ServerAddress(address,replicaSetPort);
                serverAddresses.add(adrs );
                logger.debug("开始解析地址，mongo replicate地址包含---："+adrs.toString());
			}
			
			if(serverAddresses.size()==0){
				throw new IllegalArgumentException("ExtendMongoClientFactory初始化失败，可能replicaSet配置出错");
			}
			
			mongoClient = new MongoClient(serverAddresses,getAllCredentials(), builder.build());
		}
		
		return mongoClient;
	}
	
	private List<MongoCredential> getAllCredentials(){
		if(credentials==null){
			logger.debug("mogonDB不包含任何用户名和密码(mogonDB鉴权信息),请检查配置ExtendMongoClientFactory.credentials");
			return null;
		}
		
		List<MongoCredential> credentialsList = new ArrayList<>();
		MongoCredential credential = null;
		for (Iterator<String> iterator = credentials.keySet().iterator(); iterator.hasNext();) {
			String userName = iterator.next();
			String psw = credentials.get(userName);
			if(StringUtils.isEmpty(psw)){
				throw new IllegalArgumentException(" ExtendMongoClientFactory.credentials包含空值，请检查配置");
			}
			credential = MongoCredential.createMongoCRCredential(userName, authenticationDbname, psw.toCharArray());
			credentialsList.add(credential);
		}
		
		return credentialsList;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	public void setMaxWaitTime(int maxWaitTime){
		this.maxWaitTime = maxWaitTime;
	}

	public void setSocketTimeout(int socketTimeout){
		this.socketTimeout = socketTimeout;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier){
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}
	
	public void setAutoConnectRetry(boolean autoConnectRetry){
		this.autoConnectRetry = autoConnectRetry;
	}

	@Override
	public Class<Mongo> getObjectType(){
		return Mongo.class;
	}

	@Override
	public boolean isSingleton(){
		return true;
	}
	
	public void setReplicaSetName(String replicaSetName){
		this.replicaSetName = replicaSetName;
	}

	public void setCredentials(Map<String, String> credentials){
		this.credentials = credentials;
	}

	public String getAuthenticationDbname(){
		return authenticationDbname;
	}

	public void setAuthenticationDbname(String authenticationDbname){
		this.authenticationDbname = authenticationDbname;
	}


	public int getReplicaSetPort(){
		return replicaSetPort;
	}

	public void setReplicaSetPort(int replicaSetPort){
		this.replicaSetPort = replicaSetPort;
	}
	
	public void setReplicaSetIPs(String replicaSetIPs){
		this.replicaSetIPs = replicaSetIPs;
	}
	

	public void setUseReplicate(String useReplicate){
		this.useReplicate = useReplicate;
	}

	@Override
	public void destroy() throws Exception{
		if(mongoClient!=null){
			mongoClient.close();
		}
	}

}
