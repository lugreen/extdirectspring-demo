/**
 * Copyright 2010-2012 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.extdirectspring.demo.bancha;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@Service
public class BookService {

	@Autowired
	private UserService userService;

	private final static Map<Integer, Book> bookDb = Maps.newConcurrentMap();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 35; i++) {
			Book b = new Book();
			b.setId(i + 1);
			b.setTitle("Book " + (i + 1));
			b.setPublished(i % 3 == 0);
			b.setUser_id((i % 4) + 1);
			bookDb.put(b.getId(), b);
		}
	}

	@ExtDirectMethod(value = ExtDirectMethodType.STORE_READ, group = "bancha")
	public List<Book> read() {
		return ImmutableList.copyOf(bookDb.values());
	}

}