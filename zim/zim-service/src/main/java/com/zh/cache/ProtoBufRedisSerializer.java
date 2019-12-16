package com.zh.cache;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author zhangheng
 * @date 2019/12/13
 */
public class ProtoBufRedisSerializer implements RedisSerializer {

    private static final Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return ProtobufIOUtil.toByteArray(new ObjectWrapper(o), schema, LinkedBuffer.allocate());
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        ObjectWrapper objectWrapper = new ObjectWrapper();
        ProtobufIOUtil.mergeFrom(bytes, objectWrapper, schema);
        return objectWrapper.getObject();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class ObjectWrapper {
        private Object object;
    }
}
