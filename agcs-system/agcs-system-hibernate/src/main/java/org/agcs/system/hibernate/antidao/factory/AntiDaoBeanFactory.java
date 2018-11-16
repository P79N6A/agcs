package org.agcs.system.hibernate.antidao.factory;

import java.util.List;
import java.util.Set;

import org.agcs.system.hibernate.antidao.annotation.AntiDao;
import org.agcs.system.hibernate.antidao.util.AntiDaoUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class AntiDaoBeanFactory implements BeanFactoryPostProcessor{

	private static final Logger log = Logger.getLogger(AntiDaoBeanFactory.class);
	
	private List<String> packageScan;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		log.info(".......scan register bean dao start.........");
		try {
			for(String pack : packageScan){
				if(StringUtils.isNotEmpty(pack)){
					Set<Class<?>> classSet = PackagesToScanUtil.getClasses(pack);
					for (Class antiDaoClass : classSet){
						if(antiDaoClass.isAnnotationPresent(AntiDao.class)){
							ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
							proxyFactoryBean.setBeanFactory(beanFactory);
							proxyFactoryBean.setInterfaces(new Class[]{antiDaoClass});
							proxyFactoryBean.setInterceptorNames(new String[]{"antiDaoHandler"});
							String beanName = AntiDaoUtil.getFirstSmall(antiDaoClass.getSimpleName());
							if(!beanFactory.containsBean(beanName)){
								beanFactory.registerSingleton(beanName, proxyFactoryBean.getObject());
							}
						}
					}
				}
			}
			log.info(".......scan register bean dao end.........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getPackageScan() {
		return packageScan;
	}

	public void setPackageScan(List<String> packageScan) {
		this.packageScan = packageScan;
	}
	
	
}
