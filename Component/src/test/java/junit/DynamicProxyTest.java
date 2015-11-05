package junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import service.board.biz.BoardService;
import service.board.biz.exam.dynamic.Hello;
import service.board.biz.exam.dynamic.HelloImpl;
import service.board.biz.exam.dynamic.UpperCaseHandler;
import service.board.biz.exam.tran.dynamic.TransactionHandler;
import service.board.domain.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/config/application-exam-context.xml")
public class DynamicProxyTest {
	private Board board;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Before
	public void setUp(){
		
		board = new Board();
		board.setBoardTitle("Title");
		board.setBoardContents("Contents");
		board.setWriter("conWriter");
		board.setOrderNum(1);
	}
	
	@Test
	public void factoryProxy() throws Exception{
		
		BoardService txBoardService = (BoardService)context.getBean("txFactoryBoardService");
		
		txBoardService.deleteAllBoard();
		txBoardService.insertBoard(board);
		
		List<Board> list = txBoardService.selectBoard(new Board());
		Board board1 = txBoardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board1.getBoardTitle(), is(board.getBoardTitle()));
	}
	

	@Test
	public void springProxyFactoryBean() throws Exception{            
		BoardService txBoardService = (BoardService)context.getBean("txBoardService");
		
		txBoardService.deleteAllBoard();
		txBoardService.insertBoard(board);
		
		List<Board> list = txBoardService.selectBoard(new Board());
		Board board1 = txBoardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board1.getBoardTitle(), is(board.getBoardTitle()));
	}
	
	@Test
	public void autoProxyFactoryBean() throws Exception{            
		
		boardService.deleteAllBoard();
		boardService.insertBoard(board);
		
		List<Board> list = boardService.selectBoard(new Board());
		Board board1 = boardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board1.getBoardTitle(), is(board.getBoardTitle()));
	}
	
	@Test
	public void dynamicProxy() throws Exception{
		TransactionHandler transactionHandler = new TransactionHandler();
	
		transactionHandler.setBoardService(boardService);
		transactionHandler.setPattern("insert");
		transactionHandler.setTransactionManager(transactionManager);
		
		BoardService boardService = (BoardService)Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[]{BoardService.class},
				transactionHandler);
		
		
		boardService.deleteAllBoard();
		boardService.insertBoard(board);
		
		List<Board> list = boardService.selectBoard(new Board());
		Board board1 = boardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board1.getBoardTitle(), is(board.getBoardTitle()));
	}
	
	@Test
	public void hello(){
		service.board.biz.exam.reflect.Hello hello = new service.board.biz.exam.reflect.HelloUppercase(new service.board.biz.exam.reflect.HelloImpl());
		assertThat(hello.sayHello("tobe"), is("TOBE HELLO"));
	}
	
	@Test
	public void dynamicProxyHello(){
		Hello hello = (Hello)Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[]{Hello.class},
				new UpperCaseHandler(new HelloImpl()));
		
		assertThat(hello.sayHello("tobe"), is("TOBE HELLO"));
		assertThat(hello.sayHi("tobe"), is("TOBE HI"));
	}
	
	@Test
	public void springFactoryBean(){
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		
		pfBean.addAdvice(new UppercaseAdvice());
		
		pfBean.setTarget(new HelloImpl());
		
		Hello hello = (Hello)pfBean.getObject();
		
		assertThat(hello.sayHello("tobe"), is("TOBE HELLO"));
		assertThat(hello.sayHi("tobe"), is("TOBE HI"));
	}
	
	@Test
	public void pointcutAdvisor(){
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayHe*");
		
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

		pfBean.setTarget(new HelloImpl());
		
		Hello hello = (Hello)pfBean.getObject();
		
		assertThat(hello.sayHello("tobe"), is("TOBE HELLO"));
		assertThat(hello.sayHi("tobe"), is("TOBE HI"));
		
	}
	
	public class UppercaseAdvice implements MethodInterceptor{
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret = (String)invocation.proceed();
			return ret.toUpperCase();
		}
	}
	 
	
}
