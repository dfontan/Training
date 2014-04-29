package com.intelligrape.taglib;

import java.util.List;

import com.day.cq.wcm.api.Page;

public class LinkComponent
{
	String url;
	String title;
	List<LinkComponent> validChild;
	
	public List<LinkComponent> getValidChild() {
		return validChild;
	}
	public void setValidChild(List<LinkComponent> validChild) {
		this.validChild = validChild;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/*public LinkComponent(Page page) {
		super();
		this.page = page;
	}*/
	

}
