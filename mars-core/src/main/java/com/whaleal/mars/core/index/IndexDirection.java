/**
 *    Copyright 2020-present  Shanghai Jinmu Information Technology Co., Ltd.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the Server Side Public License, version 1,
 *    as published by Shanghai Jinmu Information Technology Co., Ltd.(The name of the development team is Whaleal.)
 *
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    Server Side Public License for more details.
 *
 *    You should have received a copy of the Server Side Public License
 *    along with this program. If not, see
 *    <http://www.whaleal.com/licensing/server-side-public-license>.
 *
 *    As a special exception, the copyright holders give permission to link the
 *    code of portions of this program with the OpenSSL library under certain
 *    conditions as described in each individual source file and distribute
 *    linked combinations including the program with the OpenSSL library. You
 *    must comply with the Server Side Public License in all respects for
 *    all of the code used other than as permitted herein. If you modify file(s)
 *    with this exception, you may extend this exception to your version of the
 *    file(s), but you are not obligated to do so. If you do not wish to do so,
 *    delete this exception statement from your version. If you delete this
 *    exception statement from all source files in the program, then also delete
 *    it in the license file.
 */
package com.whaleal.mars.core.index;

/**
 * @author wh
 * @Date 2021-01-11
 * @desc 定义了索引的方向
 * 枚举类
 */
public enum IndexDirection {
    ASC(1),
    DESC(-1),
    GEO2D("2d"),
    GEO2DSPHERE("2dsphere"),
    HASHED("hashed"),
    TEXT("text");

    private final Object direction;

    IndexDirection(Object o) {
        direction = o;
    }


    public static IndexDirection fromValue(Object value) {

        if (Double.class.equals(value.getClass())) {
            value = (double) value > 0 ? 1 : -1;
        }
        for (IndexDirection indexDirection : values()) {
            if (indexDirection.direction.equals(value)) {

                return indexDirection;
            }
        }
        throw new IllegalArgumentException("No enum value found for " + value);
    }

    /**
     * Returns the value as needed by the index definition document
     *
     * @return the value
     * <p>
     * 主要用于索引的创建   直接返回其 Object  值
     */
    public Object toIndexValue() {
        return direction;
    }
}
