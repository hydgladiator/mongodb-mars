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
package com.whaleal.mars.bean;

import com.whaleal.mars.core.index.IndexDirection;
import com.whaleal.mars.core.index.annotation.Field;
import com.whaleal.mars.core.index.annotation.Index;
import com.whaleal.mars.core.index.annotation.IndexOptions;
import com.whaleal.mars.core.index.annotation.Indexes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.whaleal.mars.bson.codecs.pojo.StorageType;
import com.whaleal.mars.bson.codecs.pojo.annotations.CappedAt;
import com.whaleal.mars.bson.codecs.pojo.annotations.Entity;
import com.whaleal.mars.bson.codecs.pojo.annotations.MongoId;
import com.whaleal.mars.bson.codecs.pojo.annotations.MongoProperty;

@Entity(value = "stu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Indexes({@Index( fields = {@Field(value = "salary", type = IndexDirection.ASC), @Field(value = "name", type = IndexDirection.DESC)},options=@IndexOptions(background = true ,name = "salary"))
        ,@Index(fields = {@Field(value = "idcc",type = IndexDirection.HASHED)})
        ,@Index(fields = {@Field(value = "idcc",type = IndexDirection.ASC)},options = @IndexOptions(background = false,name = "ccdi",unique = true,expireAfterSeconds = 10))})
public class Student {

    @MongoId(value = StorageType.STRING)
    private String stuNo;

    private String classNo;

    private String stuName;

    private Integer stuAge;

    @MongoProperty(value = "height", storageType = StorageType.STRING)
    private Double stuHeight;

    @MongoProperty(value = "sex")
    private Gender stuSex;

    @MongoProperty(value = "cscore")
    private Double chineseScore;

    @MongoProperty(value = "mscore")
    private Double mathScore;

    @MongoProperty(value = "escore")
    private Double englishScore;

}
