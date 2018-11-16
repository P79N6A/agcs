package org.agcs.system.hibernate.util;

import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.util.MyBeanUtil;
import org.agcs.system.hibernate.pojo.ModelView;
import org.springframework.web.servlet.support.RequestContext;

/**
 * 国际化翻译
 * @author Administrator
 *
 */
public class I18nUtil {
	
	public static ModelView i18n(HttpServletRequest request, ModelView mv){
		ModelView i18n = null;
		if(mv != null){
			//从后台代码获取国际化信息
			i18n = new ModelView();
			try {
				MyBeanUtil.copyBean2Bean(i18n, mv);
			} catch (Exception e) {
				e.printStackTrace();
			}
            RequestContext requestContext = new RequestContext(request);
            i18n.setMessage(requestContext.getMessage(mv.getMessage()));
		}
		return i18n;
	}
	
}
