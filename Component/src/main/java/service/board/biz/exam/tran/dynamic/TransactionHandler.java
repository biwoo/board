package service.board.biz.exam.tran.dynamic;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import service.board.biz.BoardService;

public class TransactionHandler implements InvocationHandler{

	private String pattern;
	
	private PlatformTransactionManager transactionManager = null;
	
	private BoardService boardService;
	
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager){
		this.transactionManager = transactionManager;
	}
	
	public void setBoardService(BoardService boardService){
		this.boardService = boardService;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Object ret = null;
		
		if(method.getName().startsWith(pattern)){
			ret = invokeInTransaction(method, args);
		}else{
			ret = method.invoke(boardService, args);	
		}
		
		return ret;
	}
	
	public Object invokeInTransaction(Method method, Object[] args) throws Throwable{
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		Object ret = null;
		
		try{
			ret = method.invoke(boardService, args);		
			transactionManager.commit(status);
		}catch(InvocationTargetException e){
			transactionManager.rollback(status);
			throw e;
		}
		
		return ret;
	}
	
}
