package org.test.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyChatClient {
	
	public void connect(String host, int port) throws InterruptedException{
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootStrap = new Bootstrap();
			bootStrap.group(workerGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel sc)
							throws Exception {
						sc.pipeline().addLast(new SimpleClientHandler());
						
					}
					
				});
			ChannelFuture f = bootStrap.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} finally{
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		NettyChatClient clinet = new NettyChatClient();
		clinet.connect("127.0.0.1", 9089);
	}

}

class SimpleClientHandler extends ChannelInboundHandlerAdapter{
	
	@Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("SimpleClientHandler.channelRead");    
        ByteBuf result = (ByteBuf) msg;    
        byte[] result1 = new byte[result.readableBytes()];    
        result.readBytes(result1);    
        System.out.println("Server said:" + new String(result1));    
        result.release();    
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        // 当出现异常就关闭连接  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
      
    // 连接成功后，向server发送消息    
    @Override    
    public void channelActive(ChannelHandlerContext ctx) throws Exception {    
        String msg = "hello Server!";    
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());    
        encoded.writeBytes(msg.getBytes());    
        ctx.write(encoded);    
        ctx.flush();    
    } 
	
}
