/*
 *Copyright (c) [Year] [name of copyright holder]
 *[Software Name] is licensed under Mulan PubL v2.
 *You can use this software according to the terms and conditions of the Mulan PubL v2.
 *You may obtain a copy of Mulan PubL v2 at:
 *         http://license.coscl.org.cn/MulanPubL-2.0
 *THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 *EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 *MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 *See the Mulan PubL v2 for more details.
 */
package com.lamp.atom.schedule.api;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.
 * 
 * @author laohu
 *
 */
public interface AtomServiceShedule {

	public default void createService(Schedule schedule) {
	}

	public default void closeService(Schedule schedule) {
	}
	
	public default List<Object> queryAllService() {return new ArrayList<>(1);}

}
