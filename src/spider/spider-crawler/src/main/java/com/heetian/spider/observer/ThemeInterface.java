package com.heetian.spider.observer;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.exception.ObserverRegister;

public interface ThemeInterface {
	/**
	 * 观察者集合
	 */
	public static final List<AbstractListen> observers = new ArrayList<AbstractListen>();
	/**
	 * 添加
	 * @param observer
	 */
	public void setObserver(AbstractListen observer);
	/**
	 * 获得
	 * @return
	 */
	public AbstractListen getObserver(String name) throws ObserverRegister;
	/**
	 * 删除
	 * @param observer
	 */
	public void removeObserver(AbstractListen observer);
}
