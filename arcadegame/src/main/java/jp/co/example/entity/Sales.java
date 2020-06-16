package jp.co.example.entity;

public class Sales {

	private Integer saleId;
	private Integer userId;
	private Integer giveItem;
	private Integer takeItem;
	private Integer saleFlag;

	public Sales() {



	}


	public Sales(Integer saleId, Integer userId, Integer giveItem, Integer takeItem, Integer saleFlag) {

		this.saleId = saleId;
		this.userId = userId;
		this.giveItem = giveItem;
		this.takeItem = takeItem;
		this.saleFlag = saleFlag;

	}


	public Integer getSaleId() {
		return saleId;
	}


	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getGiveItem() {
		return giveItem;
	}


	public void setGiveItem(Integer giveItem) {
		this.giveItem = giveItem;
	}


	public Integer getTakeItem() {
		return takeItem;
	}


	public void setTakeItem(Integer takeItem) {
		this.takeItem = takeItem;
	}


	public Integer getSaleFlag() {
		return saleFlag;
	}


	public void setSaleFlag(Integer saleFlag) {
		this.saleFlag = saleFlag;
	}




}
