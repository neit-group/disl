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
package org.disl.pattern

import groovy.transform.CompileStatic
import org.disl.meta.Table

/**
 * Pattern for Table.
 * */
@CompileStatic
abstract class TablePattern<T extends Table> extends Pattern {
    T table

    void addSqlScriptStep(String name, String code) {
        add(ExecuteSQLScriptTableStep.create(name, code))
    }

    @Override
    public String toString() {
        "${this.getClass().getSimpleName()}(${getTable()})"
    }
}