package web.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.board.biz.BoardService;


public class ViewBoardController implements Controller{

	@Autowired BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/view/board.jsp");
		modelAndView.addObject("title", "제목");
		modelAndView.addObject("content", "내용");
		modelAndView.addObject("writer", "작성자");
		
		return modelAndView;
	}
	
}
