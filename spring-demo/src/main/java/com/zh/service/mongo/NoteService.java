package com.zh.service.mongo;

import com.zh.dao.mongo.NoteDao;
import com.zh.entity.mongo.Note;
import com.zh.entity.mongo.NoteUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    public String add(String content, Boolean important) {
        Note note = new Note();
        note.setContent(content);
        note.setImportant(important);
        return noteDao.add(note);
    }

    public long remove(String id) {
        return noteDao.remove(id);
    }

    public long update(NoteUpdateDTO noteUpdate) {
        Assert.notNull(noteUpdate.getId(), "id不能为空");
        Note note = new Note();
        note.setImportant(noteUpdate.getImportant());
        note.setContent(note.getContent());
        note.setId(noteUpdate.getId());
        return noteDao.update(note);
    }

    public Note findById(String id) {
        return noteDao.findById(id);
    }

    public List<Note> listAll() {
        List<Note> allNotes = noteDao.listAll();
        return allNotes.stream()
                .filter(note -> note.getImportant() != null && note.getImportant())
                .collect(Collectors.toList());
    }
}
