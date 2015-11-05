package service.board.domain;

public class BoardReply {
    
	int seqNum;
	
	String replyContents;
	
	String writer;
	
	String inputTime;
	
	int boardSeqNum;
	
	int parentSeqNum;
	
	int groupNum;
	
	int orderNum;
	
	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
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

	public int getBoardSeqNum() {
		return boardSeqNum;
	}

	public void setBoardSeqNum(int boardSeqNum) {
		this.boardSeqNum = boardSeqNum;
	}

	public int getParentSeqNum() {
		return parentSeqNum;
	}

	public void setParentSeqNum(int parentSeqNum) {
		this.parentSeqNum = parentSeqNum;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
