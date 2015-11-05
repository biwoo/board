package system.advice;

import java.lang.reflect.InvocationTargetException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor{

	@Autowired
	public PlatformTransactionManager transactionManager = null;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object ret = null;
		
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			ret = invocation.proceed();		
			transactionManager.commit(status);
		}catch(InvocationTargetException e){
			transactionManager.rollback(status);
			throw e;
		}
		
		return ret;
	}
	
}
