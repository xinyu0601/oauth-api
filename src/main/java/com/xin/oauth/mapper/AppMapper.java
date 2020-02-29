package com.xin.oauth.mapper;

import com.xin.oauth.models.entity.AppEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author xinyu.huang02
 * @date 2020-02-26 17:40
 * @class 应用信息 Mapper
 */
@Repository
public interface AppMapper {

    @Insert("INSERT INTO app(app_name, app_secret, app_key, description, callback_url, create_time) VALUES(#{appName}, #{appSecret}, " +
            "#{appKey}, #{description}, #{callbackUrl}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(AppEntity app);


    @Select("SELECT app_name, app_secret, app_key, description, callback_url " +
            "FROM app WHERE app_key = #{appKey} AND is_open = 0")
    AppEntity selectByAppKey(@Param("appKey") String appKey);


    @Select("SELECT app_name, app_secret, app_key, description, callback_url " +
            "FROM app WHERE id = #{appId} AND is_open = 0")
    AppEntity selectByAppId(@Param("id") String appId);


    @Select("SELECT app_name, app_secret, app_key, description, callback_url " +
            "FROM app WHERE app_key = #{appKey} AND app_secret = #{appSecret} AND is_open = 0")
    AppEntity selectByAppKeyAndAppSecret(@Param("appKey") String appKey, @Param("appSecret") String appSecret);


    @Update("UPDATE app SET app_name=#{appName}, description=#{description}, callback_url=#{callbackUrl} WHERE id =#{id}")
    void update(AppEntity app);


    @Update("UPDATE app SET app_name=#{appName}, description=#{description}, callback_url=#{callbackUrl} WHERE app_key=#{appKey}")
    void updateByAppKey(AppEntity app);


    @Delete("DELETE FROM app WHERE id=#{id}")
    void delete(Long id);


    @Delete("DELETE FROM app WHERE app_key=#{appKey}")
    void deleteByAppKey(String appKey);


    @Select("SELECT count(*) FROM app")
    int total();
}
