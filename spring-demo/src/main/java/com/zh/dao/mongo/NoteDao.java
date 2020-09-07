package com.zh.dao.mongo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zh.entity.mongo.Note;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class NoteDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public String add(Note note) {
        Note savedNote = mongoTemplate.save(note);
        return savedNote.getId();
    }

    public long remove(String id) {
        DeleteResult deleteResult = mongoTemplate.remove(id);
        return deleteResult.getDeletedCount();
    }

    public long update(Note note) {
        Query query = new Query(Criteria.where("id").is(note.getId()));

        Update update = new Update();
        update.set("content", note.getContent());
        update.set("important", note.getImportant());

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Note.class);
        return updateResult.getModifiedCount();
    }

    public Note findById(String id) {
//        return mongoTemplate.findById(id, Note.class);

        Query query = new Query(Criteria.where("id").is(id));
        Note note = mongoTemplate.findOne(query, Note.class);
        return note;
    }

    public List<Note> listAll() {
        return mongoTemplate.findAll(Note.class);
    }
}
