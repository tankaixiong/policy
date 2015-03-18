package tank.policy.core;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.utils.file.FileHelper;

/**
 * @author tank
 * @email kaixiong.tan@qq.com
 * @date:Jan 23, 2014 4:58:42 PM
 * @description:
 * @version :1.0
 */
@Component
public class PolicyFileUtils {
	private static final Logger log = LoggerFactory.getLogger(PolicyFileUtils.class);
	private static String xmlContext;
	static {
		// 加载跨域策略文件

		if (xmlContext == null) {
			xmlContext = new FileHelper().readTXT(PolicyFileUtils.class.getResource("").getPath() + "crossdomain.xml") + "\0";
		}

	}

	public static String getPolicyFile() {

		return xmlContext;
	}
}
