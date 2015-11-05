package service.board.domain;


public class Board {
	
	//게시글 일련번호
	int seqNum;
	
	//게시글 제목
	String boardTitle;
	
	//게시물 내용
	String boardContents;
	
	//작성자
	String writer;
	
	//작성시간
	String inputTime;
	
	//부모 게시글 일련번호
	int parentSeqNum;
	
	//게시글 정렬순번
	int orderNum;

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public int getParentSeqNum() {
		return parentSeqNum;
	}

	public void setParentSeqNum(int parentSeqNum) {
		this.parentSeqNum = parentSeqNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
