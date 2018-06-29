package com.gcx.api.common.wechatutils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * httpClient连接池
 */
public class HttpClientPool {

	/**************************初始化HttpClient连接池
	 * 1.使用连接池时，要尽快消费响应体并释放连接到连接池，不要保持太久
	 * 2.在开启长连接时才是真正的连接池，如果是短连接，则只是作为一个信号量来限制总请求数，连接并没有实现复用
	 * 3.不推荐使用HttpEntity#getContent#close方法来释放连接，处理不好异常将导致连接不释放，推荐使用EntityUtils.consume(response.getEntity()),
	 * EntityUtils.toString(response.getEntity())消费响应体
	 * *********/
	static PoolingHttpClientConnectionManager manager = null;
	static CloseableHttpClient httpClient = null;
	
	public static synchronized CloseableHttpClient getHttpClient(){
		if(httpClient==null){
			// 注册访问协议相关的Socket工厂
			org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry = org.apache.http.config.RegistryBuilder.<ConnectionSocketFactory>create()
			.register("http", PlainConnectionSocketFactory.INSTANCE)
			.register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
			.build();
			// HttpConnection工厂：配置写请求/解析响应处理器
			HttpConnectionFactory<HttpRoute,ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(DefaultHttpRequestWriterFactory.INSTANCE,
			DefaultHttpResponseParserFactory.INSTANCE);
			// DNS解析器
			DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
			// 创建池化连接管理器
			manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry,connFactory,dnsResolver);
			// 默认为Socket配置
			SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			manager.setDefaultSocketConfig(defaultSocketConfig);
			
			manager.setMaxTotal(300); // 设置整个连接池的最大连接数
			// 每个路由的默认最大连接，每个路由实际最大连接数默认为DefaultMaxPerRoute控制，而MaxTotal是控制整个池子最大数
			// 设置过小无法支持大并发（ConnectionPoolTimeoutException:Timeout waiting for connection from pool）
			// 路由是对maxTotal的细分
			manager.setDefaultMaxPerRoute(200); // 每个路由最大连接数
			// 在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
			manager.setValidateAfterInactivity(5*1000);
			
			// 默认请求配置
			RequestConfig defalutRequestConfig = RequestConfig.custom()
					.setConnectTimeout(2*1000) // 设置连接超时时间 2s
					.setSocketTimeout(8*1000) //设置等待数据超时时间8s
					.setConnectionRequestTimeout(2*1000) // 设置从连接池获取连接的等待超时间 2s
					.build();
			// 创建httpclient
			httpClient = HttpClients.custom()
					.setConnectionManager(manager)
					.setConnectionManagerShared(false) // 连接池不是共享模式
					.evictIdleConnections(60, TimeUnit.SECONDS) // 定期回收空闲连接
					.evictExpiredConnections()  // 定期回收过期连接
					.setConnectionTimeToLive(60, TimeUnit.SECONDS) // 连接存活时间，如果不设置则根据长连接信息决定
					.setDefaultRequestConfig(defalutRequestConfig) /// 设置默认请求配置
					.setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE) // 连接重用策略，即是否能keepAlive
					.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)  // 长连接配置，即获取长连接生产多长时间
					.setRetryHandler(new DefaultHttpRequestRetryHandler(0,false)) // 设置重试次数，默认是3次；当前是禁用掉（根据需要开启）
					.build();
			
			// JVM停止或重启时，关闭连接池释放掉连接(跟数据库连接池类似)
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					try {
						httpClient.close();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Throwable ex){
						ex.printStackTrace();
					}
				}
			});
		}
		
		return httpClient;
	}
}
