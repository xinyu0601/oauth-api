package com.xin.oauth.mapper;

import com.xin.oauth.models.entity.AppEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author xinyu.huang02
 * @date 2020-02-26 17:40
 * @class App Info Mapper
 */
@Repository
public interface AppMapper {

    @Insert("INSERT INTO app(app_name, app_secret, app_key, description, callback_url, user_id, create_time, is_open, update_time) VALUES(#{appName}, #{appSecret}, " +
            "#{appKey}, #{description}, #{callbackUrl}, #{userId}, #{createTime}, #{isOpen}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(AppEntity app);


    @Select("SELECT id, create_time, is_open, app_name, app_secret, app_key, description, callback_url, update_time, user_id " +
            "FROM app WHERE app_key = #{appKey} AND is_open = 1")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "app_name", property = "appName"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "callback_url", property = "callbackUrl"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "user_id", property = "userId")
    })
    AppEntity selectByAppKey(@Param("appKey") String appKey);


    @Select("SELECT id, create_time, is_open, app_name, app_secret, app_key, description, callback_url, update_time, user_id " +
            "FROM app WHERE app_name = #{appName} AND is_open = 1")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "app_name", property = "appName"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "callback_url", property = "callbackUrl"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "user_id", property = "userId")
    })
    AppEntity selectByAppName(@Param("appName") String appName);


    @Select("SELECT id, create_time, is_open, app_name, app_secret, app_key, description, callback_url, update_time, user_id " +
            "FROM app WHERE id = #{appId} AND is_open = 1")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "app_name", property = "appName"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "callback_url", property = "callbackUrl"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "user_id", property = "userId")
    })
    AppEntity selectByAppId(@Param("appId") Long appId);


    @Select("SELECT id, create_time, is_open, app_name, app_secret, app_key, description, callback_url, update_time, user_id " +
            "FROM app WHERE app_key = #{appKey} AND app_secret = #{appSecret} AND is_open = 1")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "app_name", property = "appName"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "callback_url", property = "callbackUrl"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "user_id", property = "userId")
    })
    AppEntity selectByAppKeyAndAppSecret(@Param("appKey") String appKey, @Param("appSecret") String appSecret);


    @Update("UPDATE app SET app_name=#{appName}, description=#{description}, callback_url=#{callbackUrl}, update_time=#{updateTime} WHERE id =#{id}")
    void update(AppEntity app);


    @Update("UPDATE app SET app_name=#{appName}, description=#{description}, callback_url=#{callbackUrl} WHERE app_key=#{appKey}")
    void updateByAppKey(AppEntity app);


    @Delete("DELETE FROM app WHERE id=#{id}")
    void delete(Long id);


    @Delete("DELETE FROM app WHERE app_key=#{appKey}")
    void deleteByAppKey(String appKey);


    @Delete("DELETE FROM app WHERE app_name=#{appName}")
    void deleteByAppName(String appName);


    @Select("SELECT count(*) FROM app")
    int total();


    @Select("SELECT id, create_time, is_open, app_name, app_secret, app_key, description, callback_url, update_time, user_id " +
            "FROM app WHERE user_id = #{userId} AND is_open = 1")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "is_open", property = "isOpen"),
            @Result(column = "app_name", property = "appName"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "callback_url", property = "callbackUrl")
    })
    List<AppEntity> selectByUserId(Long userId);
}
