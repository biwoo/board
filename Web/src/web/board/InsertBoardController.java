package web.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.board.biz.BoardService;
import service.board.domain.Board;


public class InsertBoardController implements Controller{

	@Autowired BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Board board = new Board();
		
		board.setBoardTitle(request.getParameter("title"));
		board.setBoardContents(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		board.setOrderNum(1);
		board.setParentSeqNum(1);
		
		int result = boardService.insertBoard(board);
		
		System.out.println(result);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/view/board.jsp");
		modelAndView.addObject("result", result);
		
		return modelAndView;
	}
	
}
