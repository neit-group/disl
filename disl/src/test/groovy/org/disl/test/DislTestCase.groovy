/*
 * Copyright 2015 - 2016 Karel H�bl <karel.huebl@gmail.com>.
 *
 * This file is part of disl.
 *
 * Disl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Disl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Disl.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.disl.test

import org.disl.meta.Context
import org.disl.meta.MetaFactory
import org.disl.meta.TestTable.TESTING_TABLE
import org.disl.util.test.AbstractDislTestCase

class DislTestCase extends AbstractDislTestCase {
	
	static {
		Context.setContextName("disl-test")
		def sql=Context.getSql("default")
		sql.execute("CREATE TABLE DUAL (dummy char(1))")
		sql.execute("INSERT INTO DUAL VALUES ('X')")
		MetaFactory.create(TESTING_TABLE).execute()		
	}
	
}
