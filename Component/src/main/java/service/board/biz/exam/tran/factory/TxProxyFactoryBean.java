package service.board.biz.exam.tran.factory;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import service.board.biz.BoardService;
import service.board.biz.exam.tran.dynamic.TransactionHandler;

public class TxProxyFactoryBean implements FactoryBean<BoardService>{
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Override
	public BoardService getObject() throws Exception {
		TransactionHandler transactionHandler = new TransactionHandler();
		
		transactionHandler.setBoardService(boardService);
		transactionHandler.setPattern("insert");
		transactionHandler.setTransactionManager(transactionManager);
		
		BoardService boardService = (BoardService)Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[]{BoardService.class},
				transactionHandler);
		
		return boardService;
	}

	@Override
	public Class<?> getObjectType() {		
		return BoardService.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
