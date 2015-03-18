package tank.policy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tank.policy.core.PolicyCoderFactory;
import tank.policy.core.PolicyFileHandler;

/**
 * @author tank
 * @email kaixiong.tan@qq.com
 * @date:2015年3月18日 下午4:52:38
 * @description:响应flash自动沙箱请求的安全协议服务器
 * @version :0.1
 */

public class StartRun {
	private static Logger logger = LoggerFactory.getLogger(StartRun.class);

	public static void main(String[] args) {
		int port = 843;

		SocketAcceptor acceptor = new NioSocketAcceptor();
		// 设置解析器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

		PolicyCoderFactory textCodec = new PolicyCoderFactory(Charset.forName("utf-8"));

		chain.addLast("codec", new ProtocolCodecFilter(textCodec));

		acceptor.setHandler(new PolicyFileHandler());
		try {
			acceptor.bind(new InetSocketAddress(port));
			logger.info("mina 沙箱请求的安全协议服务器启动，监听端口{}", port);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("{}", e);
		}

	}

}
